


  import { Component, OnInit, Inject } from '@angular/core';
  import { Article } from '../../model/article';
  import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
  import { ArticleService } from '../../service/article.service';
  import { NgForm } from '@angular/forms';
  import { Llivr } from '../../model/llivr';
  import { LivrService } from '../../service/livr.service';
  import { LlivrService } from '../../service/llivr.service';
  import { ToastrService } from 'ngx-toastr';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  @Component({
    selector: 'app-add-ldevis',
    templateUrl: './add-ldevis.component.html',
    styleUrls: ['./add-ldevis.component.scss']
  })
  export class AddLdevisComponent implements OnInit {
     formData: FormGroup;
      articleList:Article[];
      isValid:boolean=true;
      wtotht = 0;
      wtotrem = 0;
      wtotfodec :number  = 0;
      wtottva = 0;
      wtotttc = 0;
      constructor( public service:LlivrService,private toastr :ToastrService,
            @Inject(MAT_DIALOG_DATA)  public data,
            public dialogRef:MatDialogRef<AddLdevisComponent>,
            private articleService:ArticleService,
            private livrService:LivrService,public fb: FormBuilder){}
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
    
    validateForm(formData:Llivr){
      this.isValid=true;
      if(formData.code =='')
        this.isValid=false;
        else if(formData.qte ==0)
        this.isValid=false;
        return this.isValid;
    }
    }
    