import { Lfact } from '../model/lfact';
export class Fact {
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
    lfacts :Array<Lfact> =[];
}
