package com.websystique.library.Reader;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.library.util.CustomErrorType;

@RestController
@RequestMapping("/reader/ajax")
public class Controller {

    public static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    Service userService;

    @RequestMapping(value = "/rows", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Reader>> listAllUsers() {
        List<Reader> readers = userService.findAllUsers();
//        Map<String, Serializable> map = new HashMap<>();
//        map.put("rows", );
//        map.put("value", "Foo");

        return new ResponseEntity<>(new ArrayList<>(readers), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching Reader with id {}", id);
        Reader reader = userService.findById(id);
        if (reader == null) {
            logger.error("Reader with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Reader with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Reader>(reader, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Reader reader, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Reader : {}", reader);

        if (userService.isUserExist(reader)) {
            logger.error("Unable to create. A Reader with name {} already exist", reader.getFirstName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Reader with name " +
                    reader.getFirstName() + " already exist."), HttpStatus.CONFLICT);
        }
        userService.saveUser(reader);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/reader/{id}").buildAndExpand(reader.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Reader reader) {
        logger.info("Updating Reader with id {}", id);

        Reader currentReader = userService.findById(id);

        if (currentReader == null) {
            logger.error("Unable to update. Reader with id {} not found.", id);
            return new ResponseEntity(
                    new CustomErrorType("Unable to upate. Reader with id " + id + " not found."),
                    HttpStatus.NOT_FOUND
            );
        }

        currentReader.setFirstName(reader.getFirstName());
        currentReader.setLastName(reader.getLastName());
        currentReader.setAddress(reader.getAddress());

        userService.updateUser(currentReader);
        return new ResponseEntity<Reader>(currentReader, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Reader with id {}", id);

        Reader reader = userService.findById(id);
        if (reader == null) {
            logger.error("Unable to delete. Reader with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Reader with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<Reader>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<Reader> deleteAllUsers() {
        logger.info("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<Reader>(HttpStatus.NO_CONTENT);
    }

}