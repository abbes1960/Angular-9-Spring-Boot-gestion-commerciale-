import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Lrecouv } from '../../model/lrecouv';
import { RecouvService } from '../../service/recouv.service';
import { LrecouvService } from '../../service/lrecouv.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-add-lrecouv',
  templateUrl: './add-lrecouv.component.html',
  styleUrls: ['./add-lrecouv.component.scss']
})
export class AddLrecouvComponent implements OnInit {
  formData: Lrecouv;
  articleList:Article[];
  isValid:boolean=true;
  constructor( public service:LrecouvService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLrecouvComponent>,
        private articleService:ArticleService,
        private recouvService:RecouvService,public fb: FormBuilder){}
     

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.lrecouvIndex==null)
    {
      this.InfoForm();
    }
    else 
    this.formData =Object.assign({},this.recouvService.list[this.data.listIndex])    
}


InfoForm() {
  this.formData =  {
    id: null,
    annee : 0,
    numero : 0,
    mode_reg : '',
    nump : '',
    banque : '',
    echeance  : '',
    date_reg  : '',
    montant : 0,
   };
  } 

  
  
  
  onSubmit(form:NgForm){
    
    
    if(this.validateForm(form.value)){
  
    if(this.data.lb1016Index==null)
     this.recouvService.list.push(form.value);
    else 
    this.recouvService.list[this.data.lb1016Index] = form.value;
    this.dialogRef.close();
       
  }
  }
  validateForm(formData:Lrecouv){
    this.isValid=true;
    if(formData.mode_reg== null)
      this.isValid=false;
      else if(formData.montant ==0)
      this.isValid=false;
      return this.isValid;
  }
}
  


  



