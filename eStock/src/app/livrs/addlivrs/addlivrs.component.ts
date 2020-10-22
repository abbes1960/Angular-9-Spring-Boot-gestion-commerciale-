

  import { Component, OnInit } from '@angular/core';
  import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
  import { ClientService} from '../../service/client.service';
  import { CompteurService} from '../../service/compteur.service';
  import { Client} from '../../model/client';
  import { Compteur} from '../../model/compteur';
  import { ToastrService } from 'ngx-toastr';
  import { Router, ActivatedRoute  } from '@angular/router';
  import { NgForm } from '@angular/forms';
  import { Livrs } from '../../model/livrs';
  import { LivrsService} from '../../service/livrs.service';
  import { LlivrsService} from '../../service/llivrs.service'
  import { DatePipe } from '@angular/common';
  import { AddllivrsComponent } from '../../livrs/addllivrs/addllivrs.component';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  import { Observable } from "rxjs";
  import { Article} from '../../model/article';
  import { Llivr} from '../../model/llivr';
  import pdfMake from 'pdfmake/build/pdfmake';
  import { map } from 'rxjs/operators';
  import pdfFonts from 'pdfmake/build/vfs_fonts';
  pdfMake.vfs = pdfFonts.pdfMake.vfs;
  @Component({
    selector: 'app-addlivrs',
    templateUrl: './addlivrs.component.html',
    styleUrls: ['./addlivrs.component.scss']
  })
  export class AddlivrsComponent implements OnInit {
    p: number = 1;
    ClientList: Client[];
    isValid:boolean = true;
    articleService: any;
    compteur : any={};
    client   : any= {};
    livrs    : any={};
    annee  = 0;
    constructor(public service:LivrsService,
      public compteurservice:CompteurService,
      private dialog:MatDialog,public fb: FormBuilder,
      public clientService :ClientService,
      private toastr :ToastrService,
      public llivrService : LlivrsService,
      private router :Router,
      private currentRoute: ActivatedRoute,
      private datePipe : DatePipe) { }
      get f() { return this.service.formData.controls; }
  
      ngOnInit() {
        this.service.list = [];
        if (this.service.choixmenu == 1){
          this.InfoForm();
          this.f['annee'].setValue(parseInt(localStorage.getItem('annee')));
          this.annee = parseInt(localStorage.getItem('annee'));
          this.service.list = [];
          this.onSelectCompteur(this.annee);
              this.f['date_mvt'].setValue(this.transformDate(new Date()));
        }
                else
              {
               this.service.getData(this.service.formData.value.id).subscribe(
                  response =>{
                    this.livrs = response;
                    this.service.list = this.livrs.llivrs;
                    this.calcul();
                    },
                    error => 
                      this.toastr.warning( 'Error '))
        }
        this.clientService.getAll().subscribe(
          response =>{this.ClientList = response;}
         );
      }
  
  onSelectCompteur(id: number)
   {
    this.compteurservice.getData(id).subscribe(
      response =>{
        this.compteur = response;
        this.f['numero'].setValue(20200000 + this.compteur.numbl);
        }
     );  
   } 
     
      
  InfoForm() {
   
      this.service.formData = this.fb.group({
        id :null,
        annee : 0,
        numero : 0,
        date_mvt : '',
        code : 0,
        libcl: '',
        smtva : '',
        chauffeur: '',
        camion : '',
        totht : 0,
        totrem : 0,
        totfodec : 0,
        tottva : 0,
        totttc : 0,
        numfac : 0,
        llivrs :[],
        });
      } 
    
  ResetForm() {
        this.service.formData.reset();
    }
    
  AddData(llivrIndex,Id){  
      const dialogConfig = new MatDialogConfig();
      dialogConfig.autoFocus = true;
      dialogConfig.disableClose = true;
      dialogConfig.width="50%";
      dialogConfig.data={llivrIndex,Id};
      this.dialog.open(AddllivrsComponent, dialogConfig).afterClosed().subscribe(b10=>{
        this.calcul();
      });
    }
  
    
  onDelete(item : Llivr,Id:number,i:number){
      if(Id != null)
      this.service.formData.value.id+=Id ;
     this.service.list.splice(i,1);
     this.calcul();
     }
  
  calcul(){
    this.f['totht'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totht;
    }, 0));
    this.f['totrem'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totrem;
    }, 0));
    this.f['totfodec'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totfodec;
    }, 0));
    this.f['tottva'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.tottva;
    }, 0));
    this.f['totttc'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totttc;
    }, 0));   
     
     }
  validateForm(){
       this.isValid = true ;
      
       if(this.service.formData.value.id_client==0)
       this.isValid =false;
      
       else if (this.service.list.length==0)
       this.isValid =false;
       return this.isValid;
     }
  
  onSubmit(){
    
     this.f['llivrs'].setValue(this.service.list);
        this.service.saveOrUpdate(this.service.formData.value).
        subscribe( data => {
          this.toastr.success( 'Validation Faite avec Success'); 
          this.router.navigate(['/llivr']);
        });
     }
    
  transformDate(date){
       return this.datePipe.transform(date, 'yyyy-MM-dd');
     }
  OnSelectClient(code :number)
     {
      this.clientService.getData(code).subscribe(
        response =>{this.client = response;
     
            this.f['libcl'].setValue(this.client.libelle);
           this.f['code'].setValue(this.client.code);
           this.f['smtva'].setValue(this.client.smtva);
         error => 
            
        this.toastr.warning( 'Code Incorrecte ')
        }
      
      );
      }
      
      generatePdf(id : number) {
        const document = this.service.getDocument(id);
        pdfMake.createPdf(document).open(); 
       }
  }
  
  
