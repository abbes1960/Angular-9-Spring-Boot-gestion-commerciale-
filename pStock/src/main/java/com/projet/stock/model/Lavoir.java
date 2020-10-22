package com.projet.stock.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "lavoir")
public class Lavoir {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String code;
	  private String Libart;
	  private float qte;
	  private float pu;
	  private int tva;
	  private int fodec;
	  private float totht;
	  @ManyToOne
	    @JoinColumn
	    private Avoir avoir;
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
	public Avoir getAvoir() {
		return avoir;
	}
	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}
	public Lavoir(long id, int numero, String code, String libart, float qte, float pu, int tva, int fodec, float totht,
			Avoir avoir) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		Libart = libart;
		this.qte = qte;
		this.pu = pu;
		this.tva = tva;
		this.fodec = fodec;
		this.totht = totht;
		this.avoir = avoir;
	}
	public Lavoir() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lavoir [id=" + id + ", numero=" + numero + ", code=" + code + ", Libart=" + Libart + ", qte=" + qte
				+ ", pu=" + pu + ", tva=" + tva + ", fodec=" + fodec + ", totht=" + totht + ", avoir=" + avoir + "]";
	}


	
}
