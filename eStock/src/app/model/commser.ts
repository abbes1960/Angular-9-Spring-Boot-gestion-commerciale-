import { Lcommser } from '../model/lcommser';
export class Commser {
    id :number;
    annee : number;
    numero : number;
    code_client : number;
    lib_client : String;
    date_mvt : any;
    libelle : String;
    totht : number;
    tottva : number;
    totttc : number;
    products :Array<Lcommser> =[];
}
