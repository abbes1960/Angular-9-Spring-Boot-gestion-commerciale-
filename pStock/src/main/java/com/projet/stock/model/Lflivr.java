package com.projet.stock.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lflivr")
public class Lflivr {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String code;
	  private String Libart;
	  private float qte;
	  private float pu;
	  private int tva;
	  private float rem;
	  private int fodec;
	  private float totht;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Flivr flivr;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibart() {
		return Libart;
	}
	public void setLibart(String libart) {
		Libart = libart;
	}
	public float getQte() {
		return qte;
	}
	public void setQte(float qte) {
		this.qte = qte;
	}
	public float getPu() {
		return pu;
	}
	public void setPu(float pu) {
		this.pu = pu;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public float getRem() {
		return rem;
	}
	public void setRem(float rem) {
		this.rem = rem;
	}
	public int getFodec() {
		return fodec;
	}
	public void setFodec(int fodec) {
		this.fodec = fodec;
	}
	public float getTotht() {
		return totht;
	}
	public void setTotht(float totht) {
		this.totht = totht;
	}
	public Flivr getFlivr() {
		return flivr;
	}
	public void setFlivr(Flivr flivr) {
		this.flivr = flivr;
	}
	public Lflivr(long id, int numero, String code, String libart, float qte, float pu, int tva, float rem, int fodec,
			float totht, Flivr flivr) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		Libart = libart;
		this.qte = qte;
		this.pu = pu;
		this.tva = tva;
		this.rem = rem;
		this.fodec = fodec;
		this.totht = totht;
		this.flivr = flivr;
	}
	public Lflivr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lflivr [id=" + id + ", numero=" + numero + ", code=" + code + ", Libart=" + Libart + ", qte=" + qte
				+ ", pu=" + pu + ", tva=" + tva + ", rem=" + rem + ", fodec=" + fodec + ", totht=" + totht + ", flivr="
				+ flivr + "]";
	}

}
