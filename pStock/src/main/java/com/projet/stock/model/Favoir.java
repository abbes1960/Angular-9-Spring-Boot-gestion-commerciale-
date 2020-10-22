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
@Table(name = "favoir")
public class Favoir {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code;
	  private String lib_four;
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  private LocalDate date_mvt;
	  private String libelle;
	  private float totht;
	  private float tottva;
	  private float totttc;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "favoir")
	    @Valid
	    private List<Lfavoir> lfavoirs = new ArrayList<>();
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
	public String getLib_four() {
		return lib_four;
	}
	public void setLib_four(String lib_four) {
		this.lib_four = lib_four;
	}
	public LocalDate getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(LocalDate date_mvt) {
		this.date_mvt = date_mvt;
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
	public List<Lfavoir> getLfavoirs() {
		return lfavoirs;
	}
	public void setLfavoirs(List<Lfavoir> lfavoirs) {
		this.lfavoirs = lfavoirs;
	}
	public Favoir(long id, int annee, int numero, int code, String lib_four, LocalDate date_mvt, String libelle,
			float totht, float tottva, float totttc, @Valid List<Lfavoir> lfavoirs) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code = code;
		this.lib_four = lib_four;
		this.date_mvt = date_mvt;
		this.libelle = libelle;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.lfavoirs = lfavoirs;
	}
	public Favoir() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Favoir [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code=" + code + ", lib_four="
				+ lib_four + ", date_mvt=" + date_mvt + ", libelle=" + libelle + ", totht=" + totht + ", tottva="
				+ tottva + ", totttc=" + totttc + ", lfavoirs=" + lfavoirs + "]";
	}

}
