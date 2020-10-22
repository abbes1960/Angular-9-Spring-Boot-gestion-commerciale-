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
@Table(name = "departement")
public class Departement {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private long numero;
	  private String code;
	  private String libelle;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Patrimoine patrimoine;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Patrimoine getPatrimoine() {
		return patrimoine;
	}
	public void setPatrimoine(Patrimoine patrimoine) {
		this.patrimoine = patrimoine;
	}
	public Departement(long id, long numero, String code, String libelle, Patrimoine patrimoine) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		this.libelle = libelle;
		this.patrimoine = patrimoine;
	}
	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Departement [id=" + id + ", numero=" + numero + ", code=" + code + ", libelle=" + libelle
				+ ", patrimoine=" + patrimoine + "]";
	}


}
