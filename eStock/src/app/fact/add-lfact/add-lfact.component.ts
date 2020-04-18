import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Lfact } from '../../model/lfact';
import { FactService } from '../../service/fact.service';
import { LfactService } from '../../service/lfact.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-add-lfact',
  templateUrl: './add-lfact.component.html',
  styleUrls: ['./add-lfact.component.scss']
})
export class AddLfactComponent implements OnInit {
  formData: FormGroup;
  articleList:Article[];
  isValid:boolean=true;
  constructor( public service:LfactService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLfactComponent>,
        private articleService:ArticleService,
        private factService:FactService,public fb: FormBuilder){}
        get f() { return this.formData.controls; }

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.lcommandeIndex==null)
    {
      this.InfoForm();
    }
    else 
    this.formData =this.fb.group(Object.assign({},this.factService.list[this.data.listIndex]));
}


InfoForm() {
  this.formData = this.fb.group({
      Id: null,
      numero :this.data.id_commande,
      qte : 0,
      pu : 0,
      tva : 0,
      totht : 0,
      tottva :0,
      totttc :0,
      libart :'',
      code_article :'',
    });
  } 


updatePrice(ctrl){
  if(ctrl.selectedIndex == 0){
    this.f['pu'].setValue(0);
    this.f['tva'].setValue(0);
    this.f['libart'].setValue(' ');
    this.f['qte'].setValue(0);
  }
  else{
alert (this.articleList[ctrl.selectedIndex-1].pv);
    this.f['pu'].setValue(this.articleList[ctrl.selectedIndex-1].pv);
    this.f['tva'].setValue(this.articleList[ctrl.selectedIndex-1].tva);
    this.f['libart'].setValue(this.articleList[ctrl.selectedIndex - 1].libelle);
    this.f['id_article'].setValue( this.articleList[ctrl.selectedIndex - 1].code);
  }
  this.calcul();
}

calcul(){
  this.formData.value.totht = parseFloat((this.formData.value.qte * this.formData.value.pu).toFixed(3));
}

onSubmit() {
  if(this.validateForm(this.formData.value)){
  if(this.data.lcommandeIndex==null)
   this.factService.list.push(this.formData.value)

   this.dialogRef.close();
  }
 this.factService.list[this.data.listIndex] = this.formData.value;
  this.dialogRef.close();
  
}

validateForm(formData:Lfact){
  this.isValid=true;
  if(formData.code_article=='')
    this.isValid=false;
    else if(formData.qte ==0)
    this.isValid=false;
    return this.isValid;
}
}