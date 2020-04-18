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
import { AddDirectionComponent } from './direction/add-direction/add-direction.component';
import { ListDirectionComponent } from './direction/list-direction/list-direction.component';
import { AddResidenceComponent } from './residence/add-residence/add-residence.component';
import { ListResidenceComponent } from './residence/list-residence/list-residence.component';
import { ListLcommandeComponent } from './comm/list-lcommande/list-lcommande.component';
import { AddLcommandeComponent } from './comm/add-lcommande/add-lcommande.component';
import { AddB1016Component } from './b1016/add-b1016/add-b1016.component';
import { ListB1016Component } from './b1016/list-b1016/list-b1016.component';
import { ListLb1016Component } from './b1016/list-lb1016/list-lb1016.component';
import { AddLb1016Component } from './b1016/add-lb1016/add-lb1016.component';
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
import { AddBs1016Component } from './bs1016/add-bs1016/add-bs1016.component';
import { AddLbs1016Component } from './bs1016/add-lbs1016/add-lbs1016.component';
import { AddCommComponent } from './comm/add-comm/add-comm.component';
import { ListCommComponent } from './comm/list-comm/list-comm.component';
import { DatePipe } from '@angular/common';
import { AuthGuardService} from './auth/auth-guard.service';
import { ListBs1016Component } from './bs1016/list-bs1016/list-bs1016.component';
import { AddFactComponent } from './fact/add-fact/add-fact.component';
import { ListFactComponent } from './fact/list-fact/list-fact.component';
import { AddLfactComponent } from './fact/add-lfact/add-lfact.component';
import { ListLfactComponent } from './fact/list-lfact/list-lfact.component';
import { AddAvoirComponent } from './avoir/add-avoir/add-avoir.component';
import { ListAvoirComponent } from './avoir/list-avoir/list-avoir.component';
import { AddLavoirComponent } from './avoir/add-lavoir/add-lavoir.component';
import { ListLavoirComponent } from './avoir/list-lavoir/list-lavoir.component';
import { ListLbs1016Component } from './bs1016/list-lbs1016/list-lbs1016.component';
import { AddUserComponent } from './userPoste/add-user/add-user.component';
import { LoginpComponent } from './userPoste/loginp/loginp.component';
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
import { AddConsstegComponent } from './consSteg/add-conssteg/add-conssteg.component';
import { ListConsstegComponent } from './consSteg/list-conssteg/list-conssteg.component';
import { ListLconsstegComponent } from './consSteg/list-lconssteg/list-lconssteg.component';
import { AddLconsstegComponent } from './consSteg/add-lconssteg/add-lconssteg.component';
import { AddConssonedeComponent } from './consSonede/add-conssonede/add-conssonede.component';
import { ListConssonedeComponent } from './consSonede/list-conssonede/list-conssonede.component';
import { ListLconssonedeComponent } from './consSonede/list-lconssonede/list-lconssonede.component';
import { AddLconssonedeComponent } from './consSonede/add-lconssonede/add-lconssonede.component';
import { AddCommsComponent } from './comms/add-comms/add-comms.component';
import { ListCommsComponent } from './comms/list-comms/list-comms.component';
import { ListLcommsComponent } from './comms/list-lcomms/list-lcomms.component';
import { AddLcommsComponent } from './comms/add-lcomms/add-lcomms.component';
import { AddPreleveComponent } from './prelevement/add-preleve/add-preleve.component';
import { AddLpreleveComponent } from './prelevement/add-lpreleve/add-lpreleve.component';
import { ListPreleveComponent } from './prelevement/list-preleve/list-preleve.component';
import { ListLpreleveComponent } from './prelevement/list-lpreleve/list-lpreleve.component';
import { AddProductionComponent } from './production/add-production/add-production.component';
import { ListProductionComponent } from './production/list-production/list-production.component';
import { AddLproductionComponent } from './production/add-lproduction/add-lproduction.component';
import { ListLproductionComponent } from './production/list-lproduction/list-lproduction.component';
import { AddAgentComponent } from './agent/add-agent/add-agent.component';
import { ListAgentComponent } from './agent/list-agent/list-agent.component';
import { AddGradeComponent } from './grade/add-grade/add-grade.component';
import { ListGradeComponent } from './grade/list-grade/list-grade.component';
import { AddTypefComponent } from './typefac/add-typef/add-typef.component';
import { ListTypefComponent } from './typefac/list-typef/list-typef.component';
import { AddPanierComponent } from './panier/add-panier/add-panier.component';

