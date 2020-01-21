package com.frederik.springboot.cruddemo.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.mapping.Collection;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frederik.springboot.cruddemo.entity.Adresse;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Request;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;

@Repository
public class VeranstaltungDAOHibernateImpl implements VeranstaltungDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public VeranstaltungDAOHibernateImpl(EntityManager theEntityManager) {
	entityManager = theEntityManager;
    }

    @Override
    public List<Veranstaltung> findAll() {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// create a query
	Query<Veranstaltung> theQuery = currentSession.createQuery("from Veranstaltung", Veranstaltung.class);

	// execute query and get result list
	List<Veranstaltung> Veranstaltungn = theQuery.getResultList();

	// return the results
	return Veranstaltungn;
    }

    @Override
    public Veranstaltung findById(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, theId);

	// return the Veranstaltung
	return theVeranstaltung;
    }

    @Override
    public void save(Veranstaltung theVeranstaltung) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// save Veranstaltung
	currentSession.saveOrUpdate(theVeranstaltung);
    }

    @Override
    public void deleteById(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// delete object with primary key
	Query theQuery = currentSession.createQuery("delete from Veranstaltung where id=:veranstaltungid");
	theQuery.setParameter("veranstaltungid", theId);

	theQuery.executeUpdate();
    }

    @Override
    public Oberkategorie findVeranstaltungsOberkategorie(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, theId);

	// get VeranstaltungsOberkategorie
	Oberkategorie theOberkategorie = theVeranstaltung.getOberkategorie();

	return theOberkategorie;
    }

    @Override
    public Unterkategorie findVeranstaltungsUnterkategorie(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, theId);

	// get VeranstaltungsUnterkategorie

	Unterkategorie theUnterkategorie = theVeranstaltung.getUnterkategorie();

	return theUnterkategorie;
    }

    @Override
    public Adresse findVeranstaltungsAdresse(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, theId);

	// get VeranstaltungsAdresse
	Adresse theAdresse = theVeranstaltung.getAdresse();

	return theAdresse;

    }

    @Override
    public List<User> findVeranstaltungsBesucher(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, theId);

	// get VeranstaltungsBesucher
	List<User> users = theVeranstaltung.getVeranstaltungsBesucher();

	return users;
    }

    @Override
    public void addVeranstaltungsOberkategorieById(int veranstaltungId, int oberkategorieId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	// get the Oberkategorie
	Oberkategorie theOberkategorie = currentSession.get(Oberkategorie.class, oberkategorieId);

	theVeranstaltung.setOberkategorie(theOberkategorie);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theOberkategorie);

    }

    @Override
    public void addVeranstaltungsUnterkategorieById(int veranstaltungId, int unterkategorieId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	theVeranstaltung.setUnterkategorie(theUnterkategorie);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theUnterkategorie);

    }

    @Override
    public void addVeranstaltungsAdresseById(int veranstaltungId, int adressId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	// get the Adresse
	Adresse theAdresse = currentSession.get(Adresse.class, adressId);

	theVeranstaltung.setAdresse(theAdresse);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theAdresse);

    }

    @Override
    public void addVeranstaltungsBesucherById(int veranstaltungId, int userId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	// get the User
	User theUser = currentSession.get(User.class, userId);

	theVeranstaltung.addUser(theUser);
	// theUser.addVeranstaltung(theVeranstaltung);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theUser);

    }

    @Override
    public void addVeranstaltungsBesucher(int veranstaltungId, User theUser) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	theVeranstaltung.addUser(theUser);
	// theUser.addVeranstaltung(theVeranstaltung);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theUser);

    }

    @Override
    public Veranstalter findVeranstalter(int theId) {
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, theId);

	return theVeranstaltung.getVeranstalter();
    }

    @Override
    public void addVeranstaltungsVeranstalterById(int veranstaltungId, int veranstalterId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	// get the Adresse
	Veranstalter theVeranstalter = currentSession.get(Veranstalter.class, veranstalterId);

	theVeranstaltung.setVeranstalter(theVeranstalter);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theVeranstalter);

    }

    @Override
    public void addVeranstaltungsVeranstalter(int veranstaltungId, Veranstalter theVeranstalter) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	theVeranstaltung.setVeranstalter(theVeranstalter);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theVeranstalter);
    }

    @Override
    public void addVeranstaltungsOberkategorie(int veranstaltungId, Oberkategorie theOberkategorie) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	theVeranstaltung.setOberkategorie(theOberkategorie);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theOberkategorie);

    }

    @Override
    public void addVeranstaltungsUnterkategorie(int veranstaltungId, Unterkategorie theUnterkategorie) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	theVeranstaltung.setUnterkategorie(theUnterkategorie);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theUnterkategorie);

    }

    @Override
    public void addVeranstaltungsAdresse(int veranstaltungId, Adresse theAdresse) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	theVeranstaltung.setAdresse(theAdresse);

	currentSession.saveOrUpdate(theVeranstaltung);
	currentSession.saveOrUpdate(theAdresse);
    }

    @Override
    public List<Veranstaltung> findHighlights() {
	/// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// create a query
	Query<Veranstaltung> theQuery = currentSession.createQuery("from Veranstaltung where isthighlight = true",
		Veranstaltung.class);

	// execute query and get result list
	List<Veranstaltung> Veranstaltungn = theQuery.getResultList();

	// return the results
	return Veranstaltungn;

    }

    @Override
    public List<Veranstaltung> findFilterVeranstaltung(Request request) {
	/// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	String oberkategorie = request.getOberkategorie();
	String unterkategorie = request.getUnterkategorie();
	Timestamp datefrom = request.getDatefrom();
	Timestamp dateto = request.getDateto();
	String suche = request.getSuche();

	
	Query<Veranstaltung> theQuery = currentSession.createNativeQuery("select * from veranstaltung v "
    	+ "where v.datefrom =:datefrom"
    	+ " or v.dateto =:dateto"
    	+ " or v.titel =:suche"
    	+ " or v.oberkategorieid = ( select o.oberkategorieid from oberkategorie o where o.oberkategoriename =:oberkategorie) "
    	+ " or v.unterkategorieid = ( select u.unterkategorieid from unterkategorie u where u.unterkategoriename =:unterkategorie)"
    	+ "", Veranstaltung.class);
	
	theQuery.setParameter("oberkategorie", oberkategorie);
	theQuery.setParameter("unterkategorie", unterkategorie);
	theQuery.setParameter("datefrom", datefrom);
	theQuery.setParameter("dateto", dateto);
	theQuery.setParameter("suche", suche);
	

	// execute query and get result list
	List<Veranstaltung> Veranstaltungn = theQuery.getResultList();


	// return the results
	return Veranstaltungn;
	
	
	
	
    }
    
    

}
