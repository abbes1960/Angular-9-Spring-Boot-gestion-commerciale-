package com.projet.stock.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lcollecte")
public class Lcollecte {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
      private int num;
	  private String libelle;
	  private int code;
	  private double poids;
	  private String sms;
	  private String lib_dist;
	  private double montant;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Collecte collecte;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public String getLib_dist() {
		return lib_dist;
	}
	public void setLib_dist(String lib_dist) {
		this.lib_dist = lib_dist;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Collecte getCollecte() {
		return collecte;
	}
	public void setCollecte(Collecte collecte) {
		this.collecte = collecte;
	}
	public Lcollecte(long id, int annee, int numero, int num, String libelle, int code, double poids, String sms,
			String lib_dist, double montant, Collecte collecte) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.num = num;
		this.libelle = libelle;
		this.code = code;
		this.poids = poids;
		this.sms = sms;
		this.lib_dist = lib_dist;
		this.montant = montant;
		this.collecte = collecte;
	}
	public Lcollecte() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lcollecte [id=" + id + ", annee=" + annee + ", numero=" + numero + ", num=" + num + ", libelle="
				+ libelle + ", code=" + code + ", poids=" + poids + ", sms=" + sms + ", lib_dist=" + lib_dist
				+ ", montant=" + montant + ", collecte=" + collecte + "]";
	}

}
