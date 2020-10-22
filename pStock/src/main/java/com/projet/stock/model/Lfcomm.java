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
@Table(name = "Lfcomm")
public class Lfcomm {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String code;
	  private String libart;
	  private float pu;
	  private float qte;
	  private int tva;
	  private float rem;
	  private int fodec;
	  private float totht;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Fcomm fcomm;
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
		return libart;
	}
	public void setLibart(String libart) {
		this.libart = libart;
	}
	public float getPu() {
		return pu;
	}
	public void setPu(float pu) {
		this.pu = pu;
	}
	public float getQte() {
		return qte;
	}
	public void setQte(float qte) {
		this.qte = qte;
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
	public Fcomm getFcomm() {
		return fcomm;
	}
	public void setFcomm(Fcomm fcomm) {
		this.fcomm = fcomm;
	}
	public Lfcomm(long id, int numero, String code, String libart, float pu, float qte, int tva, float rem, int fodec,
			float totht, Fcomm fcomm) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		this.libart = libart;
		this.pu = pu;
		this.qte = qte;
		this.tva = tva;
		this.rem = rem;
		this.fodec = fodec;
		this.totht = totht;
		this.fcomm = fcomm;
	}
	public Lfcomm() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lfcomm [id=" + id + ", numero=" + numero + ", code=" + code + ", libart=" + libart + ", pu=" + pu
				+ ", qte=" + qte + ", tva=" + tva + ", rem=" + rem + ", fodec=" + fodec + ", totht=" + totht
				+ ", fcomm=" + fcomm + "]";
	}

}
