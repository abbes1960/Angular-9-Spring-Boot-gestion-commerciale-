import { Component, OnInit,Inject } from '@angular/core';
import { ScategorieService} from '../../service/scategorie.service';
import { ToastrService } from 'ngx-toastr';
import { Scategorie} from '../../model/Scategorie';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AddScategorieComponent } from '../../scategorie/add-scategorie/add-scategorie.component';


@Component({
  selector: 'app-list-scategorie',
  templateUrl: './list-scategorie.component.html',
  styleUrls: ['./list-scategorie.component.scss']
})
export class ListScategorieComponent implements OnInit {
  scategorie : Scategorie;
  control: FormControl = new FormControl('');
  constructor(public crudApi: ScategorieService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddScategorieComponent>,) { }
 
  ngOnInit() {
    
    this.getData();
  }
  addScategorie()
  {
 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AddScategorieComponent, dialogConfig);
  }
 
  

  
  getData() {
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.listData = response;}
     );
   
  }
  
  
  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Scatégorie ?')) {
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
  selectData(item : Scategorie) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    
    this.matDialog.open(AddScategorieComponent, dialogConfig);
  }

}
