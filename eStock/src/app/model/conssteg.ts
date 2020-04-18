import { Lconssteg } from '../model/lconssteg';
export class Conssteg {
    id :number;
    annee : number;
    mois  : number;
    numero : number;
    code_dir : number;
    lib_direction : string;
    libelle  : string;
    total    : number;
    lconsstegs :Array<Lconssteg> =[];
}
