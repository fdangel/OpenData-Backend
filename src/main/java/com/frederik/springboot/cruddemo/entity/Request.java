package com.frederik.springboot.cruddemo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/*
@Entity
@Table(name="request")
@SecondaryTable(name = "oberkategorie", pkJoinColumns =  @PrimaryKeyJoinColumn(name = "oberkategorieid"))
@SecondaryTable(name = "unterkategorie", pkJoinColumns =  @PrimaryKeyJoinColumn(name = "unterkategorieid"))
@SecondaryTable(name = "veranstaltung", pkJoinColumns =  @PrimaryKeyJoinColumn(name = "veranstaltungid"))
*/
public class Request {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    */
    //@PrimaryKeyJoinColumn(name = "oberkategorieid")
    private String oberkategorie;
    
    //@PrimaryKeyJoinColumn(name = "unterkategorieid")
    private String unterkategorie;
    
    //@PrimaryKeyJoinColumn(name = "veranstaltungid")
    private Timestamp datefrom;
    
    //@PrimaryKeyJoinColumn(name = "veranstaltungid")
    private Timestamp dateto;
    
    //@PrimaryKeyJoinColumn(name = "veranstaltungid")
    private String suche;
    
    public Request() {}

    public String getOberkategorie() {
        return oberkategorie;
    }

    public void setOberkategorie(String oberkategorie) {
        this.oberkategorie = oberkategorie;
    }

    public String getUnterkategorie() {
        return unterkategorie;
    }

    public void setUnterkategorie(String unterkategorie) {
        this.unterkategorie = unterkategorie;
    }

    public Timestamp getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(Timestamp datefrom) {
        this.datefrom = datefrom;
    }

    public Timestamp getDateto() {
        return dateto;
    }

    public void setDateto(Timestamp dateto) {
        this.dateto = dateto;
    }

    public String getSuche() {
        return suche;
    }

    public void setSuche(String suche) {
        this.suche = suche;
    };
    
    

}
