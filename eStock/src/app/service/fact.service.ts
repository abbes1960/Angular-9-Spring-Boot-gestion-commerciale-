import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Fact } from '../model/fact';
import { Lfact} from '../model/lfact';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FactService {
  private baseUrl = '/api/facts';
  public formData:  FormGroup; 
  list :  any=[];
  fact    : any={};
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
  getDocument(id :number) {
    {
      this.getData(id).subscribe(
       response =>{
         this.fact = response;
         }
      );  
      sessionStorage.setItem('bon', JSON.stringify(this.fact));
    return {
      content: [
        {
          columns: [
            [{
              text: 'La Poste Tunisienne' ,
              style: 'name'
            },
            {
              text: 'Centre de Comptabilité Matiére'
            },
            {
              text: 'Email : ' ,
            },
            {
              text: 'Tel  : ' ,
              color: 'blue',
            },
            ],
          ]
        },
        {
          text: 'Bon 1016',
          bold: true,
          fontSize: 20,
          alignment: 'center',
          margin: [0, 0, 0, 20]
        },
       
        {
          columns: [
            [{
              text: 'Numero : '+ this.fact.numero + ' Date Mvt : ' +this.fact.date_mvt,
              style: 'ligne',
              margin: [0, 100, 50, 20]
            },
            {
              text: 'Client :  '+ this.fact.lib_client ,
              style: 'ligne',
              margin: [0, 100, 100, 20]
            },
           
            
            ],
          ]
        },
        
        
        
        {
          text: 'Détails Produits ',
          style: 'header'
        },
       this.getEducationObject(this.fact.lfacts),
       {

       },
        {
          text: 'Total : ' + this.fact.total ,
          style: 'total',
          alignment : 'right'
        },
        
        {
          text: 'Signature',
          style: 'sign',
          alignment : 'right'

        },
       
      ],
     
        styles: {
          header: {
            fontSize: 18,
            bold: true,
            margin: [0, 20, 0, 10],
            decoration: 'underline'
          },
          name: {
            fontSize: 16,
            bold: true
          },
          total: {
            fontSize: 12,
            bold: true,
            italics: true
          },
          ligne: {
            fontSize: 12,
            bold: true,
            italics: true
          },
          sign: {
            margin: [0, 50, 0, 10],
            alignment: 'right',
            italics: true
          },
          tableHeader: {
            bold: true,
            fontSize: 15
          }
        }
    };
  }
   }

   getEducationObject(items: Lfact[]) {
    return {
      table: {
        widths: ['*', '*', '*', '*','*'],
        body: [
          [{
            text: 'Code',
            style: 'tableHeader'
          },
          {
            text: 'Désignation',
            style: 'tableHeader'
          },
          {
            text: 'Qté',
            style: 'tableHeader'
          },
          {
            text: 'Pu',
            style: 'tableHeader'
          },
          {
            text: 'Total',
            style: 'tableHeader'
          },
          ],
          ...items.map(ed => {
          //  return [ed.code,ed.libart, ed.pu, ed.qte, ed.total];
          })
        ]
      }
    };
  }
}
