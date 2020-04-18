

  import { Component, OnInit,Inject} from '@angular/core';
  import { GradeService} from '../../service/grade.service'
  import { ToastrService } from 'ngx-toastr';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  
  from '@angular/forms';
  import { Router } from '@angular/router';
  import { Categorie} from '../../model/Categorie';
  import { MAT_DIALOG_DATA } from "@angular/material/dialog";
  import { MatDialogRef } from "@angular/material/dialog";
  
  @Component({
    selector: 'app-add-grade',
    templateUrl: './add-grade.component.html',
    styleUrls: ['./add-grade.component.scss']
  })
  export class AddGradeComponent implements OnInit {
      constructor(public crudApi: GradeService ,public fb: FormBuilder,public toastr: ToastrService,
      private router : Router,@Inject(MAT_DIALOG_DATA)  public data,
      public dialogRef:MatDialogRef<AddGradeComponent>,
      ) { }
  
    ngOnInit() {
      if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
     }
  
  
    
    infoForm() {
      this.crudApi.dataForm = this.fb.group({
          id: null,
          code: ['', [Validators.required]],
          libelle: ['', [Validators.required]],
        });
      }
     
    
  
    ResetForm() {
        this.crudApi.dataForm.reset();
    }
    onSubmit() {
     
      if (this.crudApi.choixmenu == "A")
      {
        this.Add();
      }
      else
      {
       this.Update()
      }
     
  }
    
     
  
  Add() {
    this.crudApi.createData(this.crudApi.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
     
      this.crudApi.getAll().subscribe(
        response =>{this.crudApi.list = response;}
       );
      this.router.navigate(['/grades']); 
    });
  }
  Update()
    {
      this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
      subscribe( data => {
        this.dialogRef.close();
     
        this.crudApi.getAll().subscribe(
          response =>{this.crudApi.list = response;}
         );
        this.router.navigate(['/grades']);
      });
    }
  
  }
  
