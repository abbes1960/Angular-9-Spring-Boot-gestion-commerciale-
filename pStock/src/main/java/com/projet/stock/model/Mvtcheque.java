package com.projet.stock.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "mvtcheque")
public class Mvtcheque {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	  private int annee;
	  private int numero;
	  private Date date_mvt;
	  private String num_compte;
	  private int numdeb;
	  private int nbre;
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
	public String getNum_compte() {
		return num_compte;
	}
	public void setNum_compte(String num_compte) {
		this.num_compte = num_compte;
	}
	public int getNumdeb() {
		return numdeb;
	}
	public void setNumdeb(int numdeb) {
		this.numdeb = numdeb;
	}
	public int getNbre() {
		return nbre;
	}
	public void setNbre(int nbre) {
		this.nbre = nbre;
	}
	public Mvtcheque(long id, int annee, int numero, Date date_mvt, String num_compte, int numdeb, int nbre) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.date_mvt = date_mvt;
		this.num_compte = num_compte;
		this.numdeb = numdeb;
		this.nbre = nbre;
	}
	public Mvtcheque() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mvtcheque [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt
				+ ", num_compte=" + num_compte + ", numdeb=" + numdeb + ", nbre=" + nbre + "]";
	}


}
