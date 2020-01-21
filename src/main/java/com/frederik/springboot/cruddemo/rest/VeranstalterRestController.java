package com.frederik.springboot.cruddemo.rest;

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

import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.entity.Veranstalter;
import com.frederik.springboot.cruddemo.entity.Veranstaltung;
import com.frederik.springboot.cruddemo.service.VeranstalterService;

@RestController
@RequestMapping("/api")
public class VeranstalterRestController {

    private VeranstalterService VeranstalterService;

    @Autowired
    public VeranstalterRestController(VeranstalterService theVeranstalterService) {
	VeranstalterService = theVeranstalterService;
    }

    // expose "/Veranstalters" and return list of Veranstalters
    @GetMapping("/Veranstalter")
    public List<Veranstalter> findAll() {
	return VeranstalterService.findAll();
    }

    // add mapping for GET /Veranstalters/{VeranstalterId}

    @GetMapping("/Veranstalter/{VeranstalterId}")
    public Veranstalter getVeranstalter(@PathVariable int VeranstalterId) {

	Veranstalter theVeranstalter = VeranstalterService.findById(VeranstalterId);

	if (theVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + VeranstalterId);
	}

	return theVeranstalter;
    }

    // add mapping for POST /Veranstalters - add new Veranstalter

    @PostMapping("/Veranstalter")
    public Veranstalter addVeranstalter(@RequestBody Veranstalter theVeranstalter) {

	// also just in case they pass an id in JSON ... set id to 0
	// this is to force a save of new item ... instead of update

	// theVeranstalter.setVeranstalterID(0);

	VeranstalterService.save(theVeranstalter);

	return theVeranstalter;
    }

    // add mapping for PUT /Veranstalters - update existing Veranstalter

    @PutMapping("/Veranstalter")
    public Veranstalter updateVeranstalter(@RequestBody Veranstalter theVeranstalter) {
	Veranstalter tempVeranstalter = VeranstalterService.findById(theVeranstalter.getVeranstalterID());
	tempVeranstalter.merge(theVeranstalter);
	VeranstalterService.save(tempVeranstalter);

	return tempVeranstalter;
    }

    // add mapping for DELETE /Veranstalters/{VeranstalterId} - delete Veranstalter

    @DeleteMapping("/Veranstalter/{VeranstalterId}")
    public String deleteVeranstalter(@PathVariable int VeranstalterId) {

	Veranstalter tempVeranstalter = VeranstalterService.findById(VeranstalterId);

	// throw exception if null

	if (tempVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + VeranstalterId);
	}

	VeranstalterService.deleteById(VeranstalterId);

	return "Deleted Veranstalter id - " + VeranstalterId;
    }

    @GetMapping("/Veranstalter/User/{VeranstalterId}")
    public List<User> getVeranstalterUsers(@PathVariable int VeranstalterId) {

	Veranstalter tempVeranstalter = VeranstalterService.findById(VeranstalterId);

	if (tempVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + VeranstalterId);
	} else {
	    List<User> users = VeranstalterService.findVeranstalterUser(VeranstalterId);

	    if (users == null) {
		throw new UserNotFoundException("Veranstalter  - " + VeranstalterId + "hat keine User");
	    }

	    return users;
	}
    }

    @PostMapping("/Veranstalter/User/{veranstalterId}/{userId}")
    public List<User> addVeranstalterUser(@PathVariable int veranstalterId, @PathVariable int userId) {
	Veranstalter tempVeranstalter = VeranstalterService.findById(veranstalterId);

	if (tempVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + veranstalterId);
	} else {
	    VeranstalterService.addVeranstalterUser(veranstalterId, userId);
	}
	return VeranstalterService.findVeranstalterUser(veranstalterId);
    }

    @PostMapping("/Veranstalter/Veranstaltung/{veranstalterId}/{veranstaltungId}")
    public List<Veranstaltung> addVeranstalterVeranstaltungById(@PathVariable int veranstalterId,
	    @PathVariable int veranstaltungId) {
	Veranstalter tempVeranstalter = VeranstalterService.findById(veranstalterId);

	if (tempVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + veranstalterId);
	} else {
	    VeranstalterService.addVeranstalterVeranstaltung(veranstalterId, veranstaltungId);
	}
	return VeranstalterService.findVeranstalterVeranstaltung(veranstalterId);
    }

    @PostMapping("/Veranstalter/Veranstaltung/{veranstalterId}")
    public List<Veranstaltung> addVeranstalterVeranstaltung(@PathVariable int veranstalterId,
	    @RequestBody Veranstaltung theVeranstaltung) {
	Veranstalter tempVeranstalter = VeranstalterService.findById(veranstalterId);

	if (tempVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + veranstalterId);
	} else {
	    VeranstalterService.addVeranstalterVeranstaltung(veranstalterId, theVeranstaltung);
	}
	return VeranstalterService.findVeranstalterVeranstaltung(veranstalterId);
    }

    @GetMapping("/Veranstalter/Veranstaltung/{veranstalterId}")
    public List<Veranstaltung> findVeranstalterVeranstaltungen(int veranstalterId) {
	Veranstalter tempVeranstalter = VeranstalterService.findById(veranstalterId);

	if (tempVeranstalter == null) {
	    throw new VeranstalterNotFoundException("Veranstalter id not found - " + veranstalterId);
	} else {
	    return VeranstalterService.findVeranstalterVeranstaltung(veranstalterId);
	}
    }
}
