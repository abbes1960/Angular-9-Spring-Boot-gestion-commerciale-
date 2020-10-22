import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Linvent } from '../../model/linvent';
import { InventService } from '../../service/invent.service';
import { LinventService } from '../../service/linvent.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-add-linvent',
  templateUrl: './add-linvent.component.html',
  styleUrls: ['./add-linvent.component.scss']
})
export class AddLinventComponent implements OnInit {
  formData: FormGroup;
  articleList:Article[];
  isValid:boolean=true;
  wtotal = 0;
 
  constructor( public service:LinventService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLinventComponent>,
        private articleService:ArticleService,
        private inventService:InventService,public fb: FormBuilder){}
        get f() { return this.formData.controls; }
       

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.linventIndex==null)
    {
      this.InfoForm();
    }
    
    
    else 
     this.formData =this.fb.group(Object.assign({},this.inventService.list[this.data.linventIndex]));
}


InfoForm() {
  this.formData = this.fb.group({
      id: null,
      numero :0,
      qte1 : 0,
      qte2 : 0,
      qte3 : 0,
      qte4 : 0,
      total : 0,
      libart :'',
      code :'',

     
   
    });
  } 


updatePrice(ctrl){

  if(ctrl.selectedIndex == 0){
    this.f['pu'].setValue(0);
  
  }
  else{

    this.f['pu'].setValue(this.articleList[ctrl.selectedIndex-1].pv);
  
    this.f['libart'].setValue(this.articleList[ctrl.selectedIndex - 1].libelle);
    this.f['code_article'].setValue( this.articleList[ctrl.selectedIndex - 1].code);
  }
  this.updateTotal();
}

updateTotal(){
  this.wtotal =  parseFloat((this.formData.value.qte4 * this.formData.value.pu).toFixed(3));
  
  this.f['total'].setValue(this.wtotal);
  
}

onSubmit() {
  if(this.data.lcommandeIndex==null)
  {
    this.inventService.list.push(this.formData.value)
    this.dialogRef.close();
  }
  else
{
  this.inventService.list[this.data.linventIndex] = this.formData.value;

}
this.dialogRef.close();

 
}

validateForm(formData:Linvent){
  this.isValid=true;
  if(formData.code =='')
    this.isValid=false;
    else if(formData.qte1 ==0)
    this.isValid=false;
    return this.isValid;
}}
