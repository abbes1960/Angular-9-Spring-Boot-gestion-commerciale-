import { Lcommande } from '../model/lcommande';
export class Commande {
    id :number;
    annee : number;
    numero : number;
    code_client : number;
    lib_client : String;
    date_comm : any;
    libelle : String;
    totht : number;
    tottva : number;
    totttc : number;
    lcomms :Array<Lcommande> =[];
}
 