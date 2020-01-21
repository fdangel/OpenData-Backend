package com.frederik.springboot.cruddemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Oberkategorie")
public class Oberkategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oberkategorieid")
    private int oberkategorieid;

   @JsonIgnoreProperties("userOberkategorien")
    @ManyToMany(mappedBy = "userOberkategorien")
    private List<User> users;

    @JsonIgnoreProperties("oberkategorie")
    @OneToMany(mappedBy = "oberkategorie")
    private List<Unterkategorie> unterkategorien;

    //@JsonIgnoreProperties("oberkategorie")
    @JsonIgnore
    @OneToMany(mappedBy = "oberkategorie")
    private List<Veranstaltung> veranstaltungen;

    @Column(name = "oberkategoriename")
    private String oberkategoriename;

    public Oberkategorie() {

    }

    public Oberkategorie(String oberkategoriename) {
	this.oberkategoriename = oberkategoriename;
    }

    public int getoberkategorieid() {
	return oberkategorieid;
    }

    public void setoberkategorieid(int oberkategorieid) {
	this.oberkategorieid = oberkategorieid;
    }

    public String getoberkategoriename() {
	return oberkategoriename;
    }

    public void setoberkategoriename(String oberkategoriename) {
	this.oberkategoriename = oberkategoriename;
    }

    public List<User> getUsers() {
	return users;
    }

    public void setUsers(List<User> users) {
	this.users = users;
    }

    public List<Veranstaltung> getVeranstaltungen() {
	return veranstaltungen;
    }

    public void setVeranstaltungen(List<Veranstaltung> veranstaltungen) {
	this.veranstaltungen = veranstaltungen;
    }

    public void addUser(User theUser) {

	if (users == null) {
	    users = new ArrayList<>();
	}

	users.add(theUser);
	theUser.getUserOberkategorien().add(this);
    }

    public void addUnterkategorie(Unterkategorie theUnterkategorie) {

	if (unterkategorien == null) {
	    unterkategorien = new ArrayList<>();
	}

	unterkategorien.add(theUnterkategorie);
    }

    public void addVeranstaltung(Veranstaltung theVeranstaltung) {

	if (veranstaltungen == null) {
	    veranstaltungen = new ArrayList<>();
	}

	veranstaltungen.add(theVeranstaltung);
    }

    public List<Unterkategorie> getUnterkategorien() {
	return unterkategorien;
    }

    public void setUnterkategorien(List<Unterkategorie> unterkategorien) {
	this.unterkategorien = unterkategorien;
    }

    public Oberkategorie merge(Oberkategorie o) {

	if (o.getoberkategoriename() != null)
	    this.setoberkategoriename(o.getoberkategoriename());

	if (o.getUnterkategorien() != null)
	    this.setUnterkategorien(o.getUnterkategorien());

	if (o.getUsers() != null)
	    this.setUsers(o.getUsers());

	if (o.getVeranstaltungen() != null)
	    this.setVeranstaltungen(o.getVeranstaltungen());

	return this;
    }

}
