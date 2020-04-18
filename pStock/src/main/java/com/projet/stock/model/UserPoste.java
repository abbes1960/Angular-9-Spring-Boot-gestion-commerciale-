package com.projet.stock.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "userPoste")
public class UserPoste {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String nom;
	  private String role;
	  private int mat;
	  private int code_dir;
	  private int code_res;
	  private String lib_direction;
	  private String lib_residence;
	  private String pwd;
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
	public int getCode_dir() {
		return code_dir;
	}
	public void setCode_dir(int code_dir) {
		this.code_dir = code_dir;
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
	public UserPoste(long id, String nom, String role, int mat, int code_dir, int code_res, String lib_direction,
			String lib_residence, String pwd) {
		super();
		this.id = id;
		this.nom = nom;
		this.role = role;
		this.mat = mat;
		this.code_dir = code_dir;
		this.code_res = code_res;
		this.lib_direction = lib_direction;
		this.lib_residence = lib_residence;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "UserPoste [id=" + id + ", nom=" + nom + ", role=" + role + ", mat=" + mat + ", code_dir=" + code_dir
				+ ", code_res=" + code_res + ", lib_direction=" + lib_direction + ", lib_residence=" + lib_residence
				+ ", pwd=" + pwd + "]";
	}
	
}
