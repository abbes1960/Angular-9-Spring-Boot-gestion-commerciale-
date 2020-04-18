import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Bs1016 } from '../model/bs1016';
import { Lbs1016 } from '../model/lbs1016';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class Lbs1016Service {
  private baseUrl = '/api/bons';
  public formData:  FormGroup; 
  lbs1016 : Lbs1016[];
  constructor(private http:HttpClient,private toastr: ToastrService) { }
  choixmenu : number = 1;
  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  saveOrUpdate(info: Object): Observable<Object> {
  
    return this.http.post(`${this.baseUrl}`, info);
  }
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
