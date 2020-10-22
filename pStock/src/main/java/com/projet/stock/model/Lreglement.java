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
@Table(name = "lreglement")
public class Lreglement {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String numfac;
	  private float montant;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Reglement reglement;
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
	public String getNumfac() {
		return numfac;
	}
	public void setNumfac(String numfac) {
		this.numfac = numfac;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Reglement getReglement() {
		return reglement;
	}
	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}
	public Lreglement(long id, int numero, String numfac, float montant, Reglement reglement) {
		super();
		this.id = id;
		this.numero = numero;
		this.numfac = numfac;
		this.montant = montant;
		this.reglement = reglement;
	}
	public Lreglement() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lreglement [id=" + id + ", numero=" + numero + ", numfac=" + numfac + ", montant=" + montant
				+ ", reglement=" + reglement + "]";
	}

}
