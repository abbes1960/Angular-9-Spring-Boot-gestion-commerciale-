import { Component, OnInit } from '@angular/core';
import { ConscarburantService} from '../../service/consCarburant.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Router } from '@angular/router';
import { Conscarburant} from '../../model/consCarburant';
import { Compteur} from '../../model/compteur';
import { CompteurService}  from '../../service/compteur.service';
@Component({
  selector: 'app-add-consommation',
  templateUrl: './add-consommation.component.html',
  styleUrls: ['./add-consommation.component.scss']
})
export class AddConsommationComponent implements OnInit {
  constructor(public crudApi: ConscarburantService ,public fb: FormBuilder,public toastr: ToastrService,
    private router : Router,public compteurservice:CompteurService,) { }
    annee = 0;
    mat  = 0;
    code_dir = 0;
    lib_direction = '';
    compteur : any={};
    get f() { return this.crudApi.formData.controls; }

  ngOnInit() {
    if (this.crudApi.formData.value.id  == null)
    {this.infoForm()};
    this.annee = parseInt(localStorage.getItem('annee'));
    this.f['mat'].setValue(localStorage.getItem('mat'));
    this.f['code_dir'].setValue(localStorage.getItem('code_dir'));
    this.f['lib_direction'].setValue(localStorage.getItem('lib_direction'));
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
  
  infoForm() {
    this.crudApi.formData = this.fb.group({
        id: null,
        annee: [0, [Validators.required]],
        mois: [0, [Validators.required]],
        numero : [0, [Validators.required]],
        libelle: ['', [Validators.required]],
        code_direction : [0, [Validators.required]],
        lib_direction: ['', [Validators.required]],
        mat : [0, [Validators.required]],
        qte1: [0, [Validators.required]],
        qte2: [0, [Validators.required]],
        qte3: [0, [Validators.required]],
        qte4: [0, [Validators.required]],
       });
    }
   
  

  ResetForm() {
      this.crudApi.formData.reset();
  }

  onSubmit() {
    if (this.crudApi.formData.value.id  == null)
    {
      this.addData();
    }
    else
    {
      
     this.updateData()
    }
   
}
  
   

addData() {
  this.crudApi.createData(this.crudApi.formData.value).
  subscribe( data => {
    this.toastr.success( 'Validation Faite avec Success'); 
    this.router.navigate(['/conscarburant']);
  });
}
  updateData()
  {
    this.crudApi.updatedata(this.crudApi.formData.value.id,this.crudApi.formData.value).
    subscribe( data => {
      this.toastr.success( 'Modification Faite avec Success');

      this.router.navigate(['/lconscarburant']);
    });
  }
    
}

