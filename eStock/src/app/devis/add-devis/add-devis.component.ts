import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ClientService} from '../../service/client.service';
import { CompteurService} from '../../service/compteur.service';
import { Client} from '../../model/client';
import { Compteur} from '../../model/compteur';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Devis } from '../../model/devis';
import { DevisService} from '../../service/devis.service';
import { DatePipe } from '@angular/common';
import { AddLdevisComponent } from '../../devis/add-ldevis/add-ldevis.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
import { Article} from '../../model/article';
import { Ldevis} from '../../model/ldevis';
@Component({
  selector: 'app-add-devis',
  templateUrl: './add-devis.component.html',
  styleUrls: ['./add-devis.component.scss']
})
export class AddDevisComponent implements OnInit {
  clientList: Client[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  client   : any={};
  constructor(public service:DevisService,
    public clientService:ClientService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
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
      response =>{this.clientList = response;}
     );
   }
  }
OnSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numdevis);
      }
   );  
 } 
InfoForm() {
    this.service.formData = this.fb.group({
      id :null,
      annee : 0,
      numero : 0,
      date_mvt: '',
      code_client : 0,
      libelle : '',
      lib_client : '',
      totht : 0,
      tottva : 0,
      totttc : 0,
     
      });
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
    this.dialog.open(AddLdevisComponent, dialogConfig).afterClosed().subscribe(b10=>{
    this.calcul();
    });

  }
OnDelete(item : Ldevis,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

calcul(){
  this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
    return prev + curr.totht;
  }, 0));
 
   }
validateForm(){
     this.isValid = true ;
    
     if(this.service.formData.value.code_dir==0)
     this.isValid =false;
    
     else if (this.service.list.length==0)
     this.isValid =false;
     return this.isValid;
   }

onSubmit(){
      this.service.save(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/ldevis']);
      });
   }
  
transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
onselectClient(code: number)
{
  this.clientService.getData(code).subscribe(
    response =>{
      this.client = response;
      this.f['lib_client'].setValue(this.client.libelle);
      }
   );  
  } 
 }

