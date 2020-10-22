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

  errorMessage:string;  
  name : string;  
  Wdate;
  annee : 0;
  loginForm:  FormGroup; 
  
  constructor(private router:Router,private userService : UserService,
    public toastr: ToastrService,private datePipe : DatePipe,public fb: FormBuilder) { }    
  ngOnInit() {    
     this.userService.islogin = false;
     this.userService.admin = false;
     this.userService.suser = false;
     this.Wdate = this.transformDate(new Date());
     this.annee = (this.Wdate).toString().substring(0,4);
     localStorage.setItem('annee', this.annee.toString());
     this.loginForm = this.fb.group({
      'username' : [null, Validators.required],
      'password' : [null, Validators.required]
    });
  }    
  login() {
    const val = this.loginForm.value;
    this.userService.login(val.username, val.password).subscribe(
      res =>{
      this.user = res;
        localStorage.setItem("name", this.user.username);
       
        let jwt = "Bearer " + this.user.jwt;
          localStorage.setItem("token", jwt)
       
         this.userService.islogin = true;
        if (this.user.role  == "Admin")
         {
         this.userService.admin = true;
          this.router.navigate(['/accueil']);
      }
      else
      {
        this.userService.suser = true;
        
        this.router.navigate(['/accueil1']);
      }
          },
          error => 
          
            this.toastr.warning( 'Login Incorrecte ')
         
          
          );
        }
     
        
        
        logOut() {
          localStorage.removeItem("username");
        }
 


  /*  onFormSubmit(form: NgForm) {
      this.authService.login(form)
        .subscribe(res => {
          console.log(res);
          if (res.token) {
            localStorage.setItem('token', res.token);
            this.router.navigate(['products']);
          }
        }, (err) => {
          console.log(err);
        });
    }

*/

    transformDate(date){
      return this.datePipe.transform(date, 'yyyy-MM-dd');
    }
    logout() {
      // remove user from local storage and set current user to null
      localStorage.removeItem('name');
      
  }
}
