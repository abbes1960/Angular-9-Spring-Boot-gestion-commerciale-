import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { DirectionService} from '../../service/direction.service';
import { ResidenceService} from '../../service/residence.service';
import { CompteurService}  from '../../service/compteur.service';
import { B1016Service}     from '../../service/b1016.service';
import { Lb1016Service}    from '../../service/lb1016.service';
import { Direction}        from '../../model/direction';
import { Residence}        from '../../model/residence';
import { Compteur}         from '../../model/compteur';
import { Article}          from '../../model/article';
import { B1016}            from '../../model/b1016';
import { Lb1016}           from '../../model/lb1016';
import { NgForm }          from '@angular/forms';
import { DatePipe }        from '@angular/common';
import { ToastrService }   from 'ngx-toastr';
import { Router, ActivatedRoute  } from '@angular/router';
import { AddLb1016Component } from '../../b1016/add-lb1016/add-lb1016.component';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Observable } from "rxjs";
@Component({
  selector: 'app-add-b1016',
  templateUrl: './add-b1016.component.html',
  styleUrls: ['./add-b1016.component.scss']
})
export class AddB1016Component implements OnInit {
  directionList: Direction[];
  residenceList: Residence[];
  isValid:boolean = true;
  direction : any;
  minDate ;
  compteur : any={};
  residence : any={};
  annee = 0;
  constructor(public service:B1016Service,
    public compteurservice:CompteurService,
    private dialog:MatDialog,public fb: FormBuilder,
    public lb1016service: Lb1016Service,
    public directionService :DirectionService,
    public residenceService :ResidenceService,
    private toastr :ToastrService,
    private router :Router,
    private currentRoute: ActivatedRoute,
    private datePipe : DatePipe) { }
    get f() { return this.service.formData.controls; }
  ngOnInit() {
   this.minDate = this.transformDate(new Date());
    let id =this.currentRoute.snapshot.paramMap.get('id');
 //   if (this.service.formData.value.id == null){
      this.InfoForm();
      this.directionService.getAll().subscribe(
      response =>{this.directionList = response;}
     );
     

 //  }
   this.f['annee'].setValue(2020);
   this.annee = parseInt(localStorage.getItem('annee'));
     this.onSelectCompteur(this.annee);
  }

  onSelectCompteur(id: number)
  {
    this.compteurservice.getData(id).subscribe(
     response =>{
       this.compteur = response;
       this.f['numero'].setValue(20200000 + this.compteur.numbon);
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
      annee : [0, [Validators.required]],
      numero : [0, [Validators.required]],
      date_mvt : '',
      code_dir : [0, [Validators.required]],
      code_res : [0, [Validators.required]],
      libelle : ['', [Validators.required]],
      total : [0, [Validators.required]],
      lib_direction : ['', [Validators.required]],
      lib_residence : ['', [Validators.required]],
      lb1016s :[],
      });
    } 
  
  ResetForm() {
      this.service.formData.reset();
  }
  
  AddData(lb1016Index,Id){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data={lb1016Index,Id};
    this.dialog.open(AddLb1016Component , dialogConfig).afterClosed().subscribe(b10=>{
      this.calcul();
    });
  }

  onDelete(item : Lb1016,Id:number,i:number){
    if(Id != null)
    this.service.formData.value.id+=Id ;
   this.service.list.splice(i,1);
   this.calcul();
   }

  calcul(){
    this.f['total'].setValue(this.service.list.reduce((prev, curr) => {
      return prev + curr.total;
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
    this.f['lb1016s'].setValue(this.service.list);
      this.service.saveOrUpdate(this.service.formData.value).
      subscribe( data => {
        this.toastr.success( 'Validation Faite avec Success'); 
        this.router.navigate(['/commandeliste']);
      });
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
            response =>{this.residenceList = response;}
           );  
       }
     }

  
  onSelectRes(ctrl)
      {
        if(ctrl.selectedIndex == 0){
          this.f['lib_residence'].setValue('');
         }
         else{
            this.f['lib_residence'].setValue(this.residenceList[ctrl.selectedIndex - 1].libelle);
            this.f['code_res'].setValue(this.residenceList[ctrl.selectedIndex - 1].code);
          }  
      } 
  transformDate(date){
     return this.datePipe.transform(date, 'yyyy-MM-dd');
   }
}
