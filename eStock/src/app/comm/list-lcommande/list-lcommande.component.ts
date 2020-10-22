import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommandeService } from '../../service/commande.service';
import { Commande } from '../../model/commande';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import pdfMake from 'pdfmake/build/pdfmake';
@Component({
  selector: 'app-list-lcommande',
  templateUrl: './list-lcommande.component.html',
  styleUrls: ['./list-lcommande.component.scss']
})
export class ListLcommandeComponent implements OnInit {
  list : Commande[];
  SearchText :string;
  constructor( private service :CommandeService,private router:Router,
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



 
selectCommande(item :Commande){
  this.service.formData = this.fb.group(Object.assign({},item));
  
  this.router.navigate(['/commande']);
}



    
      onDelete(id: number) {
        if (window.confirm('Are sure you want to delete this Commande ?')) {
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
      
      newcomm()
      {
        this.service.choixmenu = 1
        this.router.navigate(['commande']);
      }
  
     
  
      onSelect(item : Commande) {
        this.service.choixmenu = 2;
       this.service.formData = this.fb.group(Object.assign({},item));
     
        this.router.navigate(['/commande']);
      }
  
      generatePdf(id : number) {
        const document = this.service.getDocument(id);
        pdfMake.createPdf(document).open(); 
       }
  
      transformDate(date){
        return this.datePipe.transform(Date, 'yyyy-MM-dd');
      }




}
