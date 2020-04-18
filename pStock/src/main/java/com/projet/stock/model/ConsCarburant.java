package com.projet.stock.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "ConsCarburant")
public class ConsCarburant {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private int annee;
	  private int mois;
	  private int mat;
	  private int code;
	  private String lib_direction;
	  private String libelle;
	  private int qte1;
	  private int qte2;
	  private int qte3;
	  private int qte4;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLib_direction() {
		return lib_direction;
	}
	public void setLib_direction(String lib_direction) {
		this.lib_direction = lib_direction;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getQte1() {
		return qte1;
	}
	public void setQte1(int qte1) {
		this.qte1 = qte1;
	}
	public int getQte2() {
		return qte2;
	}
	public void setQte2(int qte2) {
		this.qte2 = qte2;
	}
	public int getQte3() {
		return qte3;
	}
	public void setQte3(int qte3) {
		this.qte3 = qte3;
	}
	public int getQte4() {
		return qte4;
	}
	public void setQte4(int qte4) {
		this.qte4 = qte4;
	}
	public ConsCarburant(long id, int numero, int annee, int mois, int mat, int code, String lib_direction,
			String libelle, int qte1, int qte2, int qte3, int qte4) {
		super();
		this.id = id;
		this.numero = numero;
		this.annee = annee;
		this.mois = mois;
		this.mat = mat;
		this.code = code;
		this.lib_direction = lib_direction;
		this.libelle = libelle;
		this.qte1 = qte1;
		this.qte2 = qte2;
		this.qte3 = qte3;
		this.qte4 = qte4;
	}
	public ConsCarburant() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ConsCarburant [id=" + id + ", numero=" + numero + ", annee=" + annee + ", mois=" + mois + ", mat=" + mat
				+ ", code=" + code + ", lib_direction=" + lib_direction + ", libelle=" + libelle + ", qte1="
				+ qte1 + ", qte2=" + qte2 + ", qte3=" + qte3 + ", qte4=" + qte4 + "]";
	}

	
}
