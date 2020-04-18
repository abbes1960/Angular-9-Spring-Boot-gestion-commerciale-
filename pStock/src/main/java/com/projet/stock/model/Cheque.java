package com.projet.stock.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "cheque")
public class Cheque {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numcc;
	  private int numero;
	  private String four;
	  private Date echeance;
	  private Date date_reg;
	  private float montant;
	  private String etat;
	  private Date date_etat;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumcc() {
		return numcc;
	}
	public void setNumcc(int numcc) {
		this.numcc = numcc;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getFour() {
		return four;
	}
	public void setFour(String four) {
		this.four = four;
	}
	public Date getEcheance() {
		return echeance;
	}
	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getDate_etat() {
		return date_etat;
	}
	public void setDate_etat(Date date_etat) {
		this.date_etat = date_etat;
	}
	public Cheque(long id, int numcc, int numero, String four, Date echeance, Date date_reg, float montant, String etat,
			Date date_etat) {
		super();
		this.id = id;
		this.numcc = numcc;
		this.numero = numero;
		this.four = four;
		this.echeance = echeance;
		this.date_reg = date_reg;
		this.montant = montant;
		this.etat = etat;
		this.date_etat = date_etat;
	}
	public Cheque() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cheque [id=" + id + ", numcc=" + numcc + ", numero=" + numero + ", four=" + four + ", echeance="
				+ echeance + ", date_reg=" + date_reg + ", montant=" + montant + ", etat=" + etat + ", date_etat="
				+ date_etat + "]";
	}
	  
	  
	 
	

}
