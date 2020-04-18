package com.projet.stock.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "LconsSonede")
public class LconsSteg {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private int annee;
	  private int mois;
	  private int code_res;
	  private String lib_res;
	  private int qte;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private ConsSteg consSteg;
	 
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
	public int getCode_res() {
		return code_res;
	}
	public void setCode_res(int code_res) {
		this.code_res = code_res;
	}
	public String getLib_res() {
		return lib_res;
	}
	public void setLib_res(String lib_res) {
		this.lib_res = lib_res;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public ConsSteg getConsSteg() {
		return consSteg;
	}
	public void setConsSteg(ConsSteg consSteg) {
		this.consSteg = consSteg;
	}
	public LconsSteg(long id, int numero, int annee, int mois, int code_res, String lib_res, int qte,
			ConsSteg consSteg) {
		super();
		this.id = id;
		this.numero = numero;
		this.annee = annee;
		this.mois = mois;
		this.code_res = code_res;
		this.lib_res = lib_res;
		this.qte = qte;
		this.consSteg = consSteg;
	}
	public LconsSteg() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LconsSteg [id=" + id + ", numero=" + numero + ", annee=" + annee + ", mois=" + mois + ", code_res="
				+ code_res + ", lib_res=" + lib_res + ", qte=" + qte + ", consSteg=" + consSteg + "]";
	}
	

}
