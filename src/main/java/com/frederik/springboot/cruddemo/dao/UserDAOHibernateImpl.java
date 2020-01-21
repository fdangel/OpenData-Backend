package com.frederik.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Selection;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;

@Repository
public class UserDAOHibernateImpl implements UserDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public UserDAOHibernateImpl(EntityManager theEntityManager) {
	entityManager = theEntityManager;
    }

    @Override
    public List<User> findAll() {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// create a query
	Query<User> theQuery = currentSession.createQuery("from User", User.class);

	// execute query and get result list
	List<User> Usern = theQuery.getResultList();

	// return the results
	return Usern;
    }

    @Override
    public User findById(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the User
	User theUser = currentSession.get(User.class, theId);

	// return the User
	return theUser;
    }

    @Override
    public void save(User theUser) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// save User
	currentSession.saveOrUpdate(theUser);
    }

    @Override
    public void deleteById(int theId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// delete object with primary key
	Query theQuery = currentSession.createQuery("delete from User where id=:userid");
	theQuery.setParameter("userid", theId);

	theQuery.executeUpdate();
    }

    @Override
    public List<Veranstaltung> findUserVeranstaltungen(int userId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the User
	User theUser = currentSession.get(User.class, userId);

	// get Users Veranstaltungen

	List<Veranstaltung> theVeranstaltungen = theUser.getVeranstaltungsBesucher();

	// return Users Veranstaltungen

	return theVeranstaltungen;
    }

    @Override
    public List<Veranstalter> findUserAlsVeranstalter(int userId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the User
	User theUser = currentSession.get(User.class, userId);

	// get Veranstalter, denen User zugeordnet ist
	List<Veranstalter> theVeranstalters = theUser.getVeranstaltendeUser();

	// return Veranstalter, denen User zugeordnet ist
	return theVeranstalters;
    }

    @Override
    public List<Oberkategorie> findUserOberkategorien(int userId) {

	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the User
	User theUser = currentSession.get(User.class, userId);

	// get Users Oberkategorien
	List<Oberkategorie> usersOberkategories = theUser.getUserOberkategorien();
	return usersOberkategories;
    }

    @Override
    public List<Unterkategorie> findUserUnterkategorien(int userId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);

	// get the User
	User theUser = currentSession.get(User.class, userId);

	// get Users Oberkategorien
	List<Unterkategorie> usersUnterkategories = theUser.getUserUnterkategorien();
	return usersUnterkategories;
    }

    @Override
    public void addUserVeranstaltungById(int userId, int veranstaltungId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);
	
	User theUser = currentSession.get(User.class, userId);

	// get the Veranstaltung
	Veranstaltung theVeranstaltung = currentSession.get(Veranstaltung.class, veranstaltungId);

	// add the veranstaltung to the user and vice versa
	theUser.addVeranstaltung(theVeranstaltung);
	//theVeranstaltung.addUser(theUser);

	currentSession.saveOrUpdate(theUser);
	currentSession.saveOrUpdate(theVeranstaltung);

    }

    @Override
    public void addUserAlsVeranstalterById(int userId, int veranstalterId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);
	// get the User
	User theUser = currentSession.get(User.class, userId);
	// get the Veranstalter
	Veranstalter theVeranstalter = currentSession.get(Veranstalter.class, veranstalterId);

	theUser.addVeranstalter(theVeranstalter);
	//theVeranstalter.addUser(theUser);

	currentSession.saveOrUpdate(theUser);
	currentSession.saveOrUpdate(theVeranstalter);

    }

    @Override
    public void addUserOberkategorieById(int userId, int oberkategorieId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);
	// get the User
	User theUser = currentSession.get(User.class, userId);
	// get the Oberkategorie
	Oberkategorie theOberkategorie = currentSession.get(Oberkategorie.class, oberkategorieId);

	theUser.addOberkategorie(theOberkategorie);
	//theOberkategorie.addUser(theUser);

	currentSession.saveOrUpdate(theUser);
	currentSession.saveOrUpdate(theOberkategorie);

    }

    @Override
    public void addUserUnterkategorieById(int userId, int unterkategorieId) {
	// get the current hibernate session
	Session currentSession = entityManager.unwrap(Session.class);
	// get the User
	User theUser = currentSession.get(User.class, userId);
	// get the Unterkategorie
	Unterkategorie theUnterkategorie = currentSession.get(Unterkategorie.class, unterkategorieId);

	theUser.addUnterkategorie(theUnterkategorie);
	//theUnterkategorie.addUser(theUser);

	currentSession.saveOrUpdate(theUser);
	currentSession.saveOrUpdate(theUnterkategorie);

    }

    @Override
    public void addUserVeranstaltung(int userId, Veranstaltung theVeranstaltung) {
	// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		User theUser = currentSession.get(User.class, userId);

		// add the veranstaltung to the user and vice versa
		theUser.addVeranstaltung(theVeranstaltung);
		//theVeranstaltung.addUser(theUser);

		currentSession.saveOrUpdate(theUser);
		currentSession.saveOrUpdate(theVeranstaltung);
	
    }

    @Override
    public void addUserAlsVeranstalter(int userId, Veranstalter theVeranstalter) {
	// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// get the User
		User theUser = currentSession.get(User.class, userId);
		

		theUser.addVeranstalter(theVeranstalter);
		//theVeranstalter.addUser(theUser);

		currentSession.saveOrUpdate(theUser);
		currentSession.saveOrUpdate(theVeranstalter);
	
    }

    @Override
    public void addUserOberkategorie(int userId, Oberkategorie theOberkategorie) {
	// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// get the User
		User theUser = currentSession.get(User.class, userId);
		

		theUser.addOberkategorie(theOberkategorie);
		//theOberkategorie.addUser(theUser);

		currentSession.saveOrUpdate(theUser);
		currentSession.saveOrUpdate(theOberkategorie);

	
    }

    @Override
    public void addUserUnterkategorie(int userId, Unterkategorie theUnterkategorie) {
	// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// get the User
		User theUser = currentSession.get(User.class, userId);
		

		theUser.addUnterkategorie(theUnterkategorie);
		//theUnterkategorie.addUser(theUser);

		currentSession.saveOrUpdate(theUser);
		currentSession.saveOrUpdate(theUnterkategorie);
	
    }
    
    
    @Override
    public User findUserByMailAndPW (String email, String pw) {
	/// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<User> theQuery = currentSession.createQuery("select u from User u where u.email=:email and u.pw=:pw", User.class);
		
		theQuery.setParameter("email", email);
		theQuery.setParameter("pw", pw);
		// execute query and get result list
		User theUser= theQuery.getSingleResult();
		
		// return the results
		return theUser;
    }

    @Override
    public User existiertMail(String email) {
	Session currentSession = entityManager.unwrap(Session.class);

	// create a query
	Query<User> theQuery;
	User theUser = null;
	try { theQuery = currentSession.createQuery("select u from User u where u.email=:email", User.class);
	theQuery.setParameter("email", email);
	// execute query and get result list
	theUser= theQuery.getSingleResult();}
	catch (NoResultException n) {
	    
	    return theUser;
	}
	
	
	return theUser;
    }
    
  

    

}
