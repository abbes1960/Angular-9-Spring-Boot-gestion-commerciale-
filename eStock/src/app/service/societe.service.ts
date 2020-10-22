  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { Societe } from '../model/societe';
  import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
  from '@angular/forms';
  @Injectable({
    providedIn: 'root'
  })
  export class SocieteService {
    private baseUrl = '/api/societes';
    choixmenu : string  = 'A';
    list : Societe[];
    public dataForm:  FormGroup; 
    constructor(private http: HttpClient) { }
   
   
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
  