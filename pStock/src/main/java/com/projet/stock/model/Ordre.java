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
@Table(name = "ordre")
public class Ordre {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int mat;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date date_mvt;
	  private String libelle;
    @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "ordre", fetch=FetchType.EAGER)
    @Valid
	  private List<Lordre> lordres = new ArrayList<>();
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
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
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
	public List<Lordre> getLordres() {
		return lordres;
	}
	public void setLordres(List<Lordre> lordres) {
		this.lordres = lordres;
	}
	public Ordre(long id, int annee, int numero, int mat, Date date_mvt, String libelle, @Valid List<Lordre> lordres) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.mat = mat;
		this.date_mvt = date_mvt;
		this.libelle = libelle;
		this.lordres = lordres;
	}
	public Ordre() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Ordre [id=" + id + ", annee=" + annee + ", numero=" + numero + ", mat=" + mat + ", date_mvt=" + date_mvt
				+ ", libelle=" + libelle + ", lordres=" + lordres + "]";
	}	

}
