import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { CompteurService}   from '../../service/compteur.service';
import { RecoufService}     from '../../service/recouf.service';
import { LrecoufService}    from '../../service/lrecouf.service';
import { FournisseurService}    from '../../service/fournisseur.service';
import { Fournisseur}            from '../../model/fournisseur';
import { Compteur}          from '../../model/compteur';
import { Recouf}            from '../../model/recouf';
import { Lrecouf}           from '../../model/lrecouf';
import { NgForm }           from '@angular/forms';
import { DatePipe }         from '@angular/common';
import { ToastrService }    from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { AddLrecoufComponent } from '../../recouf/add-lrecouf/add-lrecouf.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
@Component({
  selector: 'app-add-recouf',
  templateUrl: './add-recouf.component.html',
  styleUrls: ['./add-recouf.component.scss']
})
export class AddRecoufComponent implements OnInit {

  fourList: Fournisseur[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  compteur : any = {};
  four   : any = {};
  constructor(public service:RecoufService,
    public compteurservice : CompteurService,
    public fourService : FournisseurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lrecouvservice: LrecoufService,
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
      this.fourService.getAll().subscribe(
      response =>{this.fourList = response;}
     );
   }
  }


  onSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numrecf);
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
      code_four : 0,
      lib_four : '',
      total : 0,
      });
    } 
  
  ResetForm() {
      this.service.formData.reset();
  }
  
  AddData(lrecoufIndex,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lrecoufIndex,Id};
    this.dialog.open(AddLrecoufComponent  , dialogConfig).afterClosed().subscribe(b10=>{
      this.calcul();
    });
  }

  onDelete(item : Lrecouf,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

  calcul(){
    this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.montant;
    }, 0));
  }

  validateForm(){
     this.isValid = true ;
    if(this.service.formData.value.code_four==0)
     this.isValid =false;
     else if (this.service.list.length==0)
     this.isValid =false;
     return this.isValid;
   }

  onSubmit(){
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/lrecouf']);
      });
    }

  onSelectFour(code: number)
      {
        this.fourService.getData(code).subscribe(
          response =>{
            this.four = response;
            this.f['lib_four'].setValue( this.four.libelle);
            }
         );   
      } 

  transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
 
}
