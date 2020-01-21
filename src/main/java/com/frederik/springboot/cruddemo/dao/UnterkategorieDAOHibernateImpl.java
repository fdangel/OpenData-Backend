package com.frederik.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;

@Repository
public class UnterkategorieDAOHibernateImpl implements UnterkategorieDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public UnterkategorieDAOHibernateImpl(EntityManager theEntityManager) {
	entityManager = theEntityManager;
    }

    @Override
    public List<Unterkategorie> findAll() {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// create a query
	Query<Unterkategorie> theQuery = currentSession.createQuery("from Unterkategorie", Unterkategorie.class);

	// execute query and get result list
	List<Unterkategorie> Unterkategorien = theQuery.getResultList();

	// return the results
	return Unterkategorien;
    }

    @Override
    public Unterkategorie findById(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, theId);

	// return the Unterkategorie
	return theUnterkategorie;
    }

    @Override
    public void save(Unterkategorie theUnterkategorie) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// save Unterkategorie

	currentSession.saveOrUpdate(theUnterkategorie);

    }

    @Override
    public void deleteById(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// delete object with primary key
	Query theQuery = currentSession.createQuery("delete from Unterkategorie where id=:unterkategorieid");
	theQuery.setParameter("unterkategorieid", theId);

	theQuery.executeUpdate();
    }

    @Override
    public List<User> findUnterkategorieUser(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, theId);

	// get Unterkategories User
	List<User> theUsers = theUnterkategorie.getUsers();

	// return the Unterkategorie
	return theUsers;
    }

    @Override
    public Oberkategorie findUnterkategoriesOberkategorie(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, theId);

	// get Unterkategories Oberkategorie
	Oberkategorie theOberkategorie = theUnterkategorie.getOberkategorie();

	// return the Unterkategorie
	return theOberkategorie;
    }

    @Override
    public List<Veranstaltung> findUnterkategorieVeranstaltungen(int unterkategorieId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	return theUnterkategorie.getVeranstaltungen();
    }

    @Override
    public void addUnterkategorieVeranstaltung(int unterkategorieId, int veranstaltungId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	theUnterkategorie.addVeranstaltung(theVeranstaltung);
	//theVeranstaltung.setUnterkategorie(theUnterkategorie);

	currentSession.saveOrUpdate(theUnterkategorie);
	currentSession.saveOrUpdate(theVeranstaltung);

    }

    @Override
    public void addUnterkategorieVeranstaltung(int unterkategorieId, Veranstaltung theVeranstaltung) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	theUnterkategorie.addVeranstaltung(theVeranstaltung);
	//theVeranstaltung.setUnterkategorie(theUnterkategorie);

	currentSession.saveOrUpdate(theUnterkategorie);
	currentSession.saveOrUpdate(theVeranstaltung);

    }

    @Override
    public void addUnterkategorieUser(int unterkategorieId, User theUser) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	theUnterkategorie.addUser(theUser);
	//theUser.addUnterkategorie(theUnterkategorie);

	currentSession.saveOrUpdate(theUnterkategorie);
	currentSession.saveOrUpdate(theUser);

    }

    @Override
    public void addUnterkategorieUser(int unterkategorieId, int userId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	// get the Veranstaltung
	User theUser = currentSession.get(User.class, userId);

	theUnterkategorie.addUser(theUser);
	//theUser.addUnterkategorie(theUnterkategorie);

	currentSession.saveOrUpdate(theUnterkategorie);
	currentSession.saveOrUpdate(theUser);

    }

}
