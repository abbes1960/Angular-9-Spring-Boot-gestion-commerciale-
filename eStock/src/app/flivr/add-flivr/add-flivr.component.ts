import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FournisseurService} from '../../service/fournisseur.service';
import { CompteurService} from '../../service/compteur.service';
import { Fournisseur} from '../../model/fournisseur';
import { Compteur} from '../../model/compteur';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Flivr} from '../../model/flivr';
import { FlivrService} from '../../service/flivr.service';
import { LflivrService} from '../../service/lflivr.service'
import { DatePipe } from '@angular/common';
import { AddFllivrComponent } from '../../flivr/add-fllivr/add-fllivr.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
import { Article} from '../../model/article';
import { Fllivr} from '../../model/fllivr';

@Component({
  selector: 'app-add-flivr',
  templateUrl: './add-flivr.component.html',
  styleUrls: ['./add-flivr.component.scss']
})
export class AddFlivrComponent implements OnInit {
  FourList: Fournisseur[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  compteur : any={};
  fournisseur : any= {};
  get f() { return this.service.formData.controls; }
  constructor(public service:FlivrService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public llivrservice: LflivrService,
    public fourService :FournisseurService,
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
      this.fourService.getAll().subscribe(
      response =>{this.FourList = response;}
     );
   }
  }


  onSelectCompteur(id: number)
  {
    this.compteurservice.getData(id).subscribe(
      response =>{
        this.f['numero'].setValue(20200000 + this.compteur.numblf);
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
      numero : 0,
      date_mvt : '',
      code_four : 0,
      libelle : '',
      totht : 0,
      tottva : 0,
      totttc : 0,
     
      });
    } 
  
    ResetForm() {
      this.service.formData.reset();
  }
  
  AddData(fllivrIndex,Id){
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={fllivrIndex,Id};

    this.dialog.open(AddFllivrComponent , dialogConfig).afterClosed().subscribe(b10=>{
      this.updateGrandTotal();

    });

  }
  onDelete(item : Fllivr,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.fllivr.splice(i,1);
   this.updateGrandTotal();
   }

   updateGrandTotal(){
     this.service.formData.value.totht = this.service.fllivr.reduce((prev, curr) => {
       return prev + curr.totht;
     }, 0);
     this.service.formData.value.tottva = this.service.fllivr.reduce((prev, curr) => {
      return prev + curr.tottva;
    }, 0);
     this.service.formData.value.totttc = this.service.fllivr.reduce((prev, curr) => {
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
    
     else if (this.service.fllivr.length==0)
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
  
   transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
   SelectFour(code_four : number)
   {
     this.fourService.getData(code_four).subscribe(
       response =>{
         this.fournisseur = response;
         this.f['lib_four'].setValue(this.fournisseur.libelle);
         }
      ); 
   }
 
}
