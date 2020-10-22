import { Lrecouf } from '../model/lrecouf';
export class Recouf {
    id: number;
    annee : number;
    numero : number;
    code : number;
    date_reg  : any;
    libelle   : string;
    libfour   : string;
    montant   : number;
    lrecoufs :Array<Lrecouf> =[];
}

