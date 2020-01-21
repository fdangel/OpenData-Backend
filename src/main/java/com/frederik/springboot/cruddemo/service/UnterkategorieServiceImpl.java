package com.frederik.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frederik.springboot.cruddemo.dao.UnterkategorieDAO;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;

@Service
public class UnterkategorieServiceImpl implements UnterkategorieService {

    private UnterkategorieDAO UnterkategorieDAO;

    @Autowired
    public UnterkategorieServiceImpl(UnterkategorieDAO theUnterkategorieDAO) {
	UnterkategorieDAO = theUnterkategorieDAO;
    }

    @Override
    @Transactional
    public List<Unterkategorie> findAll() {
	return UnterkategorieDAO.findAll();
    }

    @Override
    @Transactional
    public Unterkategorie findById(int theId) {
	return UnterkategorieDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Unterkategorie theUnterkategorie) {
	UnterkategorieDAO.save(theUnterkategorie);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
	UnterkategorieDAO.deleteById(theId);
    }

    @Override
    @Transactional
    public Oberkategorie findUnterkategoriesOberkategorie(int theId) {

	return UnterkategorieDAO.findUnterkategoriesOberkategorie(theId);
    }

    @Override
    @Transactional
    public List<Veranstaltung> findUnterkategorieVeranstaltungen(int unterkategorieId) {

	return UnterkategorieDAO.findUnterkategorieVeranstaltungen(unterkategorieId);
    }

    @Override
    @Transactional
    public List<User> findUnterkategorieUser(int theId) {
	return UnterkategorieDAO.findUnterkategorieUser(theId);
    }

    @Override
    @Transactional
    public void addUnterkategorieUser(int unterkategorieId, User theUser) {

	UnterkategorieDAO.addUnterkategorieUser(unterkategorieId, theUser);

    }

    @Override
    @Transactional
    public void addUnterkategorieUser(int unterkategorieId, int userId) {
	UnterkategorieDAO.addUnterkategorieUser(unterkategorieId, userId);

    }

    @Override
    @Transactional
    public void addUnterkategorieVeranstaltung(int unterkategorieId, int veranstaltungId) {
	UnterkategorieDAO.addUnterkategorieVeranstaltung(unterkategorieId, veranstaltungId);
    }

    @Override
    @Transactional
    public void addUnterkategorieVeranstaltung(int unterkategorieId, Veranstaltung theVeranstaltung) {
	UnterkategorieDAO.addUnterkategorieVeranstaltung(unterkategorieId, theVeranstaltung);

    }

}
