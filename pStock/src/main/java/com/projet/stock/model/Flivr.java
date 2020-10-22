package com.projet.stock.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "flivr")
public class Flivr {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code;
	  private String lib_four;
	  private int coddir;
	  private String lib_direction;
	  private Date date_fact;
	  private String libelle;
	  private float totht;
	  private float totrem;
	  private float totfodec;
	  private float tottva;
	  private float totttc;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "flivr")
	    @Valid
	    private List<Lflivr> lflivrs = new ArrayList<>();
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
	public int getCoddir() {
		return coddir;
	}
	public void setCoddir(int coddir) {
		this.coddir = coddir;
	}
	public String getLib_direction() {
		return lib_direction;
	}
	public void setLib_direction(String lib_direction) {
		this.lib_direction = lib_direction;
	}
	public Date getDate_fact() {
		return date_fact;
	}
	public void setDate_fact(Date date_fact) {
		this.date_fact = date_fact;
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
	public List<Lflivr> getLflivrs() {
		return lflivrs;
	}
	public void setLflivrs(List<Lflivr> lflivrs) {
		this.lflivrs = lflivrs;
	}
	public Flivr(long id, int annee, int numero, int code, String lib_four, int coddir, String lib_direction,
			Date date_fact, String libelle, float totht, float totrem, float totfodec, float tottva, float totttc,
			@Valid List<Lflivr> lflivrs) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code = code;
		this.lib_four = lib_four;
		this.coddir = coddir;
		this.lib_direction = lib_direction;
		this.date_fact = date_fact;
		this.libelle = libelle;
		this.totht = totht;
		this.totrem = totrem;
		this.totfodec = totfodec;
		this.tottva = tottva;
		this.totttc = totttc;
		this.lflivrs = lflivrs;
	}
	public Flivr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Flivr [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code=" + code + ", lib_four="
				+ lib_four + ", coddir=" + coddir + ", lib_direction=" + lib_direction + ", date_fact=" + date_fact
				+ ", libelle=" + libelle + ", totht=" + totht + ", totrem=" + totrem + ", totfodec=" + totfodec
				+ ", tottva=" + tottva + ", totttc=" + totttc + ", lflivrs=" + lflivrs + "]";
	}

}
