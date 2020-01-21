package com.frederik.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frederik.springboot.cruddemo.dao.UserDAO;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO UserDAO;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
	UserDAO = theUserDAO;
    }

    @Override
    @Transactional
    public List<User> findAll() {
	return UserDAO.findAll();
    }

    @Override
    @Transactional
    public User findById(int theId) {
	return UserDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(User theUser) {
	UserDAO.save(theUser);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
	UserDAO.deleteById(theId);
    }

    @Override
    @Transactional
    public List<Veranstaltung> findUserVeranstaltungen(int userId) {

	return UserDAO.findUserVeranstaltungen(userId);
    }

    @Override
    @Transactional
    public List<Veranstalter> findUserAlsVeranstalter(int userId) {

	return UserDAO.findUserAlsVeranstalter(userId);
    }

    @Override
    @Transactional
    public List<Oberkategorie> findUserOberkategorien(int userId) {

	return UserDAO.findUserOberkategorien(userId);
    }

    @Override
    @Transactional
    public List<Unterkategorie> findUserUnterkategorien(int userId) {

	return UserDAO.findUserUnterkategorien(userId);
    }

    @Override
    @Transactional
    public void addUserVeranstaltungById(int userId, int veranstaltungId) {
	UserDAO.addUserVeranstaltungById(userId, veranstaltungId);
	
    }

    @Override
    @Transactional
    public void addUserAlsVeranstalterById(int userId, int veranstalterId) {
	UserDAO.addUserAlsVeranstalterById( userId,  veranstalterId);
	
    }

    @Override
    @Transactional
    public void addUserOberkategorieById(int userId, int oberkategorieId) {
	UserDAO.addUserOberkategorieById(userId, oberkategorieId);
    }

    @Override
    @Transactional
    public void addUserUnterkategorieById(int userId, int unterkategorieId) {
	UserDAO.addUserUnterkategorieById(userId, unterkategorieId);
    }

    @Override
    @Transactional
    public void addUserVeranstaltung(int userId, Veranstaltung theVeranstaltung) {
	UserDAO.addUserVeranstaltung(userId, theVeranstaltung);
	
    }

    @Override
    @Transactional
    public void addUserAlsVeranstalter(int userId, Veranstalter theVeranstalter) {
	UserDAO.addUserAlsVeranstalter(userId, theVeranstalter);
	
    }

    @Override
    @Transactional
    public void addUserOberkategorie(int userId, Oberkategorie theOberkategorie) {
	UserDAO.addUserOberkategorie(userId, theOberkategorie);
	
    }

    @Override
    @Transactional
    public void addUserUnterkategorie(int userId, Unterkategorie theUnterkategorie) {
	UserDAO.addUserUnterkategorie(userId, theUnterkategorie);
	
    }

    @Override
    public User findUserByMailAndPW(String email, String pw) {
	

	return UserDAO.findUserByMailAndPW(email, pw);
    }

    @Override
    public boolean existiertMail(String email) {
	
	return (UserDAO.existiertMail(email)!=null);
    }

}
