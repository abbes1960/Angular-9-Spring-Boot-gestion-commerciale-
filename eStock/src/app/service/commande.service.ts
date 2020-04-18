import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Commande } from '../model/commande';
import { Lcommande } from '../model/lcommande';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  private baseUrl = '/api/comms';
  public formData:  FormGroup; 
  list: any={}
  commande : Commande;
 
  constructor(private http:HttpClient,private toastr: ToastrService) { }
  choixmenu : string  = "A";
  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  saveOrUpdate(info: Object) {
   
   return this.http.post(`${this.baseUrl}`,info);
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
  
  deleteAll(id: number): Observable<any> {
  
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
         
}