import { AddChariotComponent } from './chariot/add-chariot/add-chariot.component';
import { AddConsommationComponent } from './consCarburant/add-consommation/add-consommation.component';
import { ListConsommationComponent } from './consCarburant/list-consommation/list-consommation.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'
const MATERIAL_MODULES = [MatToolbarModule,
  MatIconModule
];
const appRoutes : Routes = [
  {path: '', component:MenuComponent,children : [
  {path: 'clients', component: ListClientComponent},
  {path: 'invent', component: AddInventComponent},
  {path: 'typef', component: AddTypefComponent},
  {path: 'panier', component: AddPanierComponent},
  {path: 'fact', component: AddFactComponent},
  {path: 'factf', component: AddFfactComponent},
  {path: 'recouf', component: AddRecoufComponent},
  {path: 'recouv', component: AddRecouvComponent},
  {path: 'devis', component: AddDevisComponent},
  {path: 'ldevis', component: ListDevisComponent},
  {path: 'comm', component: AddCommComponent },
  {path: 'comms', component: AddCommsComponent },
  {path: 'lcomms', component: ListCommsComponent },
  {path: 'conssonede', component: AddConssonedeComponent},
  {path: 'conssteg', component: AddConsstegComponent },
  {path: 'lcomm', component: ListCommComponent},
  {path: 'b1016', component: AddB1016Component },
  {path: 'lb1016', component: ListB1016Component },
  {path: 'bs1016', component: AddBs1016Component},
  {path: 'lbs1016', component: ListBs1016Component},
  {path: 'livr', component: AddlivrComponent },
  {path: 'flivr', component: AddFlivrComponent},
  {path: 'lflivr', component: ListFlivrComponent},
  {path: 'fact', component: AddFactComponent },
  {path: 'lfact', component: ListFactComponent },
  {path: 'avoir', component: AddAvoirComponent},
  {path: 'lavoir', component: ListAvoirComponent},
  {path: 'fournisseurs', component: ListFourComponent},
  {path: 'direction', component: AddDirectionComponent},
  {path: 'directions', component: ListDirectionComponent},
  {path: 'four', component: AddFourComponent},
  {path: 'fours', component: ListFourComponent},
  {path: 'residences', component: ListResidenceComponent},
  {path: 'residence', component: AddResidenceComponent},
  {path: 'client', component: AddClientComponent},
  {path: 'clients', component: ListClientComponent},
  {path: 'categories', component: ListCategorieComponent},
  {path: 'categorie', component: AddCategorieComponent},
  {path: 'scategories', component: ListScategorieComponent},
  {path: 'scategorie', component: AddScategorieComponent},
  {path: 'larticle', component: ListArticleComponent},
  {path: 'carburant', component: AddConsommationComponent},
  {path: 'lcarburant', component: ListConsommationComponent},
  {path: 'article', component: AddArticleComponent},]},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registerp', component: AddUserComponent},
  {path: 'loginp', component: LoginpComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    AddClientComponent,
    ListClientComponent,
    AddCategorieComponent,
    ListCategorieComponent,
    ListScategorieComponent,
    AddScategorieComponent,
    AddArticleComponent,
    ListArticleComponent,
    AddFourComponent,
    ListFourComponent,
    RegisterComponent,
    LoginComponent,
    ListUserComponent,
    AddmapComponent,
    MenuComponent,
    
    AddDirectionComponent,
    ListDirectionComponent,
    
    AddResidenceComponent,
    ListResidenceComponent,
    
    ListLcommandeComponent,
    AddLcommandeComponent,
    AddB1016Component,
    ListB1016Component,
    ListLb1016Component,
    AddLb1016Component,
   
    AddFlcommComponent,
    ListFlcommComponent,
    ListFlivrComponent,
    AddFlivrComponent,
    AddFllivrComponent,
    ListFllivrComponent,
    ListllivrComponent,
    AddllivrComponent,
    AddlivrComponent,
    ListlivrComponent,
    ListFcommComponent,
    AddFcommComponent,
    AddBs1016Component,
    AddLbs1016Component,
    AddCommComponent,
    ListCommComponent,
    ListBs1016Component,
    AddFactComponent,
    ListFactComponent,
    AddLfactComponent,
    ListLfactComponent,
    AddAvoirComponent,
    ListAvoirComponent,
    AddLavoirComponent,
    ListLavoirComponent,
    ListLbs1016Component,
    AddUserComponent,
    LoginpComponent,
    AddInventComponent,
    ListInventComponent,
    ListLinventComponent,
    AddLinventComponent,
  
    ListRecouvComponent,
    ListLrecouvComponent,
    AddLrecouvComponent,
    AddRecouvComponent,
    ListRecoufComponent,
    ListLrecoufComponent,
    AddLrecoufComponent,
    
    AddDevisComponent,
    ListDevisComponent,
    ListLdevisComponent,
    AddLdevisComponent,
    AddFfactComponent,
    ListFfactComponent,
    AddConsstegComponent,
    ListConsstegComponent,
    ListLconsstegComponent,
    AddLconsstegComponent,
    AddConssonedeComponent,
    ListConssonedeComponent,
    ListLconssonedeComponent,
    AddLconssonedeComponent,
    AddCommsComponent,
    ListCommsComponent,
    ListLcommsComponent,
    AddLcommsComponent,
    AddPreleveComponent,
    AddLpreleveComponent,
    ListPreleveComponent,
    ListLpreleveComponent,
    AddProductionComponent,
    ListProductionComponent,
    AddLproductionComponent,
    ListLproductionComponent,
    AddRecoufComponent,
    AddAgentComponent,
    ListAgentComponent,
    AddGradeComponent,
    ListGradeComponent,
    AddTypefComponent,
    ListTypefComponent,
    AddPanierComponent,
   
    AddChariotComponent,
   
    AddConsommationComponent,
   
    ListConsommationComponent
    
  
   
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    FormsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatSliderModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatIconModule,
    NgMatSearchBarModule,
    NgbModule
    
   
  

  ],
  exports : MATERIAL_MODULES,
  providers: [DatePipe,{ provide: MAT_DIALOG_DATA, useValue: {} ,},
    { provide: MatDialogRef, useValue: {} }],
  bootstrap: [AppComponent],
  entryComponents: [AddCategorieComponent]
})
export class AppModule { }
