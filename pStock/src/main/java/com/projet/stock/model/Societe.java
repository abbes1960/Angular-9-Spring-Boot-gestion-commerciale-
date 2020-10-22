package com.projet.stock.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "societe")
public class Societe {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String libelle;
	  private String slibelle;
	  private String adresse;
	  private String tel1;
	  private String tel2;
	  private String fax;
	  private String matf;
	  private String rib;
	  private String banque;
	  private int numc;
	  private int numf;
	  private String registre;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getSlibelle() {
		return slibelle;
	}
	public void setSlibelle(String slibelle) {
		this.slibelle = slibelle;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMatf() {
		return matf;
	}
	public void setMatf(String matf) {
		this.matf = matf;
	}
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public int getNumc() {
		return numc;
	}
	public void setNumc(int numc) {
		this.numc = numc;
	}
	public int getNumf() {
		return numf;
	}
	public void setNumf(int numf) {
		this.numf = numf;
	}
	public String getRegistre() {
		return registre;
	}
	public void setRegistre(String registre) {
		this.registre = registre;
	}
	public Societe(long id, String libelle, String slibelle, String adresse, String tel1, String tel2, String fax,
			String matf, String rib, String banque, int numc, int numf, String registre) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.slibelle = slibelle;
		this.adresse = adresse;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.fax = fax;
		this.matf = matf;
		this.rib = rib;
		this.banque = banque;
		this.numc = numc;
		this.numf = numf;
		this.registre = registre;
	}
	public Societe() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Societe [id=" + id + ", libelle=" + libelle + ", slibelle=" + slibelle + ", adresse=" + adresse
				+ ", tel1=" + tel1 + ", tel2=" + tel2 + ", fax=" + fax + ", matf=" + matf + ", rib=" + rib + ", banque="
				+ banque + ", numc=" + numc + ", numf=" + numf + ", registre=" + registre + "]";
	}

}
