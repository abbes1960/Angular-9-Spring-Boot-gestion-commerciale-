import { Component, OnInit, Inject } from '@angular/core';
import { ConscarburantService} from '../../service/consCarburant.service';
import { ToastrService } from 'ngx-toastr';
import { Conscarburant} from '../../model/consCarburant';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AddConsommationComponent } from '../../consCarburant/add-consommation/add-consommation.component';

@Component({
  selector: 'app-list-consommation',
  templateUrl: './list-consommation.component.html',
  styleUrls: ['./list-consommation.component.scss']
})
export class ListConsommationComponent implements OnInit {
  liste : Conscarburant[];
  constructor(public crudApi: ConscarburantService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddConsommationComponent>,) { }
 
  ngOnInit() {
    this.getData();
  }
  
  getData() {
    this.crudApi.getAll().subscribe(
      response =>{this.liste = response;}
     );
   
  }
  
  addData()
  {
    this.crudApi.formData.value.id == null;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AddConsommationComponent, dialogConfig);
  }
  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Consommation ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.success(' data successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }
  }
  selectData(item : Conscarburant) {
    this.crudApi.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(AddConsommationComponent, dialogConfig);
  }
}

