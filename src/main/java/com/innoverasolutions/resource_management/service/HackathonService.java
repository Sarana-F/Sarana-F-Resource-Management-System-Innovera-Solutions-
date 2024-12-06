package com.innoverasolutions.resource_management.service;

import com.innoverasolutions.resource_management.model.Hackathon;

public interface HackathonService {
    void saveHackathon(Hackathon hackathon);
    Iterable<Hackathon> findHackathons();
    Hackathon getHackathonId(Long id);
    void deleteHackathon(Long id);
}
