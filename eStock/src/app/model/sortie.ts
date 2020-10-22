import { Lsortie } from '../model/lsortie';
export class Sortie {
    id :number;
    annee : number;
    numero : number;
    code : number;
    libdep : string;
    date_mvt : any;
    libelle : String;
    lsortiess :Array<Lsortie> =[];
}
