import { Component, OnInit } from '@angular/core';
  import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
  import { ClientService} from '../../service/client.service';
  import { CompteurService} from '../../service/compteur.service';
  import { Client} from '../../model/client';
  import { Direction} from '../../model/direction';
  import { Compteur} from '../../model/compteur';
  import { ToastrService } from 'ngx-toastr';
  import { Router, ActivatedRoute  } from '@angular/router';
  import { NgForm } from '@angular/forms';
  import { Commser } from '../../model/commser';
  import { CommserService} from '../../service/commser.service';
  import { DatePipe } from '@angular/common';
  import { AddLcommsComponent } from '../../comms/add-lcomms/add-lcomms.component';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  import { Observable } from "rxjs";
  import { Article} from '../../model/article';
  import { Lcommser} from '../../model/lcommser';
@Component({
  selector: 'app-add-comms',
  templateUrl: './add-comms.component.html',
  styleUrls: ['./add-comms.component.scss']
})
export class AddCommsComponent implements OnInit {
 ClientList: Client[];
    
    isValid:boolean = true;
    articleService: any;
    minDate ;
    Wdate;
    numcomm :number;
    compteur : any={};
    client   : any= {};
    annee  = 0;
    constructor(public service:CommserService,
      public compteurservice:CompteurService,
      private dialog:MatDialog,public fb: FormBuilder,
      public clientService :ClientService,
      private toastr :ToastrService,
      private router :Router,
      private currentRoute: ActivatedRoute,
      private datePipe : DatePipe) { }
      get f() { return this.service.formData.controls; }
  
  ngOnInit() {
     this.minDate = this.transformDate(new Date());
     this.annee = parseInt(localStorage.getItem('annee'));
     this.onSelectCompteur(this.annee);
     this.InfoForm();
     this.f['annee'].setValue(2020);
     this.Wdate = this.transformDate(new Date());
  this.service.list = [];
     //let id =this.currentRoute.snapshot.paramMap.get('id');
    //  if (this.service.formData.value.id == null){
      
       
        this.clientService.getAll().subscribe(
        response =>{this.ClientList = response;}
       );
    // }
    }
  
  
  onSelectCompteur(id: number)
   {
    this.compteurservice.getData(id).subscribe(
      response =>{
        this.compteur = response;
        this.f['numero'].setValue(20200000 + this.compteur.numcomms);
        }
     );  
   } 
     
      
  InfoForm() {
   
      this.service.formData = this.fb.group({
        id :null,
        annee : 0,
        numero : 0,
        date_comm : '',
        code_client : 0,
        lib_client : '',
        libelle : '',
        totht : 0,
        tottva : 0,
        totttc : 0,
        lcommServices :[],
        });
      } 
    
  ResetForm() {
        this.service.formData.reset();
    }
  AddData(Index,Id){  
      const dialogConfig = new MatDialogConfig();
      dialogConfig.autoFocus = true;
      dialogConfig.disableClose = true;
      dialogConfig.width="50%";
      dialogConfig.data={Index,Id};
      this.dialog.open(AddLcommsComponent, dialogConfig).afterClosed().subscribe(b10=>{
        this.calcul();
      });
    }
  
    
  onDelete(item : Lcommser,Id:number,i:number){
      if(Id != null)
      this.service.formData.value.id+=Id ;
     this.service.list.splice(i,1);
     this.calcul();
     }
  
  calcul(){
    this.f['totht'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.totht;
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
    
     this.f['lcommServices'].setValue(this.service.list);
        this.service.saveOrUpdate(this.service.formData.value).
        subscribe( data => {
          this.toastr.success( 'Validation Faite avec Success'); 
          this.router.navigate(['/lcomms']);
        });
     }
    
  transformDate(date){
       return this.datePipe.transform(date, 'yyyy-MM-dd');
     }
  OnSelectClient(ctrl)
     {
        if(ctrl.selectedIndex == 0){
         this.f['lib_client'].setValue('');
        }
        else{
           this.f['lib_client'].setValue(this.ClientList[ctrl.selectedIndex - 1].libelle);
           this.f['code_client'].setValue(this.ClientList[ctrl.selectedIndex - 1].code);
        }
      }
  }
  