package com.projet.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "typePatrimoine")
public class TypePatrimoine {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String code;
	  private String libelle;
	  private int rang;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	public TypePatrimoine(long id, String code, String libelle, int rang) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.rang = rang;
	}
	public TypePatrimoine() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TypePatrimoine [id=" + id + ", code=" + code + ", libelle=" + libelle + ", rang=" + rang + "]";
	}

}
