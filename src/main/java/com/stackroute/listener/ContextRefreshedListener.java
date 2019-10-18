package com.stackroute.listener;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    TrackService trackService;
    @Autowired
    private Environment env;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Handling context re-freshed event. ");
        Track track = new Track();
        track.setTrackId(Integer.parseInt(env.getProperty("track.trackId")));
        track.setTrackName(env.getProperty("track.trackName"));
        track.setTrackComments(env.getProperty("track.trackComments"));
        try{
            trackService.savetrack(track);
        }catch (Exception e)
        {
            e.getMessage();
        }

    }
}

