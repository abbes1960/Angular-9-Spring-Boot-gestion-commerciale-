import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { DirectionService} from '../../service/direction.service';
import { ResidenceService} from '../../service/residence.service';
import { CompteurService}  from '../../service/compteur.service';
import { Bs1016Service}     from '../../service/bs1016.service';
import { Lbs1016Service}    from '../../service/lbs1016.service';
import { Direction}        from '../../model/direction';
import { Residence}        from '../../model/residence';
import { Compteur}         from '../../model/compteur';
import { Article}          from '../../model/article';
import { Bs1016}            from '../../model/bs1016';
import { Lbs1016}           from '../../model/lbs1016';
import { NgForm }          from '@angular/forms';
import { DatePipe }        from '@angular/common';
import { ToastrService }   from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { AddLb1016Component } from '../../b1016/add-lb1016/add-lb1016.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
@Component({
  selector: 'app-add-bs1016',
  templateUrl: './add-bs1016.component.html',
  styleUrls: ['./add-bs1016.component.scss']
})
export class AddBs1016Component implements OnInit {
  directionList: Direction[];
  residenceList: Residence[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  direction : any={};
  residence : any={};
  constructor(public service:Bs1016Service,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lb1016service: Lbs1016Service,
    public directionService :DirectionService,
    public residenceService :ResidenceService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
ngOnInit() {
   this.minDate = this.TransformDate(new Date());
   this.OnSelectCompteur(1);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.directionService.getAll().subscribe(
      response =>{this.directionList = response;}
     );
   }
  }


OnSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numbons);
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
      code_dir : 0,
      code_res : 0,
      lib_direction : '',
      lib_residence : '',
      libelle : '',
      total : 0,
      });
    } 
  
ResetForm() {
      this.service.formData.reset();
  }
  
AddData(lcommandeIndex,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lcommandeIndex,Id};
    this.dialog.open(AddLb1016Component , dialogConfig).afterClosed().subscribe(b10=>{
      this.Calcul();
    });
  }

OnDelete(item : Lbs1016,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.Calcul();
   }

Calcul(){
    this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.total;
    }, 0));
  }

ValidateForm(){
     this.isValid = true ;
     if(this.service.formData.value.id_client==0)
     this.isValid =false;
     else if (this.service.list.length==0)
     this.isValid =false;
     return this.isValid;
   }

OnSubmit(){
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/commandeliste']);
      });
    }

OnSelectDir(code: number)
{
  this.directionService.getData(code).subscribe(
    response =>{
      this.direction = response;
      this.f['lib_direction'].setValue(this.direction.libelle);
      }
   );  
  this.residenceService.listResidence(code).subscribe(
    response =>{this.residenceList = response;}
   );  
} 

TransformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }

   onSelectRes(code: number)
      {
        this.residenceService.getData(code).subscribe(
          response =>{
            this.residence = response;
            this.f['lib_residence'].setValue(this.residence.libelle);
            }
         );  
      } 
 
}