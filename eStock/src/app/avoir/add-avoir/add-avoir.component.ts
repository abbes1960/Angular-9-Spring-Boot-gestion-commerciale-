import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ClientService} from '../../service/client.service';
import { CompteurService} from '../../service/compteur.service';
import { Client} from '../../model/client';
import { Compteur} from '../../model/compteur';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Avoir } from '../../model/avoir';
import { AvoirService} from '../../service/avoir.service';
import { LavoirService} from '../../service/lavoir.service'
import { DatePipe } from '@angular/common';
import { AddLavoirComponent } from '../../avoir/add-lavoir/add-lavoir.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
import { Article} from '../../model/article';
import { Lavoir} from '../../model/lavoir';

@Component({
  selector: 'app-add-avoir',
  templateUrl: './add-avoir.component.html',
  styleUrls: ['./add-avoir.component.scss']
})
export class AddAvoirComponent implements OnInit {
  ClientList: Client[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  get f() { return this.service.formData.controls; }
  constructor(public service:AvoirService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lavoirservice : LavoirService,
    public clientService :ClientService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }

  ngOnInit() {
   this.minDate = this.transformDate(new Date());
   this.onSelectCompteur(1);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.clientService.getAll().subscribe(
      response =>{this.ClientList = response;}
     );
   }
  }


  onSelectCompteur(id: number)
{
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numav);
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
      date_cde : '',
      numero   : 0,
      id_client : 0,
      lib_client : '',
      libelle : '',
      totht : 0,
      totfodec : 0,
      tottva : 0,
      totttc : 0,
     
      });
    } 
  
  ResetForm() {
      this.service.formData.reset();1
  }
  
  AddData(Index,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={Index,Id};
    this.dialog.open(AddLavoirComponent, dialogConfig).afterClosed().subscribe(b10=>{
      this.calcul();
    });
  }
  selectClient(ctrl){
    if(ctrl.selectedIndex == 0){
       this.f['lib_client'].setValue(' ');
      }
    else{
      this.f['lib_client'].setValue(this.ClientList[ctrl.selectedIndex-1].libelle);
    }
  }

  onDelete(item : Lavoir,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

   calcul(){
    this.f['totht'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totht;
    }, 0));
    this.f['totfodec'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totfodec;
    }, 0)); 
    this.f['tottva'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.tottva;
    }, 0));
    this.f['totttc'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totttc;
    }, 0));   
     
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
      this.f['lavoirs'].setValue(this.service.list);
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/lavoir']);
      });
  }
  
   transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
 

}
