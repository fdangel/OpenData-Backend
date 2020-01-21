package com.frederik.springboot.cruddemo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frederik.springboot.cruddemo.entity.Email;
import com.frederik.springboot.cruddemo.entity.Login;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;
import com.frederik.springboot.cruddemo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService UserService;

    @Autowired
    public UserRestController(UserService theUserService) {
	UserService = theUserService;
    }

    // expose "/Users" and return list of Users
    @GetMapping("/User")
    public List<User> findAll() {
	return UserService.findAll();
    }

    // add mapping for GET /Users/{UserId}

    @GetMapping("/User/{UserId}")
    public User getUser(@PathVariable int UserId) {

	User theUser = UserService.findById(UserId);

	if (theUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	}

	return theUser;
    }

    // add mapping for POST /Users - add new User

    @PostMapping("/User")
    public User addUser(@RequestBody User theUser) {

	// also just in case they pass an id in JSON ... set id to 0
	// this is to force a save of new item ... instead of update

	//theUser.setUserID(0);
	
	boolean dbUser = UserService.existiertMail(theUser.getEmail());
	
	if (dbUser == true) {throw new RuntimeException("Email existiert bereits");} else

	UserService.save(theUser);
	

	return theUser;
    }

    // add mapping for PUT /Users - update existing User

    @PutMapping("/User")
    public User updateUser(@RequestBody User theUser) {
	User tempUser = UserService.findById(theUser.getUserID());
	tempUser.merge(theUser);
	UserService.save(tempUser);

	return tempUser;
    }

    // add mapping for DELETE /Users/{UserId} - delete User

    @DeleteMapping("/User/{UserId}")
    public String deleteUser(@PathVariable int UserId) {

	User tempUser = UserService.findById(UserId);

	// throw exception if null

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	}

	UserService.deleteById(UserId);

	return "Deleted User id - " + UserId;
    }

    @GetMapping("/User/Veranstaltung/{UserId}")
    public List<Veranstaltung> getUserVeranstaltungen(@PathVariable int UserId) {

	User theUser = UserService.findById(UserId);

	if (theUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	} else {
	    List<Veranstaltung> theVeranstaltungen = UserService.findUserVeranstaltungen(UserId);

	    if (theVeranstaltungen == null)
		throw new UserNotFoundException("User - " + UserId + "nimmt an keiner Veranstaltung teil.");

	    return theVeranstaltungen;
	}
    }

    @GetMapping("/User/Veranstalter/{UserId}")
    public List<Veranstalter> getUserAlsVeranstalter(@PathVariable int UserId) {

	User theUser = UserService.findById(UserId);

	if (theUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	} else {
	    List<Veranstalter> theVeranstalters = UserService.findUserAlsVeranstalter(UserId);

	    if (theVeranstalters == null)
		throw new UserNotFoundException("User - " + UserId + "ist kein Veranstalter.");

	    return theVeranstalters;
	}
    }

    @GetMapping("/User/Oberkategorie/{UserId}")
    public List<Oberkategorie> getUserOberkategorien(@PathVariable int UserId) {

	User theUser = UserService.findById(UserId);

	if (theUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	} else {
	    List<Oberkategorie> theOberkategories = UserService.findUserOberkategorien(UserId);

	    if (theOberkategories == null)
		throw new UserNotFoundException("User - " + UserId + "besitzt keine Oberkategorien.");

	    return theOberkategories;
	}
    }

    @GetMapping("/User/Unterkategorie/{UserId}")
    public List<Unterkategorie> getUserUnterkategorien(@PathVariable int UserId) {

	User theUser = UserService.findById(UserId);

	if (theUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	} else {
	    List<Unterkategorie> theUnterkategories = UserService.findUserUnterkategorien(UserId);

	    if (theUnterkategories == null)
		throw new UserNotFoundException("User - " + UserId + "besitzt keine Unterkategorien.");

	    return theUnterkategories;
	}
    }

    @PostMapping("/User/Veranstaltung/{UserId}")
    public List<Veranstaltung> addUserVeranstaltung(@PathVariable int UserId, @RequestBody Veranstaltung theVeranstaltung) {
	User tempUser = UserService.findById(UserId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	} else {

	    UserService.addUserVeranstaltung(UserId, theVeranstaltung);
	    return UserService.findUserVeranstaltungen(UserId);
	}
    }

    @PostMapping("/User/Veranstalter/{userId}")
    public List<Veranstalter> addUserAlsVeranstalter(@PathVariable int userId, @RequestBody Veranstalter theVeranstalter) {
	User tempUser = UserService.findById(userId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + userId);
	} else {
	    UserService.addUserAlsVeranstalter(userId, theVeranstalter);
	    return UserService.findUserAlsVeranstalter(userId);
	}
    }

    @PostMapping("/User/Oberkategorie/{userId}")
    public List<Oberkategorie> addUserOberkategorie(@PathVariable int userId, @RequestBody Oberkategorie theOberkategorie) {
	User tempUser = UserService.findById(userId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + userId);
	} else {
	    UserService.addUserOberkategorie(userId, theOberkategorie);
	    return UserService.findUserOberkategorien(userId);
	}

    }

    @PostMapping("/User/Unterkategorie/{userId}")
    public List<Unterkategorie> addUserUnterkategorie(@PathVariable int userId, @RequestBody Unterkategorie theUnterkategorie) {
	User tempUser = UserService.findById(userId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + userId);
	} else {
	    UserService.addUserUnterkategorie(userId, theUnterkategorie);
	    return UserService.findUserUnterkategorien(userId);
	}

    }

    @PostMapping("/User/Veranstaltung/{UserId}/{VeranstaltungId}")
    public List<Veranstaltung> addUserVeranstaltungById(@PathVariable int UserId, @PathVariable int VeranstaltungId) {
	User tempUser = UserService.findById(UserId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + UserId);
	} else {
	    UserService.addUserVeranstaltungById(UserId, VeranstaltungId);
	    return UserService.findUserVeranstaltungen(UserId);
	}
    }

    @PostMapping("/User/Veranstalter/{userId}/{veranstalterId}")
    public List<Veranstalter> addUserAlsVeranstalterById(@PathVariable int userId, @PathVariable int veranstalterId) {
	User tempUser = UserService.findById(userId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + userId);
	} else {
	    UserService.addUserAlsVeranstalterById(userId, veranstalterId);
	    return UserService.findUserAlsVeranstalter(userId);
	}
    }

    @PostMapping("/User/Oberkategorie/{userId}/{oberkategorieId}")
    public List<Oberkategorie> addUserOberkategorieById(@PathVariable int userId, @PathVariable int oberkategorieId) {
	User tempUser = UserService.findById(userId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + userId);
	} else {
	    UserService.addUserOberkategorieById(userId, oberkategorieId);
	    return UserService.findUserOberkategorien(userId);
	}
    }

    @PostMapping("/User/Unterkategorie/{userId}/{unterkategorieId}")
    public List<Unterkategorie> addUserUnterkategorieById(@PathVariable int userId,
	    @PathVariable int unterkategorieId) {
	User tempUser = UserService.findById(userId);

	if (tempUser == null) {
	    throw new UserNotFoundException("User id not found - " + userId);
	} else {
	    UserService.addUserUnterkategorieById(userId, unterkategorieId);
	    return UserService.findUserUnterkategorien(userId);
	}
    }
    
    @GetMapping("/User/ByPW")
    public User findUserByMailAndPW (@RequestBody Login login ) {
	String pw = login.getPw();
	String email = login.getEmail();
	User theUser = UserService.findUserByMailAndPW(email, pw);
	if (theUser != null) {return theUser;} else throw new UserNotFoundException("Login fehlgeschlagen");
    }
    
    // deprecated; nur zum prüfen; ob User mit Email existiert wird bereits geprüft;
    @GetMapping("/User/Existiert")
    public boolean existiertMail ( @RequestBody Email email) {
	String mail = email.getEmail();
	return  UserService.existiertMail(mail);
	//return (theUser != null);
    }

}
