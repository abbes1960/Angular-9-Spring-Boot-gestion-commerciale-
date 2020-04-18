import { Component, OnInit, Inject } from '@angular/core';
import { Residence } from '../../model/residence';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ResidenceService } from '../../service/residence.service';
import { NgForm } from '@angular/forms';
import { Lconssonede } from '../../model/lconssonede';
import { ConssonedeService } from '../../service/conssonede.service';
import { LconssonedeService } from '../../service/lconssonede.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-add-lconssonede',
  templateUrl: './add-lconssonede.component.html',
  styleUrls: ['./add-lconssonede.component.scss']
})
export class AddLconssonedeComponent implements OnInit {
  formData: Lconssonede;
  residenceList: Residence[];
  isValid:boolean=true;
  constructor( public service:LconssonedeService,private toastr :ToastrService,
        @Inject(MAT_DIALOG_DATA)  public data,
        public dialogRef:MatDialogRef<AddLconssonedeComponent>,
        public residenceService:ResidenceService,
        public conssonedeService: ConssonedeService,public fb: FormBuilder){}
        ngOnInit() {
          if(this.data.Index==null)
          {
            this.InfoForm();
          }
          else 
          {
            alert("gfgfghfjj");
            this.formData =Object.assign({},this.conssonedeService.list[this.data.Index])    
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
                
                this.formData.lib_residence =this.conssonedeService.listResidence[ctrl.selectedIndex - 1].libelle;
               this.formData.code_residence =this.conssonedeService.listResidence[ctrl.selectedIndex - 1].code;
                }  
            } 
        
        onSubmit(form:NgForm){
         
          if(this.validateForm(form.value)){
          if(this.data.Index==null)
           this.conssonedeService.list.push(form.value);
          else 
          this.conssonedeService.list[this.data.Index] = form.value;
          this.dialogRef.close();
             
        }
        }
        validateForm(formData:Lconssonede){
          this.isValid=true;
          if(formData.code_residence== 0)
            this.isValid=false;
            else if(formData.qte ==0)
            this.isValid=false;
            return this.isValid;
        }
}
