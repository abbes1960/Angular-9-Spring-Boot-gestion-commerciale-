import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserPoste} from '../model/user-poste'
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class UserPosteService {
  private baseUrl = '/api/usersp';
  private baseUrl1 = '/api/usersp/5';
  islogin = false;
  admin = false;
  suser = false;
  choixmenu : string  = 'A';
  list : UserPoste[];
  public formData :  FormGroup; 
  constructor(private http: HttpClient) { }
  login(login: number): Observable<Object> {
    
     return this.http.get(`${this.baseUrl1}/${login}`);
   }  
 
  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  createData(info: Object): Observable<Object> {
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
