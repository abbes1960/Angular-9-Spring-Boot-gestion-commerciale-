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
@Entity
@Table(name = "destination")
public class Destination {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private int code;
private String libelle;
@JsonIgnore
@OneToMany(mappedBy = "destination", fetch=FetchType.EAGER)
@Valid
private List<Tarif> ltarifs = new ArrayList<>();
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
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public List<Tarif> getLtarifs() {
	return ltarifs;
}
public void setLtarifs(List<Tarif> ltarifs) {
	this.ltarifs = ltarifs;
}
public Destination(long id, int code, String libelle, @Valid List<Tarif> ltarifs) {
	super();
	this.id = id;
	this.code = code;
	this.libelle = libelle;
	this.ltarifs = ltarifs;
}
@Override
public String toString() {
	return "Destination [id=" + id + ", code=" + code + ", libelle=" + libelle + ", ltarifs=" + ltarifs + "]";
}
public Destination() {
	super();
	// TODO Auto-generated constructor stub
}


}
