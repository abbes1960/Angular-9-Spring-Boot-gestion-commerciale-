import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Lcommande } from '../model/lcommande';
@Injectable({
  providedIn: 'root'
})
export class LfcommandeService {
//formData :Lb1016;
private baseUrl = '/api/Lcommandes';
lcommande : Lcommande = new Lcommande();
lcommandeList : Lcommande[];
constructor(private http: HttpClient) { }
addLcomm(info: Object): Observable<Object> {
  return this.http.post(`${this.baseUrl}`, info);
}
}
