package com.projet.stock.model;

import java.util.Date;

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
@Table(name = "ldepot")
public class Ldepot {
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
	  private double montht;
	  private double monttva;
	  private double montttc;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Depot depot;
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
	public double getMontht() {
		return montht;
	}
	public void setMontht(double montht) {
		this.montht = montht;
	}
	public double getMonttva() {
		return monttva;
	}
	public void setMonttva(double monttva) {
		this.monttva = monttva;
	}
	public double getMontttc() {
		return montttc;
	}
	public void setMontttc(double montttc) {
		this.montttc = montttc;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public Ldepot(long id, int annee, int numero, int num, String libelle, int code, double poids, String sms,
			double montht, double monttva, double montttc, Depot depot) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.num = num;
		this.libelle = libelle;
		this.code = code;
		this.poids = poids;
		this.sms = sms;
		this.montht = montht;
		this.monttva = monttva;
		this.montttc = montttc;
		this.depot = depot;
	}
	public Ldepot() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Ldepot [id=" + id + ", annee=" + annee + ", numero=" + numero + ", num=" + num + ", libelle=" + libelle
				+ ", code=" + code + ", poids=" + poids + ", sms=" + sms + ", montht=" + montht + ", monttva=" + monttva
				+ ", montttc=" + montttc + ", depot=" + depot + "]";
	}

}
