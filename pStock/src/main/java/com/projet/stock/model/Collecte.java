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
@Table(name = "collecte")
public class Collecte {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date date_mvt;
	  private int mat;
	  private int codcli;
	  private String libelle;
	  private int nbre;
	  private double totht;
	  private double tottva;
	  private double totttc;
	   @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "collecte", fetch=FetchType.EAGER)
      @Valid
	  private List<Lcollecte> lcollectes = new ArrayList<>();
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
	public int getCodcli() {
		return codcli;
	}
	public void setCodcli(int codcli) {
		this.codcli = codcli;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
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
	public double getTotttc() {
		return totttc;
	}
	public void setTotttc(double totttc) {
		this.totttc = totttc;
	}
	public List<Lcollecte> getLcollectes() {
		return lcollectes;
	}
	public void setLcollectes(List<Lcollecte> lcollectes) {
		this.lcollectes = lcollectes;
	}
	public Collecte(long id, int annee, int numero, Date date_mvt, int mat, int codcli, String libelle, int nbre,
			double totht, double tottva, double totttc, @Valid List<Lcollecte> lcollectes) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.date_mvt = date_mvt;
		this.mat = mat;
		this.codcli = codcli;
		this.libelle = libelle;
		this.nbre = nbre;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.lcollectes = lcollectes;
	}
	public Collecte() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Collecte [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt + ", mat="
				+ mat + ", codcli=" + codcli + ", libelle=" + libelle + ", nbre=" + nbre + ", totht=" + totht
				+ ", tottva=" + tottva + ", totttc=" + totttc + ", lcollectes=" + lcollectes + "]";
	}

}
