package com.projet.stock.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "residence")
public class Residence {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int code;
	  private int coddir;
	  private String libelle;
	  private String Libdir;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCoddir() {
		return coddir;
	}
	public void setCoddir(int coddir) {
		this.coddir = coddir;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getLibdir() {
		return Libdir;
	}
	public void setLibdir(String libdir) {
		Libdir = libdir;
	}
	public Residence(long id, int code, int coddir, String libelle, String libdir) {
		super();
		this.id = id;
		this.code = code;
		this.coddir = coddir;
		this.libelle = libelle;
		Libdir = libdir;
	}
	public Residence() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Residence [id=" + id + ", code=" + code + ", coddir=" + coddir + ", libelle=" + libelle + ", Libdir="
				+ Libdir + "]";
	}

	
}
