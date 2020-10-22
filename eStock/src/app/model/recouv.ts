import { Lrecouv } from '../model/lrecouv';
export class Recouv {
    id: number;
    annee : number;
    numero : number;
    code : number;
    date_reg  : any;
    libelle   : string;
    libcl   : string;
    montant   : number;
    lrecouvs :Array<Lrecouv> =[];
}
