import { Fllivr } from '../model/fllivr';
export class Flivr {
    id :number;
    code_cli : number;
    annee : number;
    numero  :number;
    date_mvt : any;
    libelle : String;
    totht : number;
    tottva : number;
    totttc : number;
    fllivrs :Array<Fllivr> =[];
}
