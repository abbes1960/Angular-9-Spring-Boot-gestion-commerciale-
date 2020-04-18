import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Avoir } from '../../model/avoir';
import { Lavoir } from '../../model/lavoir';
import { AvoirService } from '../../service/avoir.service';
import { LavoirService } from '../../service/lavoir.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-add-lavoir',
  templateUrl: './add-lavoir.component.html',
  styleUrls: ['./add-lavoir.component.scss']
})
export class AddLavoirComponent implements OnInit {
  formData: FormGroup;
  articleList:Article[];
  isValid:boolean=true;
  wtotht = 0;
  wtotfodec = 0;
  wtottva = 0;
  wtotttc = 0;
  constructor( public service:LavoirService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLavoirComponent>,
        private articleService:ArticleService,
        private avoirService:AvoirService,public fb: FormBuilder){}
        get f() { return this.formData.controls; }

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.Index==null)
    {
      this.InfoForm();
    }
    else 
    this.formData =this.fb.group(Object.assign({},this.avoirService.list[this.data.Index]));
}


InfoForm() {
  this.formData = this.fb.group({
      Id: null,
      numero :0,
      qte : 0,
      pu : 0,
      tva : 0,
      fodec : 0,
      totht : 0,
      totfodec : 0,
      tottva : 0,
      totttc : 0,
      libart :'',
      code_article :'',
    });
  } 


updatePrice(ctrl){
  if(ctrl.selectedIndex == 0){
    this.f['pu'].setValue(0); 
    this.f['tva'].setValue(0);
    this.f['fodec'].setValue(0);
    this.f['libart'].setValue(' ');
    this.f['qte'].setValue(0);
  }
  else{
alert (this.articleList[ctrl.selectedIndex-1].pv);
    this.f['pu'].setValue(this.articleList[ctrl.selectedIndex-1].pv);
    this.f['tva'].setValue(this.articleList[ctrl.selectedIndex-1].tva);
    this.f['fodec'].setValue(this.articleList[ctrl.selectedIndex-1].fodec);
    this.f['libart'].setValue(this.articleList[ctrl.selectedIndex - 1].libelle);
    this.f['code_article'].setValue( this.articleList[ctrl.selectedIndex - 1].code);
  }
  this.updateTotal();
}


updateTotal(){
  this.wtotht =  parseFloat((this.formData.value.qte * this.formData.value.pu).toFixed(3));
  this.wtotfodec = parseFloat(((this.wtotht * this.formData.value.fodec)*0.01).toFixed(3)); 
  this.wtottva = parseFloat((((this.wtotht + this.wtotfodec)* this.formData.value.tva)*0.01).toFixed(3)); 
  this.wtotttc = parseFloat((this.wtotht + this.wtotfodec + this.wtottva).toFixed(3));
  this.f['totht'].setValue(this.wtotht);
  this.f['totfodec'].setValue(this.wtotfodec);
  this.f['tottva'].setValue(this.wtottva);
  this.f['totttc'].setValue(this.wtotttc);
}

onSubmit() {
  if(this.validateForm(this.service.formData.value)){
  if(this.data.Index==null)
   this.avoirService.list.push(this.service.formData.value)
  }
  this.avoirService.list[this.data.Index] = this.formData.value;
  this.dialogRef.close();
}

validateForm(formData:Lavoir){
  this.isValid=true;
  if(formData.code_article=='')
    this.isValid=false;
    else if(formData.qte ==0)
    this.isValid=false;
    return this.isValid;
}
}
