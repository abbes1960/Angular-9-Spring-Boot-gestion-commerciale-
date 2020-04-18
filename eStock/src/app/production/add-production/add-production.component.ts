
import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { CompteurService}  from '../../service/compteur.service';
import { PrelevementService}     from '../../service/prelevement.service';
import { LprelevementService}    from '../../service/lprelevement.service';
import { AgentService}    from '../../service/agent.service';
import { Agent}        from '../../model/agent';
import { Compteur}         from '../../model/compteur';
import { Article}          from '../../model/article';
import { Prelevement}      from '../../model/prelevement';
import { Lprelevement}      from '../../model/lprelevement';
import { NgForm }          from '@angular/forms';
import { DatePipe }        from '@angular/common';
import { ToastrService }   from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { AddLpreleveComponent } from '../../prelevement/add-lpreleve/add-lpreleve.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
@Component({
  selector: 'app-add-production',
  templateUrl: './add-production.component.html',
  styleUrls: ['./add-production.component.scss']
})
export class AddProductionComponent implements OnInit {
  List: Agent[];
  isValid:boolean = true;
  articleService: any;
  minDate ;
 
  compteur : any={};
  agent: any ={};
  constructor(public service:PrelevementService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lprelevementservice: LprelevementService,
    public agentService : AgentService,
    
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
  ngOnInit() {
   this.minDate = this.transformDate(new Date());
   this.OnSelectCompteur(1);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.agentService.getAll().subscribe(
      response =>{this.List = response;}
     );
   }
  }


  OnSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numprel);
      }
   );  
 } 
   // this.service.getData(parseInt(Id)).then(res=> {
   // this.service.formData =res.commande;
  //  this.service.lcommande =res.commandeDetails;
  //  });
    
InfoForm() {
    this.service.formData = this.fb.group({
      id :null,
      annee : 0,
      numero : 0,
      date_mvt : '',
      mat : 0,
      nom : '',
      total : 0,
      });
    } 
  
  ResetForm() {
      this.service.formData.reset();
  }
  
  AddData(lcommandeIndex,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lcommandeIndex,Id};
    this.dialog.open(AddLpreleveComponent , dialogConfig).afterClosed().subscribe(b10=>{
      this.Calcul();
    });
  }

  onDelete(item : Lprelevement,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.Calcul();
   }

  Calcul(){
    this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.total;
    }, 0));
  }

  validateForm(){
     this.isValid = true ;
     if(this.service.formData.value.mat==0)
     this.isValid =false;
      else if (this.service.list.length==0)
     this.isValid =false;
     return this.isValid;
   }

  onSubmit(){
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/commandeliste']);
      });
    }

  rechAgent(mat : number)
  {
    this.agentService.getData(mat).subscribe(
      response =>{
        this.agent = response;
        this.f['nom'].setValue(this.agent.nom);
        }
     );  
  
  }
  
  transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
 
}





