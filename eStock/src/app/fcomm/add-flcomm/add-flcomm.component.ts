import { Component, OnInit, Inject } from '@angular/core';
import { Article } from '../../model/article';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ArticleService } from '../../service/article.service';
import { NgForm } from '@angular/forms';
import { Lfcommande } from '../../model/lfcommande';
import { FcommandeService } from '../../service/fcommande.service';
import { LfcommandeService } from '../../service/lfcommande.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-add-flcomm',
  templateUrl: './add-flcomm.component.html',
  styleUrls: ['./add-flcomm.component.scss']
})
export class AddFlcommComponent implements OnInit {
  formData: FormGroup;
  articleList:Article[];
  isValid:boolean=true;
  constructor( public service:LfcommandeService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddFlcommComponent>,
        private articleService:ArticleService,
        private commandeService:FcommandeService,public fb: FormBuilder){}
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
    this.formData =this.fb.group(Object.assign({},this.commandeService.list[this.data.listIndex]));
    }


InfoForm() {
  this.formData = this.fb.group({
      Id: null,
      numero :this.data.numero,
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
  this.updateTotal();
}

updateTotal(){
  this.formData.value.totht = parseFloat((this.formData.value.qte * this.formData.value.pu).toFixed(3));
}

onSubmit() {
  if(this.validateForm(this.formData.value)){
  if(this.data.lcommandeIndex==null)
   this.commandeService.list.push(this.formData.value)
  }
  this.commandeService.list[this.data.listIndex] = this.formData.value;
  this.dialogRef.close();  
}

validateForm(formData:Lfcommande){
  this.isValid=true;
  if(formData.code_article=='')
    this.isValid=false;
    else if(formData.qte ==0)
    this.isValid=false;
    return this.isValid;
} 

}
