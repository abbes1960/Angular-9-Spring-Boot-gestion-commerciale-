package com.projet.stock.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "devis")
public class Devis {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private Date date_mvt;
	  private int code;
	  private String libelle;
	  private String lib_client;
	  private float totht;
	  private float tottva;
	  private float totttc;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "devis")
	    @Valid
	    private List<Ldevis> ldeviss = new ArrayList<>();
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
	public Date getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(Date date_mvt) {
		this.date_mvt = date_mvt;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getLib_client() {
		return lib_client;
	}
	public void setLib_client(String lib_client) {
		this.lib_client = lib_client;
	}
	public float getTotht() {
		return totht;
	}
	public void setTotht(float totht) {
		this.totht = totht;
	}
	public float getTottva() {
		return tottva;
	}
	public void setTottva(float tottva) {
		this.tottva = tottva;
	}
	public float getTotttc() {
		return totttc;
	}
	public void setTotttc(float totttc) {
		this.totttc = totttc;
	}
	public List<Ldevis> getLdeviss() {
		return ldeviss;
	}
	public void setLdeviss(List<Ldevis> ldeviss) {
		this.ldeviss = ldeviss;
	}
	public Devis(long id, int annee, int numero, Date date_mvt, int code, String libelle, String lib_client,
			float totht, float tottva, float totttc, @Valid List<Ldevis> ldeviss) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.date_mvt = date_mvt;
		this.code = code;
		this.libelle = libelle;
		this.lib_client = lib_client;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.ldeviss = ldeviss;
	}
	public Devis() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Devis [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt + ", code="
				+ code + ", libelle=" + libelle + ", lib_client=" + lib_client + ", totht=" + totht + ", tottva="
				+ tottva + ", totttc=" + totttc + ", ldeviss=" + ldeviss + "]";
	}

}
