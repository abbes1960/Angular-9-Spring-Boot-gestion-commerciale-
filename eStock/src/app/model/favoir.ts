import { Lfavoir } from '../model/lfavoir';
export class Favoir {
    id :number;
    code_dir : number;
    annee : number;
    numero  :number;
    date_mvt : any;
    code : number;
    libfour : string;
    libelle : String;
    totht : number;
    tottva : number;
    totttc : number;
    lfavoirs :Array<Lfavoir> =[];
}
