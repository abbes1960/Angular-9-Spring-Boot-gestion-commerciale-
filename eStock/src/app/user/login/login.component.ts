import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';  
import { ToastrService } from 'ngx-toastr';
import { UserService} from '../../service/user.service';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Router } from '@angular/router';
import { User} from '../../model/user';
import { DatePipe }         from '@angular/common';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: any={};
  loginname : String;
  password : String;
  errorMessage:string;  
  name : string;  
  Wdate;
  annee : 0;
  constructor(private router:Router,private userService : UserService,
    public toastr: ToastrService,private datePipe : DatePipe) { }    
  ngOnInit() {    
     this.userService.islogin = false;
     this.userService.admin = false;
     this.userService.suser = false;
     this.Wdate = this.transformDate(new Date());
     this.annee = (this.Wdate).toString().substring(0,4);
     localStorage.setItem('annee', this.annee.toString());
  }    
  login() {
    
    this.userService.login(this.loginname).subscribe(
      response =>{this.user = response;
      
       if (this.user.pwd == this.password)
       {
        this.name = this.user.nom;
        localStorage.setItem('name', this.name);
         this.userService.islogin = true;
        if (this.user.role  == "Admin")
         {
          this.userService.admin = true;
        this.router.navigate(['/commandes']);
     
     
      }
      else
      {
        this.userService.suser = true;
        this.router.navigate(['/commande']);
        
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
