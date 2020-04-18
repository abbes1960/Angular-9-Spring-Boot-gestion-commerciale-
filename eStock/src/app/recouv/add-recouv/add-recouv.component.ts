import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { CompteurService}   from '../../service/compteur.service';
import { RecouvService}     from '../../service/recouv.service';
import { LrecouvService}    from '../../service/lrecouv.service';
import { ClientService}    from '../../service/client.service';
import { Client}            from '../../model/client';
import { Compteur}          from '../../model/compteur';
import { Recouv}            from '../../model/recouv';
import { Lrecouv}           from '../../model/lrecouv';
import { NgForm }           from '@angular/forms';
import { DatePipe }         from '@angular/common';
import { ToastrService }    from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { AddLrecouvComponent } from '../../recouv/add-lrecouv/add-lrecouv.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
@Component({
  selector: 'app-add-recouv',
  templateUrl: './add-recouv.component.html',
  styleUrls: ['./add-recouv.component.scss']
})
export class AddRecouvComponent implements OnInit {
  annee : 0;
  clientList: Client[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  compteur : any = {};
  client   : any = {};
  constructor(public service:RecouvService,
    public compteurservice : CompteurService,
    public clientService : ClientService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lrecouvservice: LrecouvService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
  ngOnInit() {
   
   this.minDate = this.transformDate(new Date());
   alert(this.minDate);
   this.annee = (this.minDate).toString().substring(0,4);
   alert (this.annee);
   this.onSelectCompteur(1);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.clientService.getAll().subscribe(
      response =>{this.clientList = response;}
     );
   }
  }


  onSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numreg);
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
      annee : 0,
      numero : 0,
      date_mvt : '',
      code_client : 0,
      lib_client : '',
      total : 0,
      });
    } 
  
  ResetForm() {
      this.service.formData.reset();
  }
  
  AddData(lrecouvIndex,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lrecouvIndex,Id};
    this.dialog.open(AddLrecouvComponent , dialogConfig).afterClosed().subscribe(b10=>{
      this.updateGrandTotal();
    });
  }

  onDelete(item : Lrecouv,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.updateGrandTotal();
   }

  updateGrandTotal(){
    this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.montant;
    }, 0));
  }

  validateForm(){
     this.isValid = true ;
    if(this.service.formData.value.code_client==0)
     this.isValid =false;
     else if (this.service.list.length==0)
     this.isValid =false;
     return this.isValid;
   }

  onSubmit(){
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/commandeliste']);
      });
    }

  onSelectClient(code: number)
      {
        this.clientService.getData(code).subscribe(
          response =>{
            this.client = response;
            this.f['lib_client'].setValue( this.client.libelle);
            }
         );   
      } 

  transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
 
}









  