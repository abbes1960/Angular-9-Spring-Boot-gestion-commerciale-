import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { DepotService} from '../../service/depot.service';
import { CompteurService} from '../../service/compteur.service';
import { Depot} from '../../model/depot';
import { Compteur} from '../../model/compteur';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Invent } from '../../model/invent';
import { InventService} from '../../service/invent.service';
import { DatePipe } from '@angular/common';
import { AddLinventComponent } from '../../invent/add-linvent/add-linvent.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
import { Article} from '../../model/article';
import { Linvent} from '../../model/linvent';
@Component({
  selector: 'app-add-invent',
  templateUrl: './add-invent.component.html',
  styleUrls: ['./add-invent.component.scss']
})
export class AddInventComponent implements OnInit {
 
  DepotList: Depot[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  direction : any={};
  constructor(public service:InventService,
    public depotService:DepotService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
ngOnInit() {
   this.minDate = this.transformDate(new Date());
   this.onSelectCompteur(1);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.depotService.getAll().subscribe(
      response =>{this.DepotList = response;}
     );
   }
  }


onSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numinv);
      }
   );  
 } 
   
    
InfoForm() {
  
    this.service.formData = this.fb.group({
      id :null,
      annee : 0,
      numero : 0,
      date_invent : '',
      code_dir : 0,
      libelle : '',
      lib_direction : '',
     
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
    this.dialog.open(AddLinventComponent, dialogConfig).afterClosed().subscribe(b10=>{
    
      this.calcul();
    });

  }
onDelete(item : Linvent,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

calcul(){
  this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
    return prev + curr.total;
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
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/avoirs']);
      });
   }
  
transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }

   onSelectDepot(code: number)
   {
     this.depotService.getData(code).subscribe(
       response =>{
         this.direction = response;
         this.f['lib_depot'].setValue(this.direction.libelle);
         }
      );  
     
   } 
 }
