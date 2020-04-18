import { Component, OnInit } from '@angular/core';
import { UserPosteService} from '../../service/user-poste.service'
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Router } from '@angular/router';
import { UserPoste} from '../../model/user-poste';
import { Direction}        from '../../model/direction';
import { Residence}        from '../../model/residence';
import { DirectionService} from '../../service/direction.service';
import { ResidenceService} from '../../service/residence.service';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {
  constructor(public crudApi: UserPosteService ,public fb: FormBuilder,public toastr: ToastrService,
    public residenceService : ResidenceService,private router : Router,public directionService : DirectionService) { }
    directionList: Direction[];
    residenceList: Residence[];
    get f() { return this.crudApi.formData.controls; }
  ngOnInit() {
  
   
    this.infoForm();
    this.directionService.getAll().subscribe(
      response =>{this.directionList = response;}
     );
   }

  
  infoForm() {
    this.crudApi.formData = this.fb.group({
        id: null,
        nom: ['', [Validators.required, Validators.minLength(5)]],
        role: ['', [Validators.required, Validators.minLength(8)]],
        mat: [0, [Validators.required, Validators.minLength(5)]],
        code_dir: [0, [Validators.required]],
        code_res: [0, [Validators.required]],
        pwd: ['', [Validators.required, Validators.minLength(8)]],
        pwdd: ['', [Validators.required, Validators.minLength(8)]],
        lib_direction: ['', [Validators.required, Validators.minLength(8)]],
        lib_residence: ['', [Validators.required, Validators.minLength(8)]],
        });
    }
   
  

  ResetForm() {
      this.crudApi.formData.reset();
  }
  onSubmit() {
   
    if (this.crudApi.formData.value.pwd == this.crudApi.formData.value.pwdd)
    {
      if (this.crudApi.choixmenu == "A")
      {
        this.addData();
      }
      else
      {
       this.updateData()
      }
    }
    else
    {
      this.toastr.warning( 'Vérifiet votre de passe ...');  
    }
}
  
   

addData() {
  this.crudApi.createData(this.crudApi.formData.value).
  subscribe( data => {
    this.toastr.success( 'Validation Faite avec Success'); 
    this.router.navigate(['/users']);
  });
}
  updateData()
  {
  
    this.crudApi.updatedata(this.crudApi.formData.value.id,this.crudApi.formData.value).
    subscribe( data => {
      this.toastr.success( 'Modification Faite avec Success');

      this.router.navigate(['/userss']);
    });
  }

  onSelectDir(ctrl)
  {
     if(ctrl.selectedIndex == 0){
      this.f['lib_direction'].setValue('');
     }
     else{
       alert("ddddd");
        this.f['lib_direction'].setValue(this.directionList[ctrl.selectedIndex - 1].libelle);
        this.f['code_dir'].setValue(this.directionList[ctrl.selectedIndex - 1].code);
        this.residenceService.listResidence(this.crudApi.formData.value.code_dir).subscribe(
          response =>{
            alert ("dddddddddddd");
            this.residenceList = response;}
         );  
     }
   }


onSelectRes(ctrl)
    {
      if(ctrl.selectedIndex == 0){
        this.f['lib_residence'].setValue('');
       }
       else{
          this.f['lib_residence'].setValue(this.residenceList[ctrl.selectedIndex - 1].libelle);
          this.f['code_res'].setValue(this.residenceList[ctrl.selectedIndex - 1].code);
        }  
    } 
}

