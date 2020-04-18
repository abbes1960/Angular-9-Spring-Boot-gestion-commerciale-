package com.projet.stock.model;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "livr")
public class Livr {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code_client;
	  private String lib_client;
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  private LocalDate date_liv;
	  private String libelle;
	  private float totht;
	  private float totrem;
	  private float totfodec;
	  private float tottva;
	  private float totttc;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "livr", fetch=FetchType.EAGER)
      @Valid
	  private List<Llivr> llivrs = new ArrayList<>();
	  
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
	public int getCode_client() {
		return code_client;
	}
	public void setCode_client(int code_client) {
		this.code_client = code_client;
	}
	public String getLib_client() {
		return lib_client;
	}
	public void setLib_client(String lib_client) {
		this.lib_client = lib_client;
	}
	public LocalDate getDate_liv() {
		return date_liv;
	}
	public void setDate_liv(LocalDate date_liv) {
		this.date_liv = date_liv;
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
	public float getTotrem() {
		return totrem;
	}
	public void setTotrem(float totrem) {
		this.totrem = totrem;
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
	public List<Llivr> getLlivrs() {
		return llivrs;
	}
	public void setLlivrs(List<Llivr> llivrs) {
		this.llivrs = llivrs;
	}
	public Livr(long id, int annee, int numero, int code_client, String lib_client, LocalDate date_liv, String libelle,
			float totht, float totrem, float totfodec, float tottva, float totttc, @Valid List<Llivr> llivrs) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code_client = code_client;
		this.lib_client = lib_client;
		this.date_liv = date_liv;
		this.libelle = libelle;
		this.totht = totht;
		this.totrem = totrem;
		this.totfodec = totfodec;
		this.tottva = tottva;
		this.totttc = totttc;
		this.llivrs = llivrs;
	}
	public Livr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Livr [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code_client=" + code_client
				+ ", lib_client=" + lib_client + ", date_liv=" + date_liv + ", libelle=" + libelle + ", totht=" + totht
				+ ", totrem=" + totrem + ", totfodec=" + totfodec + ", tottva=" + tottva + ", totttc=" + totttc
				+ ", llivrs=" + llivrs + "]";
	}


}
