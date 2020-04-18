import { Component, OnInit, Inject } from '@angular/core';
import { Residence } from '../../model/residence';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ResidenceService } from '../../service/residence.service';
import { NgForm } from '@angular/forms';
import { Lconssteg } from '../../model/lconssteg';
import { ConsstegService } from '../../service/conssteg.service';
import { LconsstegService } from '../../service/lconssteg.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-add-lconssteg',
  templateUrl: './add-lconssteg.component.html',
  styleUrls: ['./add-lconssteg.component.scss']
})
export class AddLconsstegComponent implements OnInit {
  formData: Lconssteg;
  isValid:boolean=true;
  constructor( public service:LconsstegService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLconsstegComponent>,
        public residenceService:ResidenceService,
        public consstegService:ConsstegService,public fb: FormBuilder){}
  ngOnInit() {
    if(this.data.Index==null)
    {
       this.InfoForm();
    }
    else 
    {
      alert("gfgfghfjj");
      this.formData =Object.assign({},this.consstegService.list[this.data.Index])    
    }    
}


InfoForm() {
  this.formData =  {
      id: null,
      numero : 0,
      qte : 0,
      lib_residence :'',
      code_residence : 0,
   };
  } 

  OnSelectRes(ctrl)
      {
        if(ctrl.selectedIndex == 0){
          this.formData.lib_residence = '';
         }
         else{
         this.formData.lib_residence =this.consstegService.listResidence[ctrl.selectedIndex - 1].libelle;
         this.formData.code_residence =this.consstegService.listResidence[ctrl.selectedIndex - 1].code;
          }  
      } 
  
  onSubmit(form:NgForm){
    if(this.validateForm(form.value)){
    if(this.data.Index==null)
     this.consstegService.list.push(form.value);
    else 
    this.consstegService.list[this.data.Index] = form.value;
    this.dialogRef.close();
       
  }
  }
  validateForm(formData:Lconssteg){
    this.isValid=true;
    if(formData.code_residence== 0)
      this.isValid=false;
      else if(formData.qte ==0)
      this.isValid=false;
      return this.isValid;
  }
}
