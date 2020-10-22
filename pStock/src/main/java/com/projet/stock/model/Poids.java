package com.projet.stock.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Poids {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private double deb;
	  private double fin;
	  private double montant;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getDeb() {
		return deb;
	}
	public void setDeb(double deb) {
		this.deb = deb;
	}
	public double getFin() {
		return fin;
	}
	public void setFin(double fin) {
		this.fin = fin;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Poids(long id, double deb, double fin, double montant) {
		super();
		this.id = id;
		this.deb = deb;
		this.fin = fin;
		this.montant = montant;
	}
	public Poids() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Poids [id=" + id + ", deb=" + deb + ", fin=" + fin + ", montant=" + montant + "]";
	}

}
