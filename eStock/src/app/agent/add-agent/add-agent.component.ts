  import { Component, OnInit,Inject } from '@angular/core';
  import { GradeService} from '../../service/grade.service';
  import { AgentService} from '../../service/agent.service';
  import { ToastrService } from 'ngx-toastr';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  import { Router } from '@angular/router';
  import { Agent} from '../../model/agent';
  import { Grade} from '../../model/grade';
  import { MAT_DIALOG_DATA,MatDialogRef } from "@angular/material/dialog";
  import { HttpResponse, HttpEventType } from '@angular/common/http';
  
  @Component({
    selector: 'app-add-agent',
    templateUrl: './add-agent.component.html',
    styleUrls: ['./add-agent.component.scss']
  })
  export class AddAgentComponent implements OnInit {
    List: Grade[];
    grade : any={};
    
    constructor(public crudApi: AgentService ,public fb: FormBuilder,public toastr: ToastrService,
      
      public gradeService: GradeService,
      private router : Router,@Inject(MAT_DIALOG_DATA)  public data,
      public dialogRef:MatDialogRef<AddAgentComponent>,
      
      ) { }
      get f() { return this.crudApi.formData.controls; }
    ngOnInit() {
     if (this.crudApi.choixmenu == "A")
      {this.infoForm()};
      this.gradeService.getAll().subscribe(
        response =>{this.List = response;}
       );
     }
    
    infoForm() {
      this.crudApi.formData = this.fb.group({
          id: null,
          mat: ['', [Validators.required]],
          nom: ['', [Validators.required]],
          code_grade: ['', [Validators.required]],
         
        });
      }
  
    ResetForm() {
        this.crudApi.formData.reset();
    }
    onSubmit() {
      if (this.crudApi.choixmenu == "A")
      {
        this.addData();
      }
      else
      {
      this.updateData()
      }   
  }
    
  OnSelectGrade(code: number)
{
  this.gradeService.getData(code).subscribe(
    response =>{
      this.grade= response;
      this.f['lib_grade'].setValue(this.grade.libelle);
      }
   );   
} 
  
  
  addData() {
    this.crudApi.createData(this.crudApi.formData.value).
    subscribe( data => {
      
      this.dialogRef.close();
      this.crudApi.getAll().subscribe(
        response =>{this.crudApi.list = response;}
       );
      this.router.navigate(['/agents']); 
    });
  }
    updateData()
    {
      this.crudApi.updatedata(this.crudApi.formData.value.id,this.crudApi.formData.value).
      subscribe( data => {
        this.dialogRef.close();
       
        this.crudApi.getAll().subscribe(
          response =>{this.crudApi.list = response;}
         );
        this.router.navigate(['/agents']); 
      });
    }
  
   
  
  
  }
