package com.stackroute.service;

import com.stackroute.customexceptions.TrackAlreadyExistsException;
import com.stackroute.customexceptions.TrackNotFoundException;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
public class TrackDummyServiceImpl implements TrackService {

    TrackRepository trackRepository;
    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository= trackRepository;
    }
    @Override
    public Track savetrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistsException("Track is already present");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack == null)
        {
            throw new TrackAlreadyExistsException("Track is already present");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateComments(Track track) throws TrackNotFoundException {
        if(trackRepository.existsById(track.getTrackId())) {
            Track updateTrack = trackRepository.getOne(track.getTrackId());
            updateTrack.setTrackComments(track.getTrackComments());
            trackRepository.save(updateTrack);
            return updateTrack;
        }
        else
        {
            throw new TrackNotFoundException("Unable to Update Track is not found anywhere");
        }
    }

    @Override
    public void deleteQuery(Track track) throws TrackNotFoundException {
        if(trackRepository.existsById(track.getTrackId())) {
            trackRepository.delete(track);
        }
        else
        {
            throw new TrackNotFoundException("Unable to delete track not found");
        }



    }

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException {

        List<Track> tracks = trackRepository.getTrackByName(name);
        if(tracks.size()==0)
        {
            throw new TrackNotFoundException();
        }
        return tracks;

    }


}
