import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ClientService} from '../../service/client.service';
import { DirectionService} from '../../service/direction.service';
import { CompteurService} from '../../service/compteur.service';
import { Client} from '../../model/client';
import { Compteur} from '../../model/compteur';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Commande } from '../../model/commande';
import { CommandeService} from '../../service/commande.service';
import { LcommandeService} from '../../service/lcommande.service';
import { DatePipe } from '@angular/common';
import { AddLcommandeComponent } from '../../comm/add-lcommande/add-lcommande.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
import { Article} from '../../model/article';
import { Lcommande} from '../../model/lcommande';
import { formatDate } from '@angular/common';
import '@angular/localize/init';
@Component({
  selector: 'app-add-comm',
  templateUrl: './add-comm.component.html',
  styleUrls: ['./add-comm.component.scss']
})
export class AddCommComponent implements OnInit {
  ClientList: Client[];
  
  isValid:boolean = true;
  articleService: any;
  Date;
  compteur : any={};
  client   : any= {};
  annee  = 0;
  constructor(public service:CommandeService,
    public compteurservice:CompteurService,
    public lcommservice:LcommandeService,
    private dialog:MatDialog,public fb: FormBuilder,
    public clientService :ClientService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls }
   
ngOnInit() {

   if (this.service.choixmenu == "A"){
    this.InfoForm();
    this.service.list = [];
    this.Date = this.transformDate(new Date(Date.now()));
    this.annee = (this.Date).toString().substring(0,4);
    this.f['annee'].setValue(this.annee);
    this.onSelectCompteur(this.annee);
    }
      else
    {
    //this.service.getData(this.service.formData.value.id).subscribe(res=> {
   // this.service.formData =this.fb.group(Object.assign({},res));
   // });
    this.lcommservice.getAll(this.service.formData.value.numero).subscribe(
     response =>{this.service.list = response}
     );
     this.f['date_comm'].setValue(this.service.formData.value.date_comm);
    }

this.clientService.getAll().subscribe(
  response =>{this.ClientList = response;}
 );
  }


  


onSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numcomm);
      }
   );  
 } 
   
    
InfoForm() {
    this.service.formData = this.fb.group({
      id :null,
      annee : 0,
      numero : 0,
      date_comm : '',
      code_client : 0,
      lib_client : '',
      libelle : '',
      totht : 0,
      tottva : 0,
      totttc : 0,
      lcomms :[],
      });
    } 
  
resetForm() {
      this.service.formData.reset();
  }

AddData(lcommandeIndex,Id){  
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lcommandeIndex,Id};
    this.dialog.open(AddLcommandeComponent, dialogConfig).afterClosed().subscribe(b10=>{
      this.calcul();
    });
  }

  
onDelete(item : Lcommande,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

calcul(){
  this.f['totht'].setValue(this.service.list.reduce((prev, curr) => {
    return prev + curr.totht;
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
    this.f['lcomms'].setValue(this.service.list);
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/lcomm']);
      });
   }
  
transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
OnSelectClient(ctrl)
   {
      if(ctrl.selectedIndex == 0){
       this.f['lib_client'].setValue('');
       this.f['code_client'].setValue('');
      }
      else{
         this.f['lib_client'].setValue(this.ClientList[ctrl.selectedIndex - 1].libelle);
         this.f['code_client'].setValue(this.ClientList[ctrl.selectedIndex - 1].code);
      }
    }
    
}
