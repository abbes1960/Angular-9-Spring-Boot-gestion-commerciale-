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
@Table(name = "Linvent")
public class Linvent {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String code;
	  private String libart;
	  private int qte1;
	  private int qte2;
	  private int qte3;
	  private int qtef;
	  private float pu;
	  private float total;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Invent invent;
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
	public int getQte1() {
		return qte1;
	}
	public void setQte1(int qte1) {
		this.qte1 = qte1;
	}
	public int getQte2() {
		return qte2;
	}
	public void setQte2(int qte2) {
		this.qte2 = qte2;
	}
	public int getQte3() {
		return qte3;
	}
	public void setQte3(int qte3) {
		this.qte3 = qte3;
	}
	public int getQtef() {
		return qtef;
	}
	public void setQtef(int qtef) {
		this.qtef = qtef;
	}
	public float getPu() {
		return pu;
	}
	public void setPu(float pu) {
		this.pu = pu;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Invent getInvent() {
		return invent;
	}
	public void setInvent(Invent invent) {
		this.invent = invent;
	}
	public Linvent(long id, int numero, String code, String libart, int qte1, int qte2, int qte3, int qtef, float pu,
			float total, Invent invent) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		this.libart = libart;
		this.qte1 = qte1;
		this.qte2 = qte2;
		this.qte3 = qte3;
		this.qtef = qtef;
		this.pu = pu;
		this.total = total;
		this.invent = invent;
	}
	public Linvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Linvent [id=" + id + ", numero=" + numero + ", code=" + code + ", libart=" + libart + ", qte1=" + qte1
				+ ", qte2=" + qte2 + ", qte3=" + qte3 + ", qtef=" + qtef + ", pu=" + pu + ", total=" + total
				+ ", invent=" + invent + "]";
	}
	
}
