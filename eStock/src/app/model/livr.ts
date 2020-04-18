import { Llivr } from '../model/llivr';
export class Livr {
    id :number;
    date_mvt : any;
    numero   : number;
    code_client : number;
    libelle : String;
    totht : number;
    totrem : number;
    totfodec : number;
    tottva : number;
    products :Array<Llivr> =[];
}
