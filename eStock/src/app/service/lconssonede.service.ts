import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Conssonede } from '../model/conssonede';
import { Lconssonede } from '../model/lConssonede';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class LconssonedeService {
//formData :Lb1016;
private baseUrl = '/api/Lconssonede';
 
lconssonede : Lconssonede = new Lconssonede();
List : Lconssonede[];

constructor(private http: HttpClient) { }
addData(info: Object): Observable<Object> {
  return this.http.post(`${this.baseUrl}`, info);
}
}
