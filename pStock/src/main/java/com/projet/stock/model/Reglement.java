package com.projet.stock.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reglement")
public class Reglement {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code;
	  private String lib_client;
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  private LocalDate date_reg;
	  private String libelle;
	  private float total;
	  private String mode_reg;
	  private String nump;
	  private String banque;
	  private Date echeance;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "reglement")
	    @Valid
	    private List<Lreglement> lreglements = new ArrayList<>();
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
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLib_client() {
		return lib_client;
	}
	public void setLib_client(String lib_client) {
		this.lib_client = lib_client;
	}
	public LocalDate getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(LocalDate date_reg) {
		this.date_reg = date_reg;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getMode_reg() {
		return mode_reg;
	}
	public void setMode_reg(String mode_reg) {
		this.mode_reg = mode_reg;
	}
	public String getNump() {
		return nump;
	}
	public void setNump(String nump) {
		this.nump = nump;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public Date getEcheance() {
		return echeance;
	}
	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}
	public List<Lreglement> getLreglements() {
		return lreglements;
	}
	public void setLreglements(List<Lreglement> lreglements) {
		this.lreglements = lreglements;
	}
	public Reglement(long id, int annee, int numero, int code, String lib_client, LocalDate date_reg, String libelle,
			float total, String mode_reg, String nump, String banque, Date echeance,
			@Valid List<Lreglement> lreglements) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code = code;
		this.lib_client = lib_client;
		this.date_reg = date_reg;
		this.libelle = libelle;
		this.total = total;
		this.mode_reg = mode_reg;
		this.nump = nump;
		this.banque = banque;
		this.echeance = echeance;
		this.lreglements = lreglements;
	}
	public Reglement() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Reglement [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code=" + code + ", lib_client="
				+ lib_client + ", date_reg=" + date_reg + ", libelle=" + libelle + ", total=" + total + ", mode_reg="
				+ mode_reg + ", nump=" + nump + ", banque=" + banque + ", echeance=" + echeance + ", lreglements="
				+ lreglements + "]";
	}

}
