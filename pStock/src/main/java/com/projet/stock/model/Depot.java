package com.projet.stock.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "depot")
public class Depot {
		@Id
		  @GeneratedValue(strategy = GenerationType.AUTO)
		  private long id;
		  private int annee;
		  private int numero;
		  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
		  private Date date_mvt;
		  private int mat;
		  private int code;
		  private String libelle;
		  private int nbre;
		  private String libcl;
		  private double total;
		  @JsonManagedReference
		  @JsonIgnore
		  @OneToMany(mappedBy = "depot", fetch=FetchType.EAGER)
	      @Valid
		  private List<Ldepot> ldepots = new ArrayList<>();
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
		public Date getDate_mvt() {
			return date_mvt;
		}
		public void setDate_mvt(Date date_mvt) {
			this.date_mvt = date_mvt;
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
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		public int getNbre() {
			return nbre;
		}
		public void setNbre(int nbre) {
			this.nbre = nbre;
		}
		public String getLibcl() {
			return libcl;
		}
		public void setLibcl(String libcl) {
			this.libcl = libcl;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		public List<Ldepot> getLdepots() {
			return ldepots;
		}
		public void setLdepots(List<Ldepot> ldepots) {
			this.ldepots = ldepots;
		}
		public Depot(long id, int annee, int numero, Date date_mvt, int mat, int code, String libelle, int nbre,
				String libcl, double total, @Valid List<Ldepot> ldepots) {
			super();
			this.id = id;
			this.annee = annee;
			this.numero = numero;
			this.date_mvt = date_mvt;
			this.mat = mat;
			this.code = code;
			this.libelle = libelle;
			this.nbre = nbre;
			this.libcl = libcl;
			this.total = total;
			this.ldepots = ldepots;
		}
		public Depot() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Depot [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt + ", mat="
					+ mat + ", code=" + code + ", libelle=" + libelle + ", nbre=" + nbre + ", libcl=" + libcl
					+ ", total=" + total + ", ldepots=" + ldepots + "]";
		}

}
