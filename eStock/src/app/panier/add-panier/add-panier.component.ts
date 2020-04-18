import { Component, OnInit } from '@angular/core';
import { ArticleService} from '../../service/article.service';
import { ToastrService } from 'ngx-toastr';
import { Article} from '../../model/article';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-panier',
  templateUrl: './add-panier.component.html',
  styleUrls: ['./add-panier.component.scss']
})
export class AddPanierComponent implements OnInit {
  article : Article;
  qte : 0;
  constructor(public crudApi: ArticleService, public toastr: ToastrService,
    private router : Router) { }
 
  ngOnInit() {
    
    this.getData();
  }
 
 
  addToCart()
  {

  }

  removeFromCart()
  {
    
  }
  getData() {
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.listData = response;}
     );
   
  }
  
 
 
 

}
