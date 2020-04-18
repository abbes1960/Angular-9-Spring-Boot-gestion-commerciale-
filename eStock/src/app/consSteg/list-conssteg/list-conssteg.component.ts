import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConsstegService } from '../../service/conssteg.service';
import { Conssteg } from '../../model/conssteg';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-list-conssteg',
  templateUrl: './list-conssteg.component.html',
  styleUrls: ['./list-conssteg.component.scss']
})
export class ListConsstegComponent implements OnInit {
Liste;
  SearchText :string;
  constructor( private service :ConsstegService,private router:Router,
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
   this.router.navigate(['/commandes/modification/'+Id]);
  }

  removeData(id: number) {
    
  }

  onDelete(id:number){
  
}

onSelect(item :Conssteg){
  this.service.formData = this.fb.group(Object.assign({},item));
  
  this.router.navigate(['/csteg']);
}
transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
}

