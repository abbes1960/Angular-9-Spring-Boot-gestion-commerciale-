import { Component, OnInit }  from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FournisseurService}       from '../../service/fournisseur.service';
import { CompteurService}     from '../../service/compteur.service';
import { Fournisseur}         from '../../model/fournisseur';
import { Compteur}            from '../../model/compteur';
import { ToastrService }      from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { NgForm }             from '@angular/forms';
import { Fcommande }          from '../../model/fcommande';
import { FcommandeService}    from '../../service/fcommande.service';
import { LfcommandeService}   from '../../service/lfcommande.service'
import { DatePipe }           from '@angular/common';
import { AddFlcommComponent } from '../../fcomm/add-flcomm/add-flcomm.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable }         from "rxjs";
import { Article}             from '../../model/article';
import { Lfcommande}          from '../../model/lfcommande';

@Component({
  selector: 'app-add-fcomm',
  templateUrl: './add-fcomm.component.html',
  styleUrls: ['./add-fcomm.component.scss']
})
export class AddFcommComponent implements OnInit {
  FourList: Fournisseur[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  fournisseur : any={};
  constructor(public service:FcommandeService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lcommservice: LfcommandeService,
    public fournisseurService :FournisseurService,
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
      this.fournisseurService.getAll().subscribe(
      response =>{this.FourList = response;}
     );
   }
}


onSelectCompteur(id: number)
{
 this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.numcomm = (20200000 + this.compteur.numcomm);
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
      date_cde : '',
      code_four : 0,
      lib_four : '',
      libelle : '',
      totht : 0,
      tottva : 0,
      totttc : 0,
      });
    } 
  
    ResetForm() {
      this.service.formData.reset();
  }
  
  Add(flcommandeIndex,Id){
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={flcommandeIndex,Id};

    this.dialog.open(AddFlcommComponent, dialogConfig).afterClosed().subscribe(b10=>{
      this.calcul();

    });

  }
  onDelete(item : Lfcommande,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

   calcul(){
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
        this.router.navigate(['/lfcommande']);
      });
    }
  
  transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
SelectFour(code_four : number)
{
  this.fournisseurService.getData(code_four).subscribe(
    response =>{
      this.fournisseur = response;
      this.f['lib_four'].setValue(this.fournisseur.libelle);
      }
   ); 
}

   
 

}
