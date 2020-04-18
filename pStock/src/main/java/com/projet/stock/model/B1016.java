package com.projet.stock.model;
import java.time.LocalDate;
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
@Table(name = "b1016")
public class B1016 {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  private int code_dir;
	  private int code_res;
	  private Date date_mvt;
	  private String libelle;
	  private double total;
	  private String lib_direction;
	  private String lib_residence;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "b1016", fetch=FetchType.EAGER)
      @Valid
	  private List<Lb1016> lb1016s = new ArrayList<>();
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
	public Date getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(Date date_mvt) {
		this.date_mvt = date_mvt;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
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
	public List<Lb1016> getLb1016s() {
		return lb1016s;
	}
	public void setLb1016s(List<Lb1016> lb1016s) {
		this.lb1016s = lb1016s;
	}
	public B1016(long id, int annee, int numero, int code_dir, int code_res, Date date_mvt, String libelle,
			double total, String lib_direction, String lib_residence, @Valid List<Lb1016> lb1016s) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.code_dir = code_dir;
		this.code_res = code_res;
		this.date_mvt = date_mvt;
		this.libelle = libelle;
		this.total = total;
		this.lib_direction = lib_direction;
		this.lib_residence = lib_residence;
		this.lb1016s = lb1016s;
	}
	public B1016() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "B1016 [id=" + id + ", annee=" + annee + ", numero=" + numero + ", code_dir=" + code_dir + ", code_res="
				+ code_res + ", date_mvt=" + date_mvt + ", libelle=" + libelle + ", total=" + total + ", lib_direction="
				+ lib_direction + ", lib_residence=" + lib_residence + ", lb1016s=" + lb1016s + "]";
	}

}
