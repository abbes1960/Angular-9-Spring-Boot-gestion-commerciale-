package com.projet.stock.model;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "ffact")
public class Ffact {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code_four;
	  private String lib_four;
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  private LocalDate date_fact;
	  private String libelle;
	  private float totht;
	  private float totrem;
	  private float totfodec;
	  private float tottva;
	  private float timbre;
	  private float totttc;
	  private float rs;
	  private float montrs;
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
	public int getCode_four() {
		return code_four;
	}
	public void setCode_four(int code_four) {
		this.code_four = code_four;
	}
	public String getLib_four() {
		return lib_four;
	}
	public void setLib_four(String lib_four) {
		this.lib_four = lib_four;
	}
	public LocalDate getDate_fact() {
		return date_fact;
	}
	public void setDate_fact(LocalDate date_fact) {
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
	public float getTimbre() {
		return timbre;
	}
	public void setTimbre(float timbre) {
		this.timbre = timbre;
	}
	public float getTotttc() {
		return totttc;
	}
	public void setTotttc(float totttc) {
		this.totttc = totttc;
	}
	public float getRs() {
		return rs;
	}
	public void setRs(float rs) {
		this.rs = rs;
	}
	public float getMontrs() {
		return montrs;
	}
	public void setMontrs(float montrs) {
		this.montrs = montrs;
	}
	public Ffact(long id, int annee, int numero, int code_four, String lib_four, LocalDate date_fact, String libelle,
			float totht, float totrem, float totfodec, float tottva, float timbre, float totttc, float rs,
			float montrs) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code_four = code_four;
		this.lib_four = lib_four;
		this.date_fact = date_fact;
		this.libelle = libelle;
		this.totht = totht;
		this.totrem = totrem;
		this.totfodec = totfodec;
		this.tottva = tottva;
		this.timbre = timbre;
		this.totttc = totttc;
		this.rs = rs;
		this.montrs = montrs;
	}
	public Ffact() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Ffact [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code_four=" + code_four
				+ ", lib_four=" + lib_four + ", date_fact=" + date_fact + ", libelle=" + libelle + ", totht=" + totht
				+ ", totrem=" + totrem + ", totfodec=" + totfodec + ", tottva=" + tottva + ", timbre=" + timbre
				+ ", totttc=" + totttc + ", rs=" + rs + ", montrs=" + montrs + "]";
	}

}
