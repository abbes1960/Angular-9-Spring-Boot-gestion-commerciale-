import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';  
import { ToastrService } from 'ngx-toastr';
import { UserPosteService} from '../../service/user-poste.service';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Router } from '@angular/router';
import { UserPoste} from '../../model/user-poste';
import { DatePipe }         from '@angular/common';
@Component({
  selector: 'app-loginp',
  templateUrl: './loginp.component.html',
  styleUrls: ['./loginp.component.scss']
})
export class LoginpComponent implements OnInit {
  user: any={};
  mat : number;
  password : String;
  errorMessage:string; 
  Date ; 
  annee : 0;  
  code_dir : 0;
  name : string;
  constructor(private router:Router,private userService : UserPosteService,
    public toastr: ToastrService,private datePipe : DatePipe) { }    
  ngOnInit() {    
     this.userService.islogin = false;
     this.userService.admin = false;
     this.userService.suser = false;
     this.Date = this.transformDate(new Date());
     this.annee = (this.Date).toString().substring(0,4);
     localStorage.setItem('annee', this.annee.toString());
  }    
  login() {
    this.userService.login(this.mat).subscribe(
      response =>{this.user = response;
       if (this.user.pwd == this.password)
       {
        
        localStorage.setItem('name', this.user.nom);
        localStorage.setItem('code_dir', this.user.code_dir.toString());
        localStorage.setItem('mat', this.user.mat);
        localStorage.setItem('lib_direction', this.user.lib_direction);
        localStorage.setItem('lib_residence', this.user.lib_residence);
        this.userService.islogin = true;
        if (this.user.role  == "Admin")
         {
          this.userService.admin = true;
        this.router.navigate(['/lcarburant']);
      }
      else
      {
        this.userService.suser = true;
        this.router.navigate(['/lb1016']);
      }
       }
              else
              {
                this.toastr.warning( 'Mot de Passe  Incorrecte ')
               }
          },
          error => 
          
            this.toastr.warning( 'Login Incorrecte ')
         );
       }
    inscrire()
    {
      this.router.navigate(['/client']);
    }
    transformDate(date){
      return this.datePipe.transform(date, 'yyyy-MM-dd');
    }
    
    logout() {
      // remove user from local storage and set current user to null
      localStorage.removeItem('name');
      
  }
  }

