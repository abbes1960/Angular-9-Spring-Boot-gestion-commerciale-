import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Bs1016 } from '../../model/bs1016';
import { Lbs1016 } from '../../model/lbs1016';
import { Bs1016Service } from '../../service/bs1016.service';
import { Lbs1016Service } from '../../service/lbs1016.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-add-lbs1016',
  templateUrl: './add-lbs1016.component.html',
  styleUrls: ['./add-lbs1016.component.scss']
})
export class AddLbs1016Component implements OnInit {
  formData: Lbs1016;
  articleList:Article[];
  isValid:boolean=true;
  constructor( public service:Lbs1016Service,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLbs1016Component>,
        private articleService:ArticleService,
        private b1016Service:Bs1016Service,public fb: FormBuilder){}
     

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.lcommandeIndex==null)
    {
      this.InfoForm();
    }
    else 
    this.formData =Object.assign({},this.b1016Service.list[this.data.listIndex])    
}


InfoForm() {
  this.formData =  {
      id: null,
      numero : 0,
      qte : 0,
      pu : 0,
      total : 0,
      libart :'',
      code_article :'',
   };
  } 

  updatePrice(ctrl){
    if(ctrl.selectedIndex == 0){
      this.formData.pu = 0;
      this.formData.libart = '';
  
    }
    else{
      this.formData.pu =this.articleList[ctrl.selectedIndex-1].pv;
      this.formData.libart = this.articleList[ctrl.selectedIndex - 1].libelle;
       this.formData.code_article = this.articleList[ctrl.selectedIndex - 1].code;
  
    }
    this.updateTotal();
  }
  
  updateTotal(){
    this.formData.total = parseFloat((this.formData.qte * this.formData.pu).toFixed(3));
  }
  
  onSubmit(form:NgForm){
    
    
    if(this.validateForm(form.value)){
   
    if(this.data.lb1016Index==null)
     this.b1016Service.list.push(form.value);
    else 
    this.b1016Service.list[this.data.lb1016Index] = form.value;
    this.dialogRef.close();
       
  }
  }
  validateForm(formData:Lbs1016){
    this.isValid=true;
    if(formData.code_article== null)
      this.isValid=false;
      else if(formData.qte ==0)
      this.isValid=false;
      return this.isValid;
  }
}
