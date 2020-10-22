package com.projet.stock.model;
import java.time.LocalDate;
import java.util.ArrayList;

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
@Table(name = "avoir")
public class Avoir {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code;
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  private LocalDate date_avoir;
	  private String libelle;
	  private float totht;
	  private float totfodec;
	  private float tottva;
	  private float totttc;
	  private String lib_client;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "avoir")
	    @Valid
	    private List<Lavoir> lavoirs = new ArrayList<>();
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
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public LocalDate getDate_avoir() {
		return date_avoir;
	}
	public void setDate_avoir(LocalDate date_avoir) {
		this.date_avoir = date_avoir;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public float getTotht() {
		return totht;
	}
	public void setTotht(float totht) {
		this.totht = totht;
	}
	public float getTotfodec() {
		return totfodec;
	}
	public void setTotfodec(float totfodec) {
		this.totfodec = totfodec;
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
	public String getLib_client() {
		return lib_client;
	}
	public void setLib_client(String lib_client) {
		this.lib_client = lib_client;
	}
	public List<Lavoir> getLavoirs() {
		return lavoirs;
	}
	public void setLavoirs(List<Lavoir> lavoirs) {
		this.lavoirs = lavoirs;
	}
	public Avoir(long id, int annee, int numero, int code, LocalDate date_avoir, String libelle, float totht,
			float totfodec, float tottva, float totttc, String lib_client, @Valid List<Lavoir> lavoirs) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code = code;
		this.date_avoir = date_avoir;
		this.libelle = libelle;
		this.totht = totht;
		this.totfodec = totfodec;
		this.tottva = tottva;
		this.totttc = totttc;
		this.lib_client = lib_client;
		this.lavoirs = lavoirs;
	}
	public Avoir() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Avoir [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code=" + code + ", date_avoir="
				+ date_avoir + ", libelle=" + libelle + ", totht=" + totht + ", totfodec=" + totfodec + ", tottva="
				+ tottva + ", totttc=" + totttc + ", lib_client=" + lib_client + ", lavoirs=" + lavoirs + "]";
	}
	
	}
