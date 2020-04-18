import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LivrService } from '../../service/livr.service';
import { Livr } from '../../model/livr';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-listlivr',
  templateUrl: './listlivr.component.html',
  styleUrls: ['./listlivr.component.scss']
})
export class ListlivrComponent implements OnInit {
  Liste;
  SearchText :string;
  constructor( private service :LivrService,private router:Router,
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

  onCommandeDelete(id:number){
  
}

selectCommande(item : Livr){
  this.service.formData = this.fb.group(Object.assign({},item));
  this.router.navigate(['/livr']);
}
transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
}


