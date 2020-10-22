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
@Table(name = "lordre")
public class Lordre {
	@JsonIgnore
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String code;
	  private String Libart;
	  private float qte;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Ordre ordre;
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
	public Ordre getOrdre() {
		return ordre;
	}
	public void setOrdre(Ordre ordre) {
		this.ordre = ordre;
	}
	public Lordre(long id, int numero, String code, String libart, float qte, Ordre ordre) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		Libart = libart;
		this.qte = qte;
		this.ordre = ordre;
	}
	public Lordre() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lordre [id=" + id + ", numero=" + numero + ", code=" + code + ", Libart=" + Libart + ", qte=" + qte
				+ ", ordre=" + ordre + "]";
	}

}
