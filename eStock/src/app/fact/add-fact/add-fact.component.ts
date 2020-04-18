
import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ClientService} from '../../service/client.service';
import { CompteurService} from '../../service/compteur.service';
import { Client} from '../../model/client';
import { Compteur} from '../../model/compteur';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Fact } from '../../model/fact';
import { FactService} from '../../service/fact.service';
import { LfactService} from '../../service/lfact.service'
import { DatePipe } from '@angular/common';
import { AddLfactComponent } from '../../fact/add-lfact/add-lfact.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
import { Article} from '../../model/article';
import { Lcommande} from '../../model/lcommande';

@Component({
  selector: 'app-add-fact',
  templateUrl: './add-fact.component.html',
  styleUrls: ['./add-fact.component.scss']
})
export class AddFactComponent implements OnInit {
  ClientList: Client[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  client : any={};
  constructor(public service:FactService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lfactservice: LfactService,
    public clientService :ClientService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
  ngOnInit() {
   this.minDate = this.transformDate(new Date());
   this.OnSelectCompteur(1);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.clientService.getAll().subscribe(
      response =>{this.ClientList = response;}
     );
   }
  }


  OnSelectCompteur(id: number)
  {
    this.compteurservice.getData(id).subscribe(
      response =>{
        this.compteur = response;
        this.f['numero'].setValue(20200000 + this.compteur.numfac);
        }
     );  
  }
   // this.service.getData(parseInt(Id)).then(res=> {
   // this.service.formData =res.commande;
  //  this.service.lcommande =res.commandeDetails;
  //  });
    
InfoForm() {
    this.service.formData = this.fb.group({
      id :null,
      date_mvt : '',
      numero  : 0,
      annee : 0,
      code_client : 0,
      lib_client : '',
      libelle : '',
      totht : 0,
      totrem : 0,
      totfodec : 0,
      tottva : 0,
      totttc : 0,
      });
    } 
OnSelectClient(code: number)
    {
      this.clientService.getData(code).subscribe(
        response =>{
          this.client= response;
          this.f['lib_client'].setValue(this.client.libelle);
          }
       );   
    } 
    
ResetForm() {
      this.service.formData.reset();
  }
  
Add(lcommandeIndex,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lcommandeIndex,Id};
    this.dialog.open( AddLfactComponent, dialogConfig).afterClosed().subscribe(b10=>{
      this.Calcul();
    });

  }
  OnDelete(item : Lcommande,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.Calcul();
   }

  Calcul(){
     this.service.formData.value.totht = this.service.list.reduce((prev, curr) => {
       return prev + curr.totht;
     }, 0);
     this.service.formData.value.tottva = this.service.list.reduce((prev, curr) => {
      return prev + curr.tottva;
    }, 0);
     this.service.formData.value.totttc = this.service.list.reduce((prev, curr) => {
      return prev + curr.totttc;
    }, 0);

     this.service.formData.value.totht = parseFloat(this.service.formData.value.totht.toFixed(3));
     this.service.formData.value.tottva = parseFloat(this.service.formData.value.tottva.toFixed(3));
     this.service.formData.value.totttc = parseFloat(this.service.formData.value.totttc.toFixed(3));
   }
   validateForm(){
     this.isValid = true ;
    
     if(this.service.formData.value.id_client==0)
     this.isValid =false;
    
     else if (this.service.list.length==0)
     this.isValid =false;
     return this.isValid;
   }

   onSubmit(){
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/livrs']);
      });
   }
  
   transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
    
}

