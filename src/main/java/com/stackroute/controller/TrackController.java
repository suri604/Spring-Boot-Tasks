package com.stackroute.controller;

import com.stackroute.customexceptions.TrackAlreadyExistsException;
import com.stackroute.customexceptions.TrackNotFoundException;
import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1")
public class TrackController {
    @Autowired
            //Qualifier("dummyTrack")
    private TrackService trackService;
    private ResponseEntity responseEntity;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @ApiOperation(value = "Saves tracks in the database")
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        try
        {
            trackService.savetrack(track);
            responseEntity = new ResponseEntity<String>("SuccessFully Created", HttpStatus.CREATED);
        }catch(TrackAlreadyExistsException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }
    @ApiOperation(value = "Returns all the tracks in the database")
    @GetMapping("/gettrack")
    public ResponseEntity<?> getAllTracks() {

        try
        {

            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        }catch(Exception e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Update COmments of particular tracks in the database")
    @PutMapping("/getupdate")
    public ResponseEntity<?> updateComments(@RequestBody Track track) {

        try
        {
             trackService.updateComments(track);
            responseEntity = new ResponseEntity<String>("successfully updated",HttpStatus.OK);
        }catch(TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Deletes tracks in the database")
    @DeleteMapping("/getdelete")
    public ResponseEntity<?> deleteTrack(@RequestBody Track track) {

        try
        {
            trackService.deleteTrack(track);
            responseEntity = new ResponseEntity<String>("Succesfully Deleted",HttpStatus.OK);
        }catch(TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Returns tracks by the name in the database")
    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name)  {
        try
        {

            responseEntity = new ResponseEntity<List<Track>>(trackService.getTrackByName(name),HttpStatus.OK);
        }catch(TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



}
