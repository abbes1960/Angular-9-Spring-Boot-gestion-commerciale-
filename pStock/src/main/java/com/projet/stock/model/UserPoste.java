package com.projet.stock.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "userPoste")
public class UserPoste {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String nom;
	  private String role;
	  private int mat;
	  private int code;
	  private int code_res;
	  private String lib_direction;
	  private String lib_residence;
	  private String pwd;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date date_mvt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public int getCode_res() {
		return code_res;
	}
	public void setCode_res(int code_res) {
		this.code_res = code_res;
	}
	public String getLib_direction() {
		return lib_direction;
	}
	public void setLib_direction(String lib_direction) {
		this.lib_direction = lib_direction;
	}
	public String getLib_residence() {
		return lib_residence;
	}
	public void setLib_residence(String lib_residence) {
		this.lib_residence = lib_residence;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(Date date_mvt) {
		this.date_mvt = date_mvt;
	}
	public UserPoste() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPoste(long id, String nom, String role, int mat, int code, int code_res, String lib_direction,
			String lib_residence, String pwd, Date date_mvt) {
		super();
		this.id = id;
		this.nom = nom;
		this.role = role;
		this.mat = mat;
		this.code = code;
		this.code_res = code_res;
		this.lib_direction = lib_direction;
		this.lib_residence = lib_residence;
		this.pwd = pwd;
		this.date_mvt = date_mvt;
	}
	@Override
	public String toString() {
		return "UserPoste [id=" + id + ", nom=" + nom + ", role=" + role + ", mat=" + mat + ", code=" + code
				+ ", code_res=" + code_res + ", lib_direction=" + lib_direction + ", lib_residence=" + lib_residence
				+ ", pwd=" + pwd + ", date_mvt=" + date_mvt + "]";
	}

	
}
