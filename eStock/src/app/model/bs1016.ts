import { Lbs1016 } from '../model/lbs1016';
export class Bs1016 {
    id :number;
    annee : number;
    numero  :number;
    code_dir : number;
    code_res : number;
    date_mvt : any;
    libelle : String;
    total : number;
    products :Array<Lbs1016> =[];
}
