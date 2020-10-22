import { Component, OnInit,Inject } from '@angular/core';
import { DepotService} from '../../service/depot.service';
import { ToastrService } from 'ngx-toastr';
import { Depot} from '../../model/depot';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AddDepotComponent } from '../../depot/add-depot/add-depot.component';

@Component({
  selector: 'app-list-depot',
  templateUrl: './list-depot.component.html',
  styleUrls: ['./list-depot.component.scss']
})
export class ListDepotComponent implements OnInit {
  depot : Depot;
  control: FormControl = new FormControl('');
  constructor(public crudApi: DepotService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddDepotComponent>,) { }
 
  ngOnInit() {
    
    this.getData();
  }
  addCategorie()
  {
 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AddDepotComponent, dialogConfig);
  }
 
  

  
  getData() {
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.list = response;}
     );
   
  }
  
 
  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Catégorie ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.warning(' data successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }
  }
  selectData(item : Depot) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    
    this.matDialog.open(AddDepotComponent, dialogConfig);
  }
}


