package com.projet.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "cposte")
public class Cposte {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	private int annee;
	private int code_dir;
	private int numblf;
	private int numcommf;
	private int numavf;
	private int numbon;
	private int numbs;
	private int numbr;
	private int numfacf;
	private int numinv;
	private int numpre;
	private int numpro;
	private int numcsonede;
	private int numcsteg;
	private int numccarburant;
	private int numcollecte;
	private int numdepot;
	private int numreg;
	private int numfact;
	private int numenvoi;
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
	public int getCode_dir() {
		return code_dir;
	}
	public void setCode_dir(int code_dir) {
		this.code_dir = code_dir;
	}
	public int getNumblf() {
		return numblf;
	}
	public void setNumblf(int numblf) {
		this.numblf = numblf;
	}
	public int getNumcommf() {
		return numcommf;
	}
	public void setNumcommf(int numcommf) {
		this.numcommf = numcommf;
	}
	public int getNumavf() {
		return numavf;
	}
	public void setNumavf(int numavf) {
		this.numavf = numavf;
	}
	public int getNumbon() {
		return numbon;
	}
	public void setNumbon(int numbon) {
		this.numbon = numbon;
	}
	public int getNumbs() {
		return numbs;
	}
	public void setNumbs(int numbs) {
		this.numbs = numbs;
	}
	public int getNumbr() {
		return numbr;
	}
	public void setNumbr(int numbr) {
		this.numbr = numbr;
	}
	public int getNumfacf() {
		return numfacf;
	}
	public void setNumfacf(int numfacf) {
		this.numfacf = numfacf;
	}
	public int getNuminv() {
		return numinv;
	}
	public void setNuminv(int numinv) {
		this.numinv = numinv;
	}
	public int getNumpre() {
		return numpre;
	}
	public void setNumpre(int numpre) {
		this.numpre = numpre;
	}
	public int getNumpro() {
		return numpro;
	}
	public void setNumpro(int numpro) {
		this.numpro = numpro;
	}
	public int getNumcsonede() {
		return numcsonede;
	}
	public void setNumcsonede(int numcsonede) {
		this.numcsonede = numcsonede;
	}
	public int getNumcsteg() {
		return numcsteg;
	}
	public void setNumcsteg(int numcsteg) {
		this.numcsteg = numcsteg;
	}
	public int getNumccarburant() {
		return numccarburant;
	}
	public void setNumccarburant(int numccarburant) {
		this.numccarburant = numccarburant;
	}
	public int getNumcollecte() {
		return numcollecte;
	}
	public void setNumcollecte(int numcollecte) {
		this.numcollecte = numcollecte;
	}
	public int getNumdepot() {
		return numdepot;
	}
	public void setNumdepot(int numdepot) {
		this.numdepot = numdepot;
	}
	public int getNumreg() {
		return numreg;
	}
	public void setNumreg(int numreg) {
		this.numreg = numreg;
	}
	public int getNumfact() {
		return numfact;
	}
	public void setNumfact(int numfact) {
		this.numfact = numfact;
	}
	public int getNumenvoi() {
		return numenvoi;
	}
	public void setNumenvoi(int numenvoi) {
		this.numenvoi = numenvoi;
	}
	public Cposte(long id, int annee, int code_dir, int numblf, int numcommf, int numavf, int numbon, int numbs,
			int numbr, int numfacf, int numinv, int numpre, int numpro, int numcsonede, int numcsteg, int numccarburant,
			int numcollecte, int numdepot, int numreg, int numfact, int numenvoi) {
		super();
		this.id = id;
		this.annee = annee;
		this.code_dir = code_dir;
		this.numblf = numblf;
		this.numcommf = numcommf;
		this.numavf = numavf;
		this.numbon = numbon;
		this.numbs = numbs;
		this.numbr = numbr;
		this.numfacf = numfacf;
		this.numinv = numinv;
		this.numpre = numpre;
		this.numpro = numpro;
		this.numcsonede = numcsonede;
		this.numcsteg = numcsteg;
		this.numccarburant = numccarburant;
		this.numcollecte = numcollecte;
		this.numdepot = numdepot;
		this.numreg = numreg;
		this.numfact = numfact;
		this.numenvoi = numenvoi;
	}
	public Cposte() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cposte [id=" + id + ", annee=" + annee + ", code_dir=" + code_dir + ", numblf=" + numblf + ", numcommf="
				+ numcommf + ", numavf=" + numavf + ", numbon=" + numbon + ", numbs=" + numbs + ", numbr=" + numbr
				+ ", numfacf=" + numfacf + ", numinv=" + numinv + ", numpre=" + numpre + ", numpro=" + numpro
				+ ", numcsonede=" + numcsonede + ", numcsteg=" + numcsteg + ", numccarburant=" + numccarburant
				+ ", numcollecte=" + numcollecte + ", numdepot=" + numdepot + ", numreg=" + numreg + ", numfact="
				+ numfact + ", numenvoi=" + numenvoi + "]";
	}


}
