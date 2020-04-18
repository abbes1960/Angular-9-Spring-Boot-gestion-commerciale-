import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Lrecouf } from '../../model/lrecouf';
import { RecoufService } from '../../service/recouf.service';
import { LrecoufService } from '../../service/lrecouf.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-add-lrecouf',
  templateUrl: './add-lrecouf.component.html',
  styleUrls: ['./add-lrecouf.component.scss']
})
export class AddLrecoufComponent implements OnInit {
  formData: Lrecouf;
  articleList:Article[];
  isValid:boolean=true;
  constructor( public service:LrecoufService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLrecoufComponent>,
        private articleService:ArticleService,
        private recoufService:RecoufService,public fb: FormBuilder){}
     

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.lrecouvIndex==null)
    {
      this.InfoForm();
    }
    else 
    this.formData =Object.assign({},this.recoufService.list[this.data.listIndex])    
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
     this.recoufService.list.push(form.value);
    else 
    this.recoufService.list[this.data.lb1016Index] = form.value;
    this.dialogRef.close();
       
  }
  }
  validateForm(formData:Lrecouf){
    this.isValid=true;
    if(formData.mode_reg== null)
      this.isValid=false;
      else if(formData.montant ==0)
      this.isValid=false;
      return this.isValid;
  }
}
  


  



