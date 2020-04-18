import { Lconssonede } from '../model/lconssonede';
export class Conssonede {
    id :number;
    annee : number;
    mois  : number;
    numero : number;
    code_dir : number;
    lib_direction : string;
    libelle  : string;
    total    : number;
    lconssondes :Array<Lconssonede> =[];
}
