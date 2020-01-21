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

import com.frederik.springboot.cruddemo.entity.Adresse;
import com.frederik.springboot.cruddemo.entity.Oberkategorie;
import com.frederik.springboot.cruddemo.entity.Parkplatz;
import com.frederik.springboot.cruddemo.entity.User;
import com.frederik.springboot.cruddemo.service.ParkplatzService;

@RestController
@RequestMapping("/api")
public class ParkplatzRestController {

    private ParkplatzService ParkplatzService;

    @Autowired
    public ParkplatzRestController(ParkplatzService theParkplatzService) {
	ParkplatzService = theParkplatzService;
    }

    // expose "/Parkplatzs" and return list of Parkplatzs
    @GetMapping("/Parkplatz")
    public List<Parkplatz> findAll() {
	return ParkplatzService.findAll();
    }

    // add mapping for GET /Parkplatzs/{ParkplatzId}

    @GetMapping("/Parkplatz/{ParkplatzId}")
    public Parkplatz getParkplatz(@PathVariable int ParkplatzId) {

	Parkplatz theParkplatz = ParkplatzService.findById(ParkplatzId);

	if (theParkplatz == null) {
	    throw new RuntimeException("Parkplatz id not found - " + ParkplatzId);
	}

	return theParkplatz;
    }

    // add mapping for POST /Parkplatzs - add new Parkplatz

    @PostMapping("/Parkplatz")
    public Parkplatz addParkplatz(@RequestBody Parkplatz theParkplatz) {

	// also just in case they pass an id in JSON ... set id to 0
	// this is to force a save of new item ... instead of update

	//theParkplatz.setParkplatzID(0);

	ParkplatzService.save(theParkplatz);

	return theParkplatz;
    }

    // add mapping for PUT /Parkplatzs - update existing Parkplatz

    @PutMapping("/Parkplatz")
    public Parkplatz updateParkplatz(@RequestBody Parkplatz theParkplatz) {
	Parkplatz tempParkplatz = ParkplatzService.findById(theParkplatz.getParkplatzID());
	tempParkplatz.merge(theParkplatz);
	ParkplatzService.save(tempParkplatz);

	return tempParkplatz;
    }

    // add mapping for DELETE /Parkplatzs/{ParkplatzId} - delete Parkplatz

    @DeleteMapping("/Parkplatz/{ParkplatzId}")
    public String deleteParkplatz(@PathVariable int ParkplatzId) {

	Parkplatz tempParkplatz = ParkplatzService.findById(ParkplatzId);

	// throw exception if null

	if (tempParkplatz == null) {
	    throw new RuntimeException("Parkplatz id not found - " + ParkplatzId);
	}

	ParkplatzService.deleteById(ParkplatzId);

	return "Deleted Parkplatz id - " + ParkplatzId;
    }

    @GetMapping("/Parkplatz/Adresse/{ParkplatzId}")
    public Adresse getParkplatzAdresse(@PathVariable int ParkplatzId) {

	Parkplatz theParkplatz = ParkplatzService.findById(ParkplatzId);

	if (theParkplatz == null) {
	    throw new RuntimeException("Parkplatz id not found - " + ParkplatzId);
	} else {

	    Adresse theAdresse = ParkplatzService.findParkplatzAdresse(ParkplatzId);
	    if (theAdresse == null)
		throw new RuntimeException("Parkplatz - " + ParkplatzId + " besitzt keine Adresse.");

	    return theAdresse;
	}
    }
    
    @PostMapping("/Parkplatz/Adresse/{ParkplatzId}/{AdressId}")
    public Adresse addParkplatzAdresse(@PathVariable int ParkplatzId, @PathVariable int AdressId) {

	Parkplatz theParkplatz = ParkplatzService.findById(ParkplatzId);

	ParkplatzService.addParkplatzAdresse(ParkplatzId, AdressId);
	

	    return ParkplatzService.findParkplatzAdresse(ParkplatzId);
	
    }

}
