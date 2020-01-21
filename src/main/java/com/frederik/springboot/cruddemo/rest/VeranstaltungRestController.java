package com.frederik.springboot.cruddemo.rest;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frederik.springboot.cruddemo.entity.Adresse;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Request;
import com.frederik.springboot.cruddemo.entity.Unterkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;
import com.frederik.springboot.cruddemo.service.VeranstaltungService;
import com.frederik.springboot.repository.VeranstaltungRepository;

@RestController
@RequestMapping("/api")
public class VeranstaltungRestController {

    private VeranstaltungService VeranstaltungService;

    @Autowired
    public VeranstaltungRestController(VeranstaltungService theVeranstaltungService) {
	VeranstaltungService = theVeranstaltungService;
    }

    // expose "/Veranstaltungs" and return list of Veranstaltungs
    @GetMapping("/Veranstaltung")
    public List<Veranstaltung> findAll() {
	return VeranstaltungService.findAll();
    }

    // add mapping for GET /Veranstaltungs/{VeranstaltungId}

    @GetMapping("/Veranstaltung/{VeranstaltungId}")
    public Veranstaltung getVeranstaltung(@PathVariable int VeranstaltungId) {

	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	}

	return theVeranstaltung;
    }

    // add mapping for POST /Veranstaltungs - add new Veranstaltung

    @PostMapping("/Veranstaltung")
    public Veranstaltung addVeranstaltung(@RequestBody Veranstaltung theVeranstaltung) {

	// also just in case they pass an id in JSON ... set id to 0
	// this is to force a save of new item ... instead of update

	// theVeranstaltung.setVeranstaltungsID(0);

	VeranstaltungService.save(theVeranstaltung);

	return theVeranstaltung;
    }

    // add mapping for PUT /Veranstaltungs - update existing Veranstaltung

    @PutMapping("/Veranstaltung")
    public Veranstaltung updateVeranstaltung(@RequestBody Veranstaltung theVeranstaltung) {

	Veranstaltung tempVeranstaltung = VeranstaltungService.findById(theVeranstaltung.getVeranstaltungsID());
	tempVeranstaltung.merge(theVeranstaltung);
	VeranstaltungService.save(tempVeranstaltung);

	return tempVeranstaltung;
    }

    // add mapping for DELETE /Veranstaltungs/{VeranstaltungId} - delete
    // Veranstaltung

    @DeleteMapping("/Veranstaltung/{VeranstaltungId}")
    public String deleteVeranstaltung(@PathVariable int VeranstaltungId) {

	Veranstaltung tempVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	// throw exception if null

	if (tempVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	}

	VeranstaltungService.deleteById(VeranstaltungId);

	return "Deleted Veranstaltung id - " + VeranstaltungId;
    }

    @GetMapping("/Veranstaltung/Veranstalter/{VeranstaltungId}")
    public Veranstalter getVeranstaltungsVeranstalter(@PathVariable int VeranstaltungId) {

	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    Veranstalter theVeranstalter = VeranstaltungService.findVeranstalter(VeranstaltungId);

	    if (theVeranstalter == null) {
		throw new VeranstaltungNotFoundException(
			"Veranstaltung id  - " + VeranstaltungId + "hat keinen Veranstalter");
	    } else
		return theVeranstalter;
	}

    }

    @GetMapping("/Veranstaltung/Oberkategorie/{VeranstaltungId}")
    public Oberkategorie getVeranstaltungOberkategorie(@PathVariable int VeranstaltungId) {

	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    Oberkategorie theOberkategorie = VeranstaltungService.findVeranstaltungsOberkategorie(VeranstaltungId);

	    if (theOberkategorie == null) {
		throw new VeranstaltungNotFoundException(
			"Veranstaltung id  - " + VeranstaltungId + "hat keine Oberkategorie");
	    } else
		return theOberkategorie;
	}
    }

    @GetMapping("/Veranstaltung/Unterkategorie/{VeranstaltungId}")
    public Unterkategorie getVeranstaltungUnterkategorie(@PathVariable int VeranstaltungId) {

	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    Unterkategorie theUnterkategorie = VeranstaltungService.findVeranstaltungsUnterkategorie(VeranstaltungId);

	    if (theUnterkategorie == null) {
		throw new VeranstaltungNotFoundException(
			"Veranstaltung id  - " + VeranstaltungId + "hat keine Unterkategorie");
	    } else
		return theUnterkategorie;
	}
    }

    @GetMapping("/Veranstaltung/Adresse/{VeranstaltungId}")
    public Adresse getVeranstaltungsAdresse(@PathVariable int VeranstaltungId) {

	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    Adresse theAdresse = VeranstaltungService.findVeranstaltungsAdresse(VeranstaltungId);

	    if (theAdresse == null) {
		throw new VeranstaltungNotFoundException(
			"Veranstaltung id  - " + VeranstaltungId + "hat keine Adresse");
	    } else
		return theAdresse;
	}
    }

    @GetMapping("/Veranstaltung/User/{VeranstaltungId}")
    public List<User> getVeranstaltungsBesucher(@PathVariable int VeranstaltungId) {

	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    List<User> users = VeranstaltungService.findVeranstaltungsBesucher(VeranstaltungId);

	    if (users == null) {
		throw new VeranstaltungNotFoundException(
			"Veranstaltung id  - " + VeranstaltungId + "hat keine Besucher");
	    } else
		return users;
	}
    }

    @PostMapping("/Veranstaltung/Veranstalter/{VeranstaltungId}/{veranstalterId}")
    public Veranstalter addVeranstaltungsVeranstalterById(@PathVariable int VeranstaltungId,
	    @PathVariable int veranstalterId) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsVeranstalterById(VeranstaltungId, veranstalterId);
	}
	return VeranstaltungService.findVeranstalter(VeranstaltungId);
    }

    @PostMapping("/Veranstaltung/Veranstalter/{VeranstaltungId}")
    public Veranstalter addVeranstaltungsVeranstalter(@PathVariable int VeranstaltungId,
	    @RequestBody Veranstalter theVeranstalter) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(VeranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + VeranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsVeranstalter(VeranstaltungId, theVeranstalter);
	}
	return VeranstaltungService.findVeranstalter(VeranstaltungId);
    }

    @PostMapping("/Veranstaltung/User/{veranstaltungId}/{userId}")
    public List<User> addVeranstaltungsBesucherById(@PathVariable int veranstaltungId, @PathVariable int userId) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsBesucherById(veranstaltungId, userId);
	}
	return VeranstaltungService.findVeranstaltungsBesucher(veranstaltungId);

    }

    @PostMapping("/Veranstaltung/User/{veranstaltungId}")
    public List<User> addVeranstaltungsBesucher(@PathVariable int veranstaltungId, @RequestBody User theUser) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsBesucher(veranstaltungId, theUser);
	}
	return VeranstaltungService.findVeranstaltungsBesucher(veranstaltungId);
    }

    @PostMapping("/Veranstaltung/Oberkategorie/{veranstaltungId}/{oberkategorieId}")
    public Oberkategorie addVeranstaltungsOberkategorieById(@PathVariable int veranstaltungId,
	    @PathVariable int oberkategorieId) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsOberkategorieById(veranstaltungId, oberkategorieId);
	}
	return VeranstaltungService.findVeranstaltungsOberkategorie(veranstaltungId);
    }

    @PostMapping("/Veranstaltung/Oberkategorie/{veranstaltungId}")
    public Oberkategorie addVeranstaltungsOberkategorie(@PathVariable int veranstaltungId,
	    @RequestBody Oberkategorie theOberkategorie) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsOberkategorie(veranstaltungId, theOberkategorie);
	}
	return VeranstaltungService.findVeranstaltungsOberkategorie(veranstaltungId);
    }

    @PostMapping("/Veranstaltung/Unterkategorie/{veranstaltungId}/{unterkategorieId}")
    public Unterkategorie addVeranstaltungsUnterkategorieById(@PathVariable int veranstaltungId,
	    @PathVariable int unterkategorieId) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsUnterkategorieById(veranstaltungId, unterkategorieId);
	}
	return VeranstaltungService.findVeranstaltungsUnterkategorie(veranstaltungId);
    }

    @PostMapping("/Veranstaltung/Unterkategorie/{veranstaltungId}")
    public Unterkategorie addVeranstaltungsUnterkategorie(@PathVariable int veranstaltungId,
	    @RequestBody Unterkategorie theUnterkategorie) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsUnterkategorie(veranstaltungId, theUnterkategorie);
	}
	return VeranstaltungService.findVeranstaltungsUnterkategorie(veranstaltungId);
    }

    @PostMapping("/Veranstaltung/Adresse/{veranstaltungId}/{adressId}")
    public Adresse addVeranstaltungsAdresseById(@PathVariable int veranstaltungId, @PathVariable int adressId) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsAdresseById(veranstaltungId, adressId);
	}
	return VeranstaltungService.findVeranstaltungsAdresse(veranstaltungId);
    }

    @PostMapping("/Veranstaltung/Adresse/{veranstaltungId}")
    public Adresse addVeranstaltungsAdresse(@PathVariable int veranstaltungId, @RequestBody Adresse theAdresse) {
	Veranstaltung theVeranstaltung = VeranstaltungService.findById(veranstaltungId);

	if (theVeranstaltung == null) {
	    throw new VeranstaltungNotFoundException("Veranstaltung id not found - " + veranstaltungId);
	} else {
	    VeranstaltungService.addVeranstaltungsAdresse(veranstaltungId, theAdresse);
	}
	return VeranstaltungService.findVeranstaltungsAdresse(veranstaltungId);
    }

    @GetMapping("/Veranstaltung/Highlights")
    public List<Veranstaltung> findHighlights() {
	return VeranstaltungService.findHighlights();
    }
    
    
    @GetMapping("/Veranstaltung/Request")
    public List<Veranstaltung> findFilterVeranstaltung (@RequestBody Request request) {
	if ((request.getDatefrom() == null) && (request.getDateto()==null) && (request.getOberkategorie()==null) && (request.getUnterkategorie() == null) && request.getSuche() ==null)
	return VeranstaltungService.findAll();
	    return VeranstaltungService.findFilterVeranstaltung (request);
    }
    
    
   
    
    
}
