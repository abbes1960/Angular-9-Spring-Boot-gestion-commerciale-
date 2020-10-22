package com.projet.stock.model;
import java.time.LocalDate;
import java.util.ArrayList;
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
@Table(name = "recouv")
public class Recouv {
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
	  @JsonManagedReference
	    @OneToMany(mappedBy = "recouv")
	    @Valid
	    
	    private List<Lrecouv> lrecouvs = new ArrayList<>();
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
	public List<Lrecouv> getLrecouvs() {
		return lrecouvs;
	}
	public void setLrecouvs(List<Lrecouv> lrecouvs) {
		this.lrecouvs = lrecouvs;
	}
	public Recouv(long id, int annee, int numero, int code, String lib_client, LocalDate date_reg, String libelle,
			float total, @Valid List<Lrecouv> lrecouvs) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code = code;
		this.lib_client = lib_client;
		this.date_reg = date_reg;
		this.libelle = libelle;
		this.total = total;
		this.lrecouvs = lrecouvs;
	}
	public Recouv() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Recouv [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code=" + code + ", lib_client="
				+ lib_client + ", date_reg=" + date_reg + ", libelle=" + libelle + ", total=" + total + ", lrecouvs="
				+ lrecouvs + "]";
	}

}
