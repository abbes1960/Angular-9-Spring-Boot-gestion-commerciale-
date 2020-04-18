import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Lb1016 } from '../../model/lb1016';
import { B1016Service } from '../../service/b1016.service';
import { Lb1016Service } from '../../service/lb1016.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-add-lb1016',
  templateUrl: './add-lb1016.component.html',
  styleUrls: ['./add-lb1016.component.scss']
})
export class AddLb1016Component implements OnInit {
  formData: Lb1016;
  articleList:Article[];
  isValid:boolean=true;
  constructor( public service:Lb1016Service,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLb1016Component>,
        private articleService:ArticleService,
        private b1016Service:B1016Service,public fb: FormBuilder){}
  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.lb1016Index==null)
    {
      this.InfoForm();
    }
    else 
    {
      alert("ddddddd");
      this.formData =Object.assign({},this.b1016Service.list[this.data.lb1016Index])    
    }
    
}


InfoForm() {
  this.formData =  {
      id: null,
      numero : 0,
      qte : 0,
      pu : 0,
      total : 0,
      libart :'',
      code_article : '',
   };
  } 

  selectPrice(ctrl){
    if(ctrl.selectedIndex == 0){
      this.formData.pu = 0;
      this.formData.libart = '';
      this.formData.code_article = '';
    }
    else{
      this.formData.pu =this.articleList[ctrl.selectedIndex-1].pv;
      this.formData.libart = this.articleList[ctrl.selectedIndex - 1].libelle;
      this.formData.code_article = this.articleList[ctrl.selectedIndex - 1].code;
     
    }
    this.calcul();
  }
  
  calcul(){
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
  validateForm(formData:Lb1016){
    this.isValid=true;
    if(formData.code_article== null)
      this.isValid=false;
      else if(formData.qte ==0)
      this.isValid=false;
      return this.isValid;
  }
}
