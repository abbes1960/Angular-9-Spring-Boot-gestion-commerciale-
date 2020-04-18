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
  selector: 'app-list-invent',
  templateUrl: './list-invent.component.html',
  styleUrls: ['./list-invent.component.scss']
})
export class ListInventComponent implements OnInit {
  commandeListe;
  SearchText :string;
  constructor( private service :CommandeService,private router:Router,
    private toastr :ToastrService,public fb: FormBuilder,
    private datePipe : DatePipe) { }

  ngOnInit() {
    
    this.refreshListe();
    
  }
refreshListe(){
  this.service.getAll().subscribe(
    response =>{this.commandeListe = response;}
   );

}

  openForEdit(Id:number){
   this.router.navigate(['/commandes/modification/'+Id]);
  }

  removeData(id: number) {
    
  }

  onCommandeDelete(id:number){
  
}

selectCommande(item :Commande){
  this.service.formData = this.fb.group(Object.assign({},item));
  
  this.router.navigate(['/commande']);
}
transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
}
