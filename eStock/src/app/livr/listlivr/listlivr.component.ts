import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LivrService } from '../../service/livr.service';
import { Livr } from '../../model/livr';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import pdfMake from 'pdfmake/build/pdfmake';
  import { map } from 'rxjs/operators';
  import pdfFonts from 'pdfmake/build/vfs_fonts';
  pdfMake.vfs = pdfFonts.pdfMake.vfs;
@Component({
  selector: 'app-listlivr',
  templateUrl: './listlivr.component.html',
  styleUrls: ['./listlivr.component.scss']
})
export class ListlivrComponent implements OnInit {
  p: number = 1;
  list : Livr[];
  SearchText :string;
  constructor( private service :LivrService,private router:Router,
    private toastr :ToastrService,public fb: FormBuilder,
    private datePipe : DatePipe) { }

  ngOnInit() {
    
    this.getData();
    
  }
getData(){
  this.service.getAll().subscribe(
    response =>{this.list = response;}
   );

}

  
  onDelete(id: number) {
    if (window.confirm('Are sure you want to delete this Livraison ?')) {
      this.service.deleteData(id)
        .subscribe(
          data => {
            console.log(data);
            this.toastr.success(' data successfully deleted!'); 
            this.getData();
          },
          error => console.log(error));
    }
  }
  newlivr()
  {
    this.service.choixmenu = 1
    this.router.navigate(['/livr']);
  }
 

onSelect(item : Livr){
  this.service.choixmenu = 2;
  this.service.formData = this.fb.group(Object.assign({},item));
  this.router.navigate(['/livr']);
}

transformDate(date){
  return this.datePipe.transform(date, 'yyyy-MM-dd');
}
generatePdf(id : number) {
  const document = this.service.getDocument(id);
  pdfMake.createPdf(document).open(); 
 }
}


