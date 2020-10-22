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
@Table(name = "ConsSonede")
public class ConsSonede {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private int annee;
	  private int mois;
	  private int code;
	  private int mat;
	  private int total;
	  private String lib_direction;
	  private String libelle;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "consSonede", fetch=FetchType.EAGER)
      @Valid
	  private List<LconsSonede> lconsSonedes = new ArrayList<>();
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
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
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
	public List<LconsSonede> getLconsSonedes() {
		return lconsSonedes;
	}
	public void setLconsSonedes(List<LconsSonede> lconsSonedes) {
		this.lconsSonedes = lconsSonedes;
	}
	public ConsSonede(long id, int numero, int annee, int mois, int code, int mat, int total, String lib_direction,
			String libelle, @Valid List<LconsSonede> lconsSonedes) {
		super();
		this.id = id;
		this.numero = numero;
		this.annee = annee;
		this.mois = mois;
		this.code = code;
		this.mat = mat;
		this.total = total;
		this.lib_direction = lib_direction;
		this.libelle = libelle;
		this.lconsSonedes = lconsSonedes;
	}
	public ConsSonede() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ConsSonede [id=" + id + ", numero=" + numero + ", annee=" + annee + ", mois=" + mois + ", code=" + code
				+ ", mat=" + mat + ", total=" + total + ", lib_direction=" + lib_direction + ", libelle=" + libelle
				+ ", lconsSonedes=" + lconsSonedes + "]";
	}


}
