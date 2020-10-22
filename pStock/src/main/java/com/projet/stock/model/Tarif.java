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
@Table(name = "tarif")
public class Tarif {
@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 private int code;
 private double deb;
 private double fin;
 private double montant;
 private String libelle;
 @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 @JsonBackReference
 private Destination destination;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
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
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Destination getDestination() {
	return destination;
}
public void setDestination(Destination destination) {
	this.destination = destination;
}
public Tarif(long id, int code, double deb, double fin, double montant, String libelle, Destination destination) {
	super();
	this.id = id;
	this.code = code;
	this.deb = deb;
	this.fin = fin;
	this.montant = montant;
	this.libelle = libelle;
	this.destination = destination;
}
public Tarif() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Tarif [id=" + id + ", code=" + code + ", deb=" + deb + ", fin=" + fin + ", montant=" + montant
			+ ", libelle=" + libelle + ", destination=" + destination + "]";
}


}
