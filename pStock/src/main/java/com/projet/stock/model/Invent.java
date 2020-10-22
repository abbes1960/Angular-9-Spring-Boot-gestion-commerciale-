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
@Table(name = "invent")
public class Invent {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code;
	  private Date date_invent;
	  private String lib_direction;
	  private String libelle;
	  private float total;
	  @JsonManagedReference
	    @OneToMany(mappedBy = "invent")
	    @Valid
	    private List<Linvent> linvents = new ArrayList<>();
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
	public Date getDate_invent() {
		return date_invent;
	}
	public void setDate_invent(Date date_invent) {
		this.date_invent = date_invent;
	}
	public String getLib_direction() {
		return lib_direction;
	}
	public void setLib_direction(String lib_direction) {
		this.lib_direction = lib_direction;
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
	public List<Linvent> getLinvents() {
		return linvents;
	}
	public void setLinvents(List<Linvent> linvents) {
		this.linvents = linvents;
	}
	public Invent(long id, int annee, int numero, int code, Date date_invent, String lib_direction, String libelle,
			float total, @Valid List<Linvent> linvents) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code = code;
		this.date_invent = date_invent;
		this.lib_direction = lib_direction;
		this.libelle = libelle;
		this.total = total;
		this.linvents = linvents;
	}
	public Invent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Invent [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code=" + code + ", date_invent="
				+ date_invent + ", lib_direction=" + lib_direction + ", libelle=" + libelle + ", total=" + total
				+ ", linvents=" + linvents + "]";
	}


}
