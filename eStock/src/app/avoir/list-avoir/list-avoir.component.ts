import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommandeService } from '../../service/commande.service';
import { Commande } from '../../model/commande';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Component({
  selector: 'app-list-avoir',
  templateUrl: './list-avoir.component.html',
  styleUrls: ['./list-avoir.component.scss']
})
export class ListAvoirComponent implements OnInit {
  avoirListe;
  SearchText :string;
  constructor( private service :CommandeService,private router:Router,
    private toastr :ToastrService,public fb: FormBuilder,
    private datePipe : DatePipe) { }

  ngOnInit() {
    
    this.refreshListe();
    
  }
refreshListe(){
  this.service.getAll().subscribe(
    response =>{this.avoirListe = response;}
   );

}

  openForEdit(Id:number){
   this.router.navigate(['/commandes/modification/'+Id]);
  }

  removeData(id: number) {
    
  }

  onDelete(id:number){
  
}

selectData(item :Commande){
  this.service.formData = this.fb.group(Object.assign({},item));
  
  this.router.navigate(['/avoir']);
}
transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
  }


