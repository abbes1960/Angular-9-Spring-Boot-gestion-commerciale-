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
  selector: 'app-list-comm',
  templateUrl: './list-comm.component.html',
  styleUrls: ['./list-comm.component.scss']
})
export class ListCommComponent implements OnInit {
  list : Commande[];
  SearchText :string;
  constructor( private service :CommandeService,private router:Router,
    private toastr :ToastrService,public fb: FormBuilder,
    private datePipe : DatePipe) { }

  ngOnInit() {
    
    this.refreshListe();
    
  }
refreshListe(){
  this.service.getAll().subscribe(
    response =>{this.list = response;}
   );

}


  onDelete(id: number) {
   
    if (window.confirm('Are sure you want to delete this Article ?')) {
      this.service.deleteAll(id)
        .subscribe(
          data => {
            console.log(data);
            this.toastr.warning(' data successfully deleted!'); 
            this.refreshListe();
          },
          error => console.log(error));
    }
  }
newComm()
  {
    this.service.choixmenu =1
  this.router.navigate(['/comm']);
  }

onSelect(item :Commande){
  this.service.formData = this.fb.group(Object.assign({},item));
  this.service.choixmenu =2
  this.router.navigate(['/comm']);
}
transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
}

