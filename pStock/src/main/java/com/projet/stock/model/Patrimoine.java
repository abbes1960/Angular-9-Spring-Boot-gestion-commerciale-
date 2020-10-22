package com.projet.stock.model;

import java.sql.Date;
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
@Table(name = "patrimoine")
public class Patrimoine {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String code;
	  private String libelle;
	  private String adresse;
	  private String ville;
	  private String codep;
	  private String typep;
	  private double couvert;
	  private double superficie;
	  private int code_dir;
	  private String  pv;
	  private String  nature;
	  private String contrat;
	  private String proprietaire;
	  private double montant;
	  private String image;
	  private int    annee;
	  private String num;
	  private String lng;
	  private String lat;
	  private String observation;
	  private String lib_direction;
	  private int lgf;
	  private int  chs;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "patrimoine", fetch=FetchType.EAGER)
      @Valid
	  private List<Departement> ldepartements = new ArrayList<>();
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
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodep() {
		return codep;
	}
	public void setCodep(String codep) {
		this.codep = codep;
	}
	public String getTypep() {
		return typep;
	}
	public void setTypep(String typep) {
		this.typep = typep;
	}
	public double getCouvert() {
		return couvert;
	}
	public void setCouvert(double couvert) {
		this.couvert = couvert;
	}
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	public int getCode_dir() {
		return code_dir;
	}
	public void setCode_dir(int code_dir) {
		this.code_dir = code_dir;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getContrat() {
		return contrat;
	}
	public void setContrat(String contrat) {
		this.contrat = contrat;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getLib_direction() {
		return lib_direction;
	}
	public void setLib_direction(String lib_direction) {
		this.lib_direction = lib_direction;
	}
	public int getLgf() {
		return lgf;
	}
	public void setLgf(int lgf) {
		this.lgf = lgf;
	}
	public int getChs() {
		return chs;
	}
	public void setChs(int chs) {
		this.chs = chs;
	}
	public List<Departement> getLdepartements() {
		return ldepartements;
	}
	public void setLdepartements(List<Departement> ldepartements) {
		this.ldepartements = ldepartements;
	}
	public Patrimoine(long id, String code, String libelle, String adresse, String ville, String codep, String typep,
			double couvert, double superficie, int code_dir, String pv, String nature, String contrat,
			String proprietaire, double montant, String image, int annee, String num, String lng, String lat,
			String observation, String lib_direction, int lgf, int chs, @Valid List<Departement> ldepartements) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.adresse = adresse;
		this.ville = ville;
		this.codep = codep;
		this.typep = typep;
		this.couvert = couvert;
		this.superficie = superficie;
		this.code_dir = code_dir;
		this.pv = pv;
		this.nature = nature;
		this.contrat = contrat;
		this.proprietaire = proprietaire;
		this.montant = montant;
		this.image = image;
		this.annee = annee;
		this.num = num;
		this.lng = lng;
		this.lat = lat;
		this.observation = observation;
		this.lib_direction = lib_direction;
		this.lgf = lgf;
		this.chs = chs;
		this.ldepartements = ldepartements;
	}
	public Patrimoine() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Patrimoine [id=" + id + ", code=" + code + ", libelle=" + libelle + ", adresse=" + adresse + ", ville="
				+ ville + ", codep=" + codep + ", typep=" + typep + ", couvert=" + couvert + ", superficie="
				+ superficie + ", code_dir=" + code_dir + ", pv=" + pv + ", nature=" + nature + ", contrat=" + contrat
				+ ", proprietaire=" + proprietaire + ", montant=" + montant + ", image=" + image + ", annee=" + annee
				+ ", num=" + num + ", lng=" + lng + ", lat=" + lat + ", observation=" + observation + ", lib_direction="
				+ lib_direction + ", lgf=" + lgf + ", chs=" + chs + ", ldepartements=" + ldepartements + "]";
	}

}
