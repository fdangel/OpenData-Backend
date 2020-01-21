package com.frederik.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.frederik.springboot.cruddemo.dao.UnterkategorieDAO;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;
import com.frederik.springboot.cruddemo.service.OberkategorieService;
import com.frederik.springboot.cruddemo.service.OberkategorieServiceImpl;
import com.frederik.springboot.cruddemo.service.UnterkategorieService;

import ch.qos.logback.core.net.server.Client;

@RestController
@RequestMapping("/api")
public class UnterkategorieRestController {

    private UnterkategorieService UnterkategorieService;

    @Autowired
    public UnterkategorieRestController(UnterkategorieService theUnterkategorieService) {
	UnterkategorieService = theUnterkategorieService;
    }

    // expose "/Unterkategories" and return list of Unterkategories
    @GetMapping("/Unterkategorie")
    public List<Unterkategorie> findAll() {
	return UnterkategorieService.findAll();
    }

    // add mapping for GET /Unterkategories/{UnterkategorieId}

    @GetMapping("/Unterkategorie/{UnterkategorieId}")
    public Unterkategorie getUnterkategorie(@PathVariable int UnterkategorieId) {

	Unterkategorie theUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (theUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	}

	return theUnterkategorie;
    }

    // add mapping for POST /Unterkategories - add new Unterkategorie

    @PostMapping("/Unterkategorie")
    public Unterkategorie addUnterkategorie(@RequestBody Unterkategorie theUnterkategorie) {

	//theUnterkategorie.setUnterkategorieID(0);
	UnterkategorieService.save(theUnterkategorie);
	return theUnterkategorie;
    }

    // add mapping for PUT /Unterkategories - update existing Unterkategorie

    @PutMapping("/Unterkategorie")
    public Unterkategorie updateUnterkategorie(@RequestBody Unterkategorie theUnterkategorie) {

	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(theUnterkategorie.getUnterkategorieID());
	tempUnterkategorie.merge(theUnterkategorie);

	UnterkategorieService.save(tempUnterkategorie);

	return tempUnterkategorie;
    }

    // add mapping for DELETE /Unterkategories/{UnterkategorieId} - delete
    // Unterkategorie

    @DeleteMapping("/Unterkategorie/{UnterkategorieId}")
    public String deleteUnterkategorie(@PathVariable int UnterkategorieId) {

	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	// throw exception if null

	if (tempUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	}

	UnterkategorieService.deleteById(UnterkategorieId);

	return "Deleted Unterkategorie id - " + UnterkategorieId;
    }

    @GetMapping("/Unterkategorie/Oberkategorie/{UnterkategorieId}")
    public Oberkategorie getOberkategorie(@PathVariable int UnterkategorieId) {

	Unterkategorie theUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (theUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	    Oberkategorie theOberkategorie = UnterkategorieService.findUnterkategoriesOberkategorie(UnterkategorieId);

	    if (theOberkategorie == null) {
		throw new UnterkategorieNotFoundException("Unterkategorie  - " + UnterkategorieId + "hat keine Oberkategorie");
	    }

	    return theOberkategorie;
	}
    }

    @GetMapping("/Unterkategorie/User/{UnterkategorieId}")
    public List<User> getUnterkategorieUsers(@PathVariable int UnterkategorieId) {

	Unterkategorie theUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (theUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	    List<User> users = UnterkategorieService.findUnterkategorieUser(UnterkategorieId);

	    if (users == null) {
		throw new UnterkategorieNotFoundException("Unterkategorie  - " + UnterkategorieId + "hat keine User");
	    }

	    return users;
	}
    }
    
    @GetMapping("/Unterkategorie/Veranstaltung/{UnterkategorieId}")
    public List<Veranstaltung> getUnterkategorieVeranstaltungen(@PathVariable int UnterkategorieId) {

	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (tempUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	    List<Veranstaltung> veranstaltungen = UnterkategorieService.findUnterkategorieVeranstaltungen(UnterkategorieId);

	    if (veranstaltungen == null) {
		throw new UnterkategorieNotFoundException("Unterkategorie  - " + UnterkategorieId + "hat keine Veranstaltungen");
	    }

	    return veranstaltungen;
	}
    }
    
    @PostMapping("/Unterkategorie/Veranstaltung/{UnterkategorieId}")
    public List<Veranstaltung> addUnterkategorieVeranstaltung(@PathVariable int UnterkategorieId,
	    @RequestBody Veranstaltung theVeranstaltung) {
	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (tempUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	    
	    UnterkategorieService.addUnterkategorieVeranstaltung(UnterkategorieId, theVeranstaltung);
	    return tempUnterkategorie.getVeranstaltungen();
	}
    }
    
    @PostMapping("/Unterkategorie/Veranstaltung/{UnterkategorieId}/{veranstaltungId}")
    public List<Veranstaltung> addUnterkategorieVeranstaltungById(@PathVariable int UnterkategorieId,
	    @PathVariable int veranstaltungId) {
	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (tempUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	UnterkategorieService.addUnterkategorieVeranstaltung(UnterkategorieId, veranstaltungId);
	return UnterkategorieService.findUnterkategorieVeranstaltungen(veranstaltungId);}
    }
    
    @PostMapping("/Unterkategorie/User/{UnterkategorieId}")
    public List<User> addUnterkategorieUser(@PathVariable int UnterkategorieId, @RequestBody User theUser) {

	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (tempUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	    UnterkategorieService.addUnterkategorieUser(UnterkategorieId, theUser);
	    return tempUnterkategorie.getUsers();
	}
    }
    
    @PostMapping("/Unterkategorie/User/{UnterkategorieId}/{userId}")
    public List<User> addUnterkategorieUserById(@PathVariable int UnterkategorieId, @PathVariable int userId) {
	Unterkategorie tempUnterkategorie = UnterkategorieService.findById(UnterkategorieId);

	if (tempUnterkategorie == null) {
	    throw new UnterkategorieNotFoundException("Unterkategorie id not found - " + UnterkategorieId);
	} else {
	UnterkategorieService.addUnterkategorieUser(UnterkategorieId, userId);
	return UnterkategorieService.findUnterkategorieUser(userId);}
    }
    
    


}
