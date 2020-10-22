package com.projet.stock.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "ConsSteg")
public class ConsSteg {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private int annee;
	  private int mois;
	  private int mat;
	  private int code;
	  private String lib_direction;
	  private String libelle;
	  private int total;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "consSteg", fetch=FetchType.EAGER)
      @Valid
	  private List<LconsSteg> lconsStegs = new ArrayList<>();
	  
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
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<LconsSteg> getLconsStegs() {
		return lconsStegs;
	}
	public void setLconsStegs(List<LconsSteg> lconsStegs) {
		this.lconsStegs = lconsStegs;
	}
	public ConsSteg(long id, int numero, int annee, int mois, int mat, int code, String lib_direction, String libelle,
			int total, @Valid List<LconsSteg> lconsStegs) {
		super();
		this.id = id;
		this.numero = numero;
		this.annee = annee;
		this.mois = mois;
		this.mat = mat;
		this.code = code;
		this.lib_direction = lib_direction;
		this.libelle = libelle;
		this.total = total;
		this.lconsStegs = lconsStegs;
	}
	public ConsSteg() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ConsSteg [id=" + id + ", numero=" + numero + ", annee=" + annee + ", mois=" + mois + ", mat=" + mat
				+ ", code=" + code + ", lib_direction=" + lib_direction + ", libelle=" + libelle + ", total=" + total
				+ ", lconsStegs=" + lconsStegs + "]";
	}

}
