import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client} from '../model/client'
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl = '/api/clients';
  choixmenu : string  = 'A';
  list : Client[];
  tokenStr = localStorage.getItem('token');
  public dataForm:  FormGroup; 
  constructor(private http: HttpClient) { }
 
 
  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`,{ headers: { authorization: this.tokenStr } });
  }
 
  createData(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, info,{ headers: { authorization: this.tokenStr } });
  }
  
  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value,{ headers: { authorization: this.tokenStr } });
  }
 
  deleteData(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' , headers: { authorization: this.tokenStr }  });
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}`,{ headers: { authorization: this.tokenStr } });
  }
  
}
