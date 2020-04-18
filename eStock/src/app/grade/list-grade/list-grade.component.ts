

  import { Component, OnInit,Inject } from '@angular/core';
  import { GradeService} from '../../service/grade.service';
  import { ToastrService } from 'ngx-toastr';
  import { Grade} from '../../model/grade';
  import { Observable } from "rxjs";
  import { Router } from '@angular/router';
  import { DatePipe } from '@angular/common';
  import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
  import { MatDialogRef } from "@angular/material/dialog";
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  import { MAT_DIALOG_DATA } from "@angular/material/dialog";
  import { AddGradeComponent } from '../../grade/add-grade/add-grade.component';
  @Component({
    selector: 'app-list-grade',
    templateUrl: './list-grade.component.html',
    styleUrls: ['./list-grade.component.scss']
  })
  export class ListGradeComponent implements OnInit {
    grade : Grade;
    control: FormControl = new FormControl('');
    constructor(public crudApi: GradeService, public toastr: ToastrService,
      private router : Router,public fb: FormBuilder,
      private matDialog: MatDialog,
      @Inject(MAT_DIALOG_DATA) public data: any,
      public dialogRef:MatDialogRef<AddGradeComponent>,) { }
   
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
      this.matDialog.open(AddGradeComponent, dialogConfig);
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
    selectData(item : Grade) {
      this.crudApi.choixmenu = "M";
      this.crudApi.dataForm = this.fb.group(Object.assign({},item));
      const dialogConfig = new MatDialogConfig();
      dialogConfig.autoFocus = true;
      dialogConfig.disableClose = true;
      dialogConfig.width="50%";
      
      this.matDialog.open(AddGradeComponent, dialogConfig);
    }
  }
  