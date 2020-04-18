import { Component, OnInit,Inject } from '@angular/core';
import { FournisseurService} from '../../service/fournisseur.service';
import { FactfService} from '../../service/factf.service';
import { TypefacService} from '../../service/typefac.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { DatePipe }           from '@angular/common';
import { Router } from '@angular/router';
import { Factf} from '../../model/factf';
import { Fournisseur} from '../../model/fournisseur';
import { Typefac} from '../../model/typefac';
import { MAT_DIALOG_DATA,MatDialogRef } from "@angular/material/dialog";
import { HttpResponse, HttpEventType } from '@angular/common/http';
@Component({
  selector: 'app-add-ffact',
  templateUrl: './add-ffact.component.html',
  styleUrls: ['./add-ffact.component.scss']
})
export class AddFfactComponent implements OnInit {
  fourList: Fournisseur[];
  typefList: Typefac[];
  minDate;
  fournisseur : any={};
    constructor(public crudApi: FactfService ,public fb: FormBuilder,public toastr: ToastrService,
    public typefacService: TypefacService,
    public fourService: FournisseurService,
    private router : Router,@Inject(MAT_DIALOG_DATA)  public data,
    private datePipe : DatePipe,
    public dialogRef:MatDialogRef<AddFfactComponent>,
    ) { }
    get f() { return this.crudApi.formData.controls; }
  ngOnInit() {
    this.minDate = this.transformDate(new Date());
   if (this.crudApi.choixmenu == "A")
    {this.infoForm()};
    this.fourService.getAll().subscribe(
      response =>{this.fourList = response;}
     );
     this.typefacService.getAll().subscribe(
      response =>{this.typefList = response;}
     );
   }
  
  infoForm() {
    this.crudApi.formData = this.fb.group({
        id: null,
        date_mvt :['', [Validators.required]],
        numero   : [0, [Validators.required]],
        annee    : [0, [Validators.required]],
        numfac     : ['', [Validators.required]],
        code_four : [0, [Validators.required]],
        lib_four : ['', [Validators.required]],
        date_reg :['', [Validators.required]],
        type_fact : ['', [Validators.required]],
        libelle : ['', [Validators.required]],
        totht : [0, [Validators.required]],
        totrem : [0, [Validators.required]],
        totfodec : [0, [Validators.required]],
        tottva : [0, [Validators.required]],
        timbre : [0, [Validators.required]],
        totttc : [0, [Validators.required]],
        rs     : [0, [Validators.required]],
        
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

SelectFour(code_four : number)
{
  this.fourService.getData(code_four).subscribe(
    response =>{
      this.fournisseur = response;
      this.f['lib_four'].setValue(this.fournisseur.libelle);
      }
   ); 
}



addData() {
  this.crudApi.saveOrUpdate(this.crudApi.formData.value).
  subscribe( data => {
  
    this.dialogRef.close();
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.list = response;}
     );
    this.router.navigate(['/factfs']); 
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
      this.router.navigate(['/lfactf']); 
    });
  }
transformDate(date){
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }

}