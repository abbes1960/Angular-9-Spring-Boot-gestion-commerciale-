package com.projet.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "agent")
public class Agent {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int mat;
	  private String nom;
	  private String code;
	  private String libGrade;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibGrade() {
		return libGrade;
	}
	public void setLibGrade(String libGrade) {
		this.libGrade = libGrade;
	}
	public Agent(long id, int mat, String nom, String code, String libGrade) {
		super();
		this.id = id;
		this.mat = mat;
		this.nom = nom;
		this.code = code;
		this.libGrade = libGrade;
	}
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Agent [id=" + id + ", mat=" + mat + ", nom=" + nom + ", code=" + code + ", libGrade=" + libGrade + "]";
	}

}
