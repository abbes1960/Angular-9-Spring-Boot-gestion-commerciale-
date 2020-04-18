import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommandeService } from '../../service/commande.service';
import { Commande } from '../../model/commande';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Component({
  selector: 'app-listllivr',
  templateUrl: './listllivr.component.html',
  styleUrls: ['./listllivr.component.scss']
})
export class ListllivrComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
