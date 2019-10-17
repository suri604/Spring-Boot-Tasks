package com.stackroute.service;

import com.stackroute.customexceptions.TrackAlreadyExistsException;
import com.stackroute.customexceptions.TrackNotFoundException;
import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {
    public Track savetrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks();
    public Track updateComments(Track track) throws TrackNotFoundException;
    public void deleteTrack(Track track) throws TrackNotFoundException;
    public List<Track> getTrackByName(String name) throws TrackNotFoundException;

}
