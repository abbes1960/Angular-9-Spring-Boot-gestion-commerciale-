import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ToastrModule } from 'ngx-toastr';
import { FormsModule  } from '@angular/forms';
import { RouterModule, Routes} from '@angular/router';
import { MatDialogModule,MatDialogRef, } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule  } from '@angular/forms';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AppComponent } from './app.component';
import { AddClientComponent } from './client/add-client/add-client.component';
import { ListClientComponent } from './client/list-client/list-client.component';
import { AddCategorieComponent } from './categorie/add-categorie/add-categorie.component';
import { ListCategorieComponent } from './categorie/list-categorie/list-categorie.component';
import { ListScategorieComponent } from './scategorie/list-scategorie/list-scategorie.component';
import { AddScategorieComponent } from './scategorie/add-scategorie/add-scategorie.component';
import { AddArticleComponent } from './article/add-article/add-article.component';
import { ListArticleComponent } from './article/list-article/list-article.component';
import { AddFourComponent } from './fournisseur/add-four/add-four.component';
import { ListFourComponent } from './fournisseur/list-four/list-four.component';
import { HttpClientModule} from '@angular/common/http';
import { MatSliderModule } from '@angular/material/slider';
import { FlexLayoutModule } from '@angular/flex-layout';

import { NgMatSearchBarModule } from 'ng-mat-search-bar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { RegisterComponent } from './user/register/register.component';
import { LoginComponent } from './user/login/login.component';
import { ListUserComponent } from './user/list-user/list-user.component';
import { AddmapComponent } from './map/addmap/addmap.component';
import { MenuComponent } from './menu/menu.component';
import { AddDepotComponent } from './depot/add-depot/add-depot.component';
import { ListDepotComponent } from './depot/list-depot/list-depot.component';

import { ListLcommandeComponent } from './comm/list-lcommande/list-lcommande.component';
import { AddLcommandeComponent } from './comm/add-lcommande/add-lcommande.component';

import { AddFlcommComponent } from './fcomm/add-flcomm/add-flcomm.component';
import { ListFlcommComponent } from './fcomm/list-flcomm/list-flcomm.component';
import { ListFlivrComponent } from './flivr/list-flivr/list-flivr.component';
import { AddFlivrComponent } from './flivr/add-flivr/add-flivr.component';
import { AddFllivrComponent } from './flivr/add-fllivr/add-fllivr.component';
import { ListFllivrComponent } from './flivr/list-fllivr/list-fllivr.component';
import { ListllivrComponent } from './livr/listllivr/listllivr.component';
import { AddllivrComponent } from './livr/addllivr/addllivr.component';
import { AddlivrComponent } from './livr/addlivr/addlivr.component';
import { ListlivrComponent } from './livr/listlivr/listlivr.component';
import { ListFcommComponent } from './fcomm/list-fcomm/list-fcomm.component';
import { AddFcommComponent } from './fcomm/add-fcomm/add-fcomm.component';
import { AddlivrsComponent } from './livrs/addlivrs/addlivrs.component';
import { ListlivrsComponent } from './livrs/listlivrs/listlivrs.component';
import { AddCommComponent } from './comm/add-comm/add-comm.component';
import { ListCommComponent } from './comm/list-comm/list-comm.component';
import { DatePipe } from '@angular/common';
import { AuthGuardService} from './auth/auth-guard.service';

import { AddFactComponent } from './fact/add-fact/add-fact.component';
import { ListFactComponent } from './fact/list-fact/list-fact.component';
import { AddLfactComponent } from './fact/add-lfact/add-lfact.component';
import { ListLfactComponent } from './fact/list-lfact/list-lfact.component';
import { AddAvoirComponent } from './avoir/add-avoir/add-avoir.component';
import { ListAvoirComponent } from './avoir/list-avoir/list-avoir.component';
import { AddLavoirComponent } from './avoir/add-lavoir/add-lavoir.component';
import { ListLavoirComponent } from './avoir/list-lavoir/list-lavoir.component';

