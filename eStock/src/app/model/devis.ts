import { Ldevis } from '../model/ldevis';
export class Devis {
    id :number;
    date_mvt : any;
    numero   : number;
    code_client : number;
    libelle : String;
    totht : number;
    totrem : number;
    totfodec : number;
    tottva : number;
    totttc : number;
    products :Array<Ldevis> =[];
}
