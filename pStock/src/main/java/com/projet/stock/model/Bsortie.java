package com.projet.stock.model;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "bsortie")
public class Bsortie {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int numbon;
	  private int code;
	  private int mat;
	  private int codres;
	  private Date date_mvt;
	  private String libelle;
	  private float totht;
	  private float tottva;
	  private float totttc;
	  private String lib_direction;
	  private String lib_residence;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "bsortie")
	    @Valid
	    private List<Lbsortie> lbsorties = new ArrayList<>();
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
	public int getNumbon() {
		return numbon;
	}
	public void setNumbon(int numbon) {
		this.numbon = numbon;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getCodres() {
		return codres;
	}
	public void setCode_res(int codres) {
		this.codres = codres;
	}
	public Date getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(Date date_mvt) {
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
	public String getLib_direction() {
		return lib_direction;
	}
	public void setLib_direction(String lib_direction) {
		this.lib_direction = lib_direction;
	}
	public String getLib_residence() {
		return lib_residence;
	}
	public void setLib_residence(String lib_residence) {
		this.lib_residence = lib_residence;
	}
	public List<Lbsortie> getLbsorties() {
		return lbsorties;
	}
	public void setLbsorties(List<Lbsortie> lbsorties) {
		this.lbsorties = lbsorties;
	}
	public Bsortie(long id, int annee, int numero, int numbon, int code, int mat, int codres, Date date_mvt,
			String libelle, float totht, float tottva, float totttc, String lib_direction, String lib_residence,
			@Valid List<Lbsortie> lbsorties) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.numbon = numbon;
		this.code = code;
		this.mat = mat;
		this.codres = codres;
		this.date_mvt = date_mvt;
		this.libelle = libelle;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.lib_direction = lib_direction;
		this.lib_residence = lib_residence;
		this.lbsorties = lbsorties;
	}
	public Bsortie() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Bsortie [id=" + id + ", annee=" + annee + ", numero=" + numero + ", numbon=" + numbon + ", code=" + code
				+ ", mat=" + mat + ", codres=" + codres + ", date_mvt=" + date_mvt + ", libelle=" + libelle
				+ ", totht=" + totht + ", tottva=" + tottva + ", totttc=" + totttc + ", lib_direction=" + lib_direction
				+ ", lib_residence=" + lib_residence + ", lbsorties=" + lbsorties + "]";
	}

}
