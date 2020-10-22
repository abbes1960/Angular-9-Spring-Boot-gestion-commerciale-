import { Llivrs } from '../model/llivrs';
export class Livrs {
    id :number;
    numero :number;
    annee : number;
    date_mvt : any;
    code : number;
    libcl: string;
    smtva : string;
    chauffeur: string;
    camion : string;
    totht : number;
    totrem : number;
    totfodec : number;
    tottva : number;
    totttc : number;
    numfac : number;
    llivrs :Array<Llivrs> =[];
}