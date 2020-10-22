package com.projet.stock.model;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "facture")
public class Facture {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date date_mvt;
	  private int mat;
	  private int code;
	  private String libelle;
	  private String libcl;
	  private int numdep;
	  
	  private int nbre;
	  private double totht;
	  private double tottva;
	  private double timbre;
	  private double totttc;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "facture", fetch=FetchType.EAGER)
    @Valid
	  private List<Lfacture> lfactures = new ArrayList<>();
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
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
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
	public String getLibcl() {
		return libcl;
	}
	public void setLibcl(String libcl) {
		this.libcl = libcl;
	}
	public int getNumdep() {
		return numdep;
	}
	public void setNumdep(int numdep) {
		this.numdep = numdep;
	}
	public int getNbre() {
		return nbre;
	}
	public void setNbre(int nbre) {
		this.nbre = nbre;
	}
	public double getTotht() {
		return totht;
	}
	public void setTotht(double totht) {
		this.totht = totht;
	}
	public double getTottva() {
		return tottva;
	}
	public void setTottva(double tottva) {
		this.tottva = tottva;
	}
	public double getTimbre() {
		return timbre;
	}
	public void setTimbre(double timbre) {
		this.timbre = timbre;
	}
	@Override
	public String toString() {
		return "Facture [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt + ", mat="
				+ mat + ", code=" + code + ", libelle=" + libelle + ", libcl=" + libcl + ", numdep=" + numdep
				+ ", nbre=" + nbre + ", totht=" + totht + ", tottva=" + tottva + ", timbre=" + timbre + ", totttc="
				+ totttc + ", lfactures=" + lfactures + "]";
	}
	public Facture(long id, int annee, int numero, Date date_mvt, int mat, int code, String libelle, String libcl,
			int numdep, int nbre, double totht, double tottva, double timbre, double totttc,
			@Valid List<Lfacture> lfactures) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.date_mvt = date_mvt;
		this.mat = mat;
		this.code = code;
		this.libelle = libelle;
		this.libcl = libcl;
		this.numdep = numdep;
		this.nbre = nbre;
		this.totht = totht;
		this.tottva = tottva;
		this.timbre = timbre;
		this.totttc = totttc;
		this.lfactures = lfactures;
	}
	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getTotttc() {
		return totttc;
	}
	public void setTotttc(double totttc) {
		this.totttc = totttc;
	}
	public List<Lfacture> getLfactures() {
		return lfactures;
	}
	public void setLfactures(List<Lfacture> lfactures) {
		this.lfactures = lfactures;
	}


}
