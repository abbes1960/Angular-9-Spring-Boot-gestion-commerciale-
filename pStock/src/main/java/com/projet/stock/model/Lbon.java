package com.projet.stock.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "lbon")
public class Lbon {
	@JsonIgnore
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String code;
	  private String Libart;
	  private float pu;
	  private float qte;
	  private double total;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Bon bon;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Bon getBon() {
		return bon;
	}
	public void setBon(Bon bon) {
		this.bon = bon;
	}
	public Lbon(long id, int numero, String code, String libart, float pu, float qte, double total, Bon bon) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		Libart = libart;
		this.pu = pu;
		this.qte = qte;
		this.total = total;
		this.bon = bon;
	}
	public Lbon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lbon [id=" + id + ", numero=" + numero + ", code=" + code + ", Libart=" + Libart + ", pu=" + pu
				+ ", qte=" + qte + ", total=" + total + ", bon=" + bon + "]";
	}

}
