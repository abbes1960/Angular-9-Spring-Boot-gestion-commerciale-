import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { DirectionService} from '../../service/direction.service';
import { ConsstegService}  from '../../service/conssteg.service';
import { LconsstegService}  from '../../service/lconssteg.service';
import { CompteurService}  from '../../service/compteur.service';
import { Direction}        from '../../model/direction';
import { Compteur}         from '../../model/compteur';
import { Conssteg }        from '../../model/conssteg';
import { Lconssteg }        from '../../model/lconssteg';
import { NgForm }          from '@angular/forms';
import { DatePipe }        from '@angular/common';
import { ToastrService }   from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { AddLconsstegComponent } from '../../consSteg/add-lconssteg/add-lconssteg.component';
import { ResidenceService } from '../../service/residence.service';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";

@Component({
  selector: 'app-add-conssteg',
  templateUrl: './add-conssteg.component.html',
  styleUrls: ['./add-conssteg.component.scss']
})
export class AddConsstegComponent implements OnInit {
  direction : any = {};
  directionList: Direction[];
 
  isValid:boolean = true;
  articleService: any;
  minDate ;
  numcomm :number;
  compteur : any={};
  lb1016: any;
  constructor(public service:ConsstegService,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lconssonedeservice: LconsstegService,
    public directionService :DirectionService,
    public residenceService :ResidenceService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
  ngOnInit() {
   this.minDate = this.transformDate(new Date());
   this.onSelectCompteur(2020);
   this.InfoForm();
    let id =this.currentRoute.snapshot.paramMap.get('id');
    if (this.service.formData.value.id == null){
      this.InfoForm();
      this.directionService.getAll().subscribe(
      response =>{this.directionList = response;}
     );
   }
  }
  OnSelectDir(ctrl)
  {
     if(ctrl.selectedIndex == 0){
      this.f['lib_direction'].setValue('');
     }
     else{
        this.f['lib_direction'].setValue(this.directionList[ctrl.selectedIndex - 1].libelle);
        this.f['code_dir'].setValue(this.directionList[ctrl.selectedIndex - 1].code);
        this.residenceService.listResidence(this.service.formData.value.code_dir).subscribe(
          response =>{this.service.listResidence = response;}
         );  
     }
   }

onSelectCompteur(id: number)
 {
  this.compteurservice.getData(id).subscribe(
    response =>{
      this.compteur = response;
      this.f['numero'].setValue(20200000 + this.compteur.numcsteg);
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
      mois : 0,
      numero : 0,
      code_direction : 0,
      lib_direction : '',
      libelle : '',
      total : 0,
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
    this.dialog.open(AddLconsstegComponent , dialogConfig).afterClosed().subscribe(b10=>{
      this.calcul();
    });
  }

  onDelete(item : Lconssteg,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

  calcul(){
    this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.qte;
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
    this.f['lconsstegs'].setValue(this.service.list);
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/lsteg']);
      });
    }

 

  transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
 
}
