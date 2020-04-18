import { Lavoir } from '../model/lavoir';
export class Avoir {
    id :number;
    annee : number;
    numero  :number;
    date_avoir : any;
    code_client : number;
    lib_client : String;
    libelle : String;
    totht : number;
    totfodec : number;
    tottva : number;
    totttc : number;
    lavoirs :Array<Lavoir> =[];
}


