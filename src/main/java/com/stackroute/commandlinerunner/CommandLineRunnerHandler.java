package com.stackroute.commandlinerunner;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerHandler implements CommandLineRunner {
   @Autowired
    TrackRepository trackRepository;
    @Override
    public void run(String... args) throws Exception {
        try {
            trackRepository.save(new Track(2, "HUM", "JEET GAYE"));
        }catch(Exception e)
        {
            e.getMessage();
        }
    }
}
