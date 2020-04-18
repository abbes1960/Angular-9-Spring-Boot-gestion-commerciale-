import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConssonedeService } from '../../service/conssonede.service';
import { Conssonede } from '../../model/conssonede';

import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';@Component({
  selector: 'app-list-conssonede',
  templateUrl: './list-conssonede.component.html',
  styleUrls: ['./list-conssonede.component.scss']
})
export class ListConssonedeComponent implements OnInit {
Liste;
  SearchText :string;
  constructor( private service :ConssonedeService,private router:Router,
    private toastr :ToastrService,public fb: FormBuilder ) { }

  ngOnInit() {
    
    this.refreshListe();
    
  }
refreshListe(){
  this.service.getAll().subscribe(
    response =>{this.Liste = response;}
   );

}

  openForEdit(Id:number){
   this.router.navigate(['/commandes/modification/'+Id]);
  }

  removeData(id: number) {
    
  }

  onDelete(id:number){
  
}

onSelect(item :Conssonede){
  this.service.formData = this.fb.group(Object.assign({},item));
  
  this.router.navigate(['/csonede']);
}

}

