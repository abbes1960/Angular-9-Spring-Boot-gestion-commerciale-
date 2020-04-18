import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Commser } from '../model/commser';
import { Lcommser } from '../model/lcommser';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class CommserService {
  private baseUrl = '/api/commss';
  public formData:  FormGroup; 
  list: Lcommser[] = [];
  
  constructor(private http:HttpClient,private toastr: ToastrService) { }
  choixmenu : number = 1;
  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  saveOrUpdate(info: Object){

    return this.http.post(`${this.baseUrl}`, info);
   }
  
  //saveOrUpdate(info: Object): Observable<Object> {
  
   // return this.http.post(`${this.baseUrl}`, info);
  //}
  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteData(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  
}
