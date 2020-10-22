import { Ldevis } from '../model/ldevis';
export class Devis {
    id :number;
    date_mvt : any;
    numero   : number;
    code : number;
    libelle : String;
    libcl : String;
    totht : number;
    totrem : number;
    totfodec : number;
    tottva : number;
    totttc : number;
    ldevis :Array<Ldevis> =[];
}
