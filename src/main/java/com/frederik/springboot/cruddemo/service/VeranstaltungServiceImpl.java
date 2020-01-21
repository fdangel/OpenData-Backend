package com.frederik.springboot.cruddemo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frederik.springboot.cruddemo.dao.VeranstaltungDAO;
import com.frederik.springboot.cruddemo.entity.Adresse;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Request;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;
import com.frederik.springboot.repository.VeranstaltungRepository;

@Service
public class VeranstaltungServiceImpl implements VeranstaltungService {

    private VeranstaltungDAO VeranstaltungDAO;
    private VeranstaltungRepository veranstaltungRepository;

    @Autowired
    public VeranstaltungServiceImpl(VeranstaltungDAO theVeranstaltungDAO) {
	VeranstaltungDAO = theVeranstaltungDAO;
    }

    @Override
    @Transactional
    public List<Veranstaltung> findAll() {
	return VeranstaltungDAO.findAll();
    }

    @Override
    @Transactional
    public Veranstaltung findById(int theId) {
	return VeranstaltungDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Veranstaltung theVeranstaltung) {
	VeranstaltungDAO.save(theVeranstaltung);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
	VeranstaltungDAO.deleteById(theId);
    }

    @Override
    @Transactional
    public Veranstalter findVeranstalter(int theId) {
	return VeranstaltungDAO.findVeranstalter(theId);
    }

    @Override
    @Transactional
    public Oberkategorie findVeranstaltungsOberkategorie(int theId) {
	return VeranstaltungDAO.findVeranstaltungsOberkategorie(theId);
    }

    @Override
    @Transactional
    public Unterkategorie findVeranstaltungsUnterkategorie(int theId) {
	return VeranstaltungDAO.findVeranstaltungsUnterkategorie(theId);
    }

    @Override
    @Transactional
    public Adresse findVeranstaltungsAdresse(int theId) {
	return VeranstaltungDAO.findVeranstaltungsAdresse(theId);
    }

    @Override
    @Transactional
    public List<User> findVeranstaltungsBesucher(int theId) {
	return VeranstaltungDAO.findVeranstaltungsBesucher(theId);
    }

    @Override
    @Transactional
    public void addVeranstaltungsBesucherById(int veranstaltungId, int userId) {
	VeranstaltungDAO.addVeranstaltungsBesucherById(veranstaltungId, userId);

    }

    @Override
    @Transactional
    public void addVeranstaltungsBesucher(int veranstaltungId, User theUser) {
	VeranstaltungDAO.addVeranstaltungsBesucher(veranstaltungId, theUser);

    }

    @Override
    @Transactional
    public void addVeranstaltungsOberkategorie(int veranstaltungId, Oberkategorie theOberkategorie) {
	VeranstaltungDAO.addVeranstaltungsOberkategorie(veranstaltungId, theOberkategorie);

    }

    @Override
    @Transactional
    public void addVeranstaltungsUnterkategorie(int veranstaltungId, Unterkategorie theUnterkategorie) {
	VeranstaltungDAO.addVeranstaltungsUnterkategorie(veranstaltungId, theUnterkategorie);

    }

    @Override
    @Transactional
    public void addVeranstaltungsAdresse(int veranstaltungId, Adresse theAdresse) {
	VeranstaltungDAO.addVeranstaltungsAdresse(veranstaltungId, theAdresse);

    }

    @Override
    @Transactional
    public void addVeranstaltungsOberkategorieById(int veranstaltungId, int oberkategorieId) {
	VeranstaltungDAO.addVeranstaltungsOberkategorieById(veranstaltungId, oberkategorieId);

    }

    @Override
    @Transactional
    public void addVeranstaltungsUnterkategorieById(int veranstaltungId, int unterkategorieId) {
	VeranstaltungDAO.addVeranstaltungsUnterkategorieById(veranstaltungId, unterkategorieId);

    }

    @Override
    @Transactional
    public void addVeranstaltungsAdresseById(int veranstaltungId, int adressId) {
	VeranstaltungDAO.addVeranstaltungsAdresseById(veranstaltungId, adressId);

    }

    @Override
    @Transactional
    public void addVeranstaltungsVeranstalterById(int veranstaltungId, int veranstalterId) {
	VeranstaltungDAO.addVeranstaltungsVeranstalterById(veranstaltungId, veranstalterId);

    }

    @Override
    @Transactional
    public void addVeranstaltungsVeranstalter(int veranstaltungId, Veranstalter theVeranstalter) {
	VeranstaltungDAO.addVeranstaltungsVeranstalter(veranstaltungId, theVeranstalter);

    }
    
    @Override
    @Transactional
    public List<Veranstaltung> findHighlights (){
	return VeranstaltungDAO.findHighlights();
    }

    @Override
    public List<Veranstaltung> findFilterVeranstaltung(Request request) {
	return VeranstaltungDAO.findFilterVeranstaltung(request);
    }

    @Override
    public List<Veranstaltung> findFilterVeranstaltung(String oberkategorie, String unterkategorie, Timestamp datefrom,
	    Timestamp dateto, String suche) {
	return veranstaltungRepository.findFilterVeranstaltung(oberkategorie, unterkategorie, datefrom, dateto, suche);
    }

}
