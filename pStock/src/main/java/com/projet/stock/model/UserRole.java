package com.projet.stock.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "userRole")
public class UserRole {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private long iduser;
	  private long idrole;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIduser() {
		return iduser;
	}
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public long getIdrole() {
		return idrole;
	}
	public void setIdrole(long idrole) {
		this.idrole = idrole;
	}
	public UserRole(long iduser, long idrole) {
		super();
		this.iduser = iduser;
		this.idrole = idrole;
	}
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", iduser=" + iduser + ", idrole=" + idrole + "]";
	}



}