import { AddInventComponent } from './invent/add-invent/add-invent.component';
import { ListInventComponent } from './invent/list-invent/list-invent.component';
import { ListLinventComponent } from './invent/list-linvent/list-linvent.component';
import { AddLinventComponent } from './invent/add-linvent/add-linvent.component';
import { ListRecouvComponent } from './recouv/list-recouv/list-recouv.component';
import { ListLrecouvComponent } from './recouv/list-lrecouv/list-lrecouv.component';
import { AddLrecouvComponent } from './recouv/add-lrecouv/add-lrecouv.component';
import { AddRecouvComponent } from './recouv/add-recouv/add-recouv.component';
import { AddRecoufComponent } from './recouf/add-recouf/add-recouf.component';
import { ListRecoufComponent } from './recouf/list-recouf/list-recouf.component';
import { ListLrecoufComponent } from './recouf/list-lrecouf/list-lrecouf.component';
import { AddLrecoufComponent } from './recouf/add-lrecouf/add-lrecouf.component';
import { AddDevisComponent } from './devis/add-devis/add-devis.component';
import { ListDevisComponent } from './devis/list-devis/list-devis.component';
import { ListLdevisComponent } from './devis/list-ldevis/list-ldevis.component';
import { AddLdevisComponent } from './devis/add-ldevis/add-ldevis.component';
import { AddFfactComponent } from './ffact/add-ffact/add-ffact.component';
import { ListFfactComponent } from './ffact/list-ffact/list-ffact.component';

import { AddCommsComponent } from './comms/add-comms/add-comms.component';
import { ListCommsComponent } from './comms/list-comms/list-comms.component';
import { ListLcommsComponent } from './comms/list-lcomms/list-lcomms.component';
import { AddLcommsComponent } from './comms/add-lcomms/add-lcomms.component';
import { AddTypefComponent } from './typefac/add-typef/add-typef.component';
import { ListTypefComponent } from './typefac/list-typef/list-typef.component';
import { AddPanierComponent } from './panier/add-panier/add-panier.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';
import {NgxPaginationModule} from 'ngx-pagination';
import { AccueilComponent } from './accueil/accueil.component';
import { Accueil1Component } from './accueil1/accueil1.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
const appRoutes : Routes = [
    {path: '', component:MenuComponent,canActivate:[AuthGaurdService] ,children : [
    {path: 'clients', component: ListClientComponent},
    {path: 'invent', component: AddInventComponent},
    {path: 'typef', component: AddTypefComponent},
    {path: 'accueil', component: AccueilComponent},
    {path: 'accueil1', component: Accueil1Component},
    {path: 'fact', component: AddFactComponent},
    {path: 'factf', component: AddFfactComponent},
    {path: 'recouf', component: AddRecoufComponent},
    {path: 'recouv', component: AddRecouvComponent},
    {path: 'devis', component: AddDevisComponent},
    {path: 'ldevis', component: ListDevisComponent},
   {path: 'comms', component: AddCommsComponent },
    {path: 'lcomms', component: ListCommsComponent },
    {path: 'lcomm', component: ListCommComponent},
    {path: 'livr', component: AddlivrComponent },
    {path: 'llivr', component: ListlivrComponent },
    {path: 'livrs', component: AddlivrsComponent },
    {path: 'llivrs', component: ListlivrsComponent },
    {path: 'llivr', component: ListlivrComponent },
    {path: 'flivr', component: AddFlivrComponent},
    {path: 'lflivr', component: ListFlivrComponent},
    {path: 'fact', component: AddFactComponent },
    {path: 'lfact', component: ListFactComponent },
    {path: 'avoir', component: AddAvoirComponent},
    {path: 'lavoir', component: ListAvoirComponent},
    {path: 'fournisseurs', component: ListFourComponent},
    {path: 'fournisseur', component: AddFourComponent},
    {path: 'depot', component: AddDepotComponent},
    {path: 'depots', component: ListDepotComponent},
    {path: 'four', component: AddFourComponent},
    {path: 'fours', component: ListFourComponent},
    {path: 'client', component: AddClientComponent},
    {path: 'clients', component: ListClientComponent},
    {path: 'categories', component: ListCategorieComponent},
    {path: 'categories', component: ListCategorieComponent},
    {path: 'article', component: AddArticleComponent},
    {path: 'articles', component: ListArticleComponent},
   ]},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'panier', component: AddPanierComponent },
    
    {path: 'panier', component: AddPanierComponent},
    {path: 'comm', component: AddCommComponent },
    
  ];
  @NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }