import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { B1016Service } from '../../service/b1016.service';
import { B1016 } from '../../model/b1016';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-list-b1016',
  templateUrl: './list-b1016.component.html',
  styleUrls: ['./list-b1016.component.scss']
})
export class ListB1016Component implements OnInit {
  Liste;
  SearchText :string;
  constructor( private service :B1016Service,private router:Router,
    private toastr :ToastrService,public fb: FormBuilder,
    private datePipe : DatePipe) { }

  ngOnInit() {
    
    this.refreshListe();
    
  }
refreshListe(){
  this.service.getAll().subscribe(
    response =>{this.Liste = response;}
   );

}

  openForEdit(Id:number){
   this.router.navigate(['/b1016/modification/'+Id]);
  }

  removeData(id: number) {
    
 }

onDelete(id:number){
  
}

selectData(item :B1016){
  this.service.formData = this.fb.group(Object.assign({},item));
  
  this.router.navigate(['/b1016']);
}
transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
}
