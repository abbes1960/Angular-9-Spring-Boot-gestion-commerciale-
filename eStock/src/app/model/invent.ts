import { Linvent } from '../model/linvent';
export class Invent {
    id :number;
    annee : number;
    numero  :number;
    date_invent : any;
    code_client : number;
    lib_client : String;
    libelle : String;
    total : number;
    linvents :Array<Linvent> =[]; 
}
