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
	  private String code;
	  private String code_dir;
	  private String libelle;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_dir() {
		return code_dir;
	}
	public void setCode_dir(String code_dir) {
		this.code_dir = code_dir;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Residence(long id, String code, String code_dir, String libelle) {
		super();
		this.id = id;
		this.code = code;
		this.code_dir = code_dir;
		this.libelle = libelle;
	}
	public Residence() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Residence [id=" + id + ", code=" + code + ", code_dir=" + code_dir + ", libelle=" + libelle + "]";
	}
	

}
