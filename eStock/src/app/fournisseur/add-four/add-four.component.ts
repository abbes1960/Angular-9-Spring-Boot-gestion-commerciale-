import { Component, OnInit } from '@angular/core';
import { FournisseurService} from '../../service/fournisseur.service'
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';
import { Client} from '../../model/Client';

@Component({
  selector: 'app-add-four',
  templateUrl: './add-four.component.html',
  styleUrls: ['./add-four.component.scss']
})
export class AddFourComponent implements OnInit {

  constructor(public crudApi: FournisseurService ,public fb: FormBuilder,public toastr: ToastrService,
    private router : Router) { }

  ngOnInit() {
  
    if (this.crudApi.choixmenu == "A")
    {this.infoForm()};
   }
  infoForm() {
    this.crudApi.dataForm = this.fb.group({
        id: null,
        libelle: ['', [Validators.required]],
        adresse: ['', [Validators.required, Validators.minLength(5)]],
        tel: ['', [Validators.required, Validators.minLength(8)]],
        email: ['', [Validators.required, Validators.minLength(10)]],
        fax: ['', [Validators.required, Validators.minLength(8)]],
        login: ['', [Validators.required, Validators.minLength(8)]],
        pwd: ['', [Validators.required, Validators.minLength(8)]],
       
    
        });
    }
   
  

  ResetForm() {
      this.crudApi.dataForm.reset();
  }
  onSubmit() {
    if (this.crudApi.choixmenu == "A")
    {
      this.addData();
    }
    else
    {
      
     this.updateData()
    }
   
}
  
   

addData() {
  this.crudApi.createData(this.crudApi.dataForm.value).
  subscribe( data => {
    this.toastr.success( 'Validation Faite avec Success'); 
    this.router.navigate(['/clients']);
  });
}
  updateData()
  {
  
    this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
    subscribe( data => {
      this.toastr.success( 'Modification Faite avec Success');

      this.router.navigate(['/clients']);
    });
  }
}
