import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Lcommande } from '../../model/lcommande';
import { CommandeService } from '../../service/commande.service';
import { LcommandeService } from '../../service/lcommande.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-add-lcomms',
  templateUrl: './add-lcomms.component.html',
  styleUrls: ['./add-lcomms.component.scss']
})
export class AddLcommsComponent implements OnInit {
  formData: FormGroup;
  articleList:Article[];
  isValid:boolean=true;
  wtotht = 0;
  wtottva = 0;
  wtotttc = 0;
  constructor( public service:LcommandeService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef< AddLcommsComponent>,
        private articleService:ArticleService,
        private commandeService:CommandeService,public fb: FormBuilder){}
        get f() { return this.formData.controls; }
       

  ngOnInit() {
    this.articleService.getAll().subscribe(
      response =>{this.articleList= response;}
     );
    if(this.data.Index==1)
    {
      this.InfoForm();
    }
    else 
     this.formData =this.fb.group(Object.assign({},this.commandeService.list[this.data.lcommandeIndex]));
}


InfoForm() {
  this.formData = this.fb.group({
      id      : null,
      numero  :0,
      ligne   : 0,
      qte     : 0,
      pu      : 0,
      tva     : 0,
      rem     : 0,
      totht   : 0,
      tottva  :0,
      totttc  :0,
      lib_art :'',
});
  } 





cal(){
  this.wtotht =  parseFloat((this.formData.value.qte * this.formData.value.pu).toFixed(3));
  this.wtottva = parseFloat(((this.wtotht * this.formData.value.tva)*0.01).toFixed(3)); 
  this.wtotttc = parseFloat((this.wtotht + this.wtottva).toFixed(3));
  this.f['totht'].setValue(this.wtotht);
  this.f['tottva'].setValue(this.wtottva);
  this.f['totttc'].setValue(this.wtotttc);
}

onSubmit() {
  if(this.data.Index==null)
  {
    this.commandeService.list.push(this.formData.value)
    this.dialogRef.close();
  }
  else
{
  this.commandeService.list[this.data.Index] = this.formData.value;
}
this.dialogRef.close();

 
}

validateForm(formData:Lcommande){
  this.isValid=true;
  if(formData.code_article=='')
    this.isValid=false;
    else if(formData.qte ==0)
    this.isValid=false;
    return this.isValid;
}
}
