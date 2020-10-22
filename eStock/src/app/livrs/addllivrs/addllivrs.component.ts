

  import { Component, OnInit, Inject } from '@angular/core';
  import { Article } from '../../model/article';
  import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
  import { ArticleService } from '../../service/article.service';
  import { NgForm } from '@angular/forms';
  import { Llivrs } from '../../model/llivrs';
  import { LivrsService } from '../../service/livrs.service';
  import { LlivrsService } from '../../service/llivrs.service';
  import { ToastrService } from 'ngx-toastr';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  @Component({
    selector: 'app-addllivrs',
    templateUrl: './addllivrs.component.html',
    styleUrls: ['./addllivrs.component.scss']
  })
  export class AddllivrsComponent implements OnInit {
     formData: FormGroup;
      articleList:Article[];
      isValid:boolean=true;
      wtotht = 0;
      wtotrem = 0;
      wtotfodec :number  = 0;
      wtottva = 0;
      wtotttc = 0;
      constructor( public service:LlivrsService,private toastr :ToastrService,
            @Inject(MAT_DIALOG_DATA)  public data,
            public dialogRef:MatDialogRef<AddllivrsComponent>,
            private articleService:ArticleService,
            private livrService:LivrsService,public fb: FormBuilder){}
            get f() { return this.formData.controls; }
           
    
      ngOnInit() {
        if(this.data.llivrIndex==null)
        {
          this.InfoForm();
        }
        else 
        {
         this.formData =this.fb.group(Object.assign({},this.livrService.list[this.data.llivrIndex]));
        }
       this.articleService.getAll().subscribe(
          response =>{this.articleList= response;}
         );
    }
    
    
    InfoForm() {
      this.formData = this.fb.group({
          id: null,
          numero :this.data.numero,
          ligne  : 0,
          qte : 0,
          pu : 0,
          tva : 0,
          rem : 0,
          fodec : 0,
          totht : 0,
          totrem : 0,
          totfodec :0,
          tottva :0,
          totttc :0,
          libart :'',
          code :'',
         
        });
      } 
    
    
    
    
    cal(){
      this.wtotht =  parseFloat((this.formData.value.qte * this.formData.value.pu).toFixed(3));
      this.wtotrem =  parseFloat((this.wtotht * this.formData.value.rem *0.01).toFixed(3));
      this.wtotfodec = this.wtotht - this.wtotrem;
      this.wtotfodec = parseFloat((this.wtotfodec * this.formData.value.fodec *0.01).toFixed(3));
      this.wtottva = this.wtotht + this.wtotfodec - this.wtotrem;
      this.wtottva = parseFloat(((this.wtottva * this.formData.value.tva)*0.01).toFixed(3)); 
      this.wtotttc = parseFloat((this.wtotht -  this.wtotrem+ this.wtotfodec +this.wtottva).toFixed(3));
      this.f['totht'].setValue(this.wtotht);
      this.f['totrem'].setValue(this.wtotrem);
      this.f['totfodec'].setValue(this.wtotfodec);
      this.f['tottva'].setValue(this.wtottva);
      this.f['totttc'].setValue(this.wtotttc);
    }
    
    onSubmit() {
      if(this.data.llivrIndex==null)
      {
        this.livrService.list.push(this.formData.value)
        this.dialogRef.close();
      }
      else
    {
      
      this.livrService.list[this.data.llivrIndex] = this.formData.value;
    }
    this.dialogRef.close();
    
     
    }
    
    validateForm(formData:Llivrs){
      this.isValid=true;
      if(formData.ligne ==0)
        this.isValid=false;
        else if(formData.qte ==0)
        this.isValid=false;
        return this.isValid;
    }
    }
    

