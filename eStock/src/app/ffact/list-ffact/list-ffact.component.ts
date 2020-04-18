import { Component, OnInit,Inject } from '@angular/core';
import { FactfService} from '../../service/factf.service';
import { ToastrService } from 'ngx-toastr';
import { Factf} from '../../model/factf';
import { Fournisseur} from '../../model/fournisseur';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { AddFfactComponent } from '../../ffact/add-ffact/add-ffact.component';

@Component({
  selector: 'app-list-ffact',
  templateUrl: './list-ffact.component.html',
  styleUrls: ['./list-ffact.component.scss']
})
export class ListFfactComponent implements OnInit {
  fournisseur  : Fournisseur;
  control: FormControl = new FormControl('');
  constructor(public crudApi: FactfService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddFfactComponent >,) { }
 
  ngOnInit() {
    
    this.GetData();
  }
  addFact()
  {
    this.crudApi.choixmenu = "A";
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AddFfactComponent , dialogConfig);
  }
GetData() {
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.list = response;}
     );
   
  }
  
 
RemoveData(id: number) {
    if (window.confirm('Are sure you want to delete this Article ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.warning(' data successfully deleted!'); 
          this.GetData();
        },
        error => console.log(error));
  }
  }
  SelectData(item : Factf) {
    this.crudApi.choixmenu = "M";
    this.crudApi.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    
    this.matDialog.open(AddFfactComponent , dialogConfig);
  }
}
