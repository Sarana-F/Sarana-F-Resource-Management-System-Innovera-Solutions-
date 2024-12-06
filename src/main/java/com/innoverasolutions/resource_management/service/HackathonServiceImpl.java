package com.innoverasolutions.resource_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.innoverasolutions.resource_management.model.Hackathon;
import com.innoverasolutions.resource_management.repository.HackathonRepository;

@Service
public class HackathonServiceImpl implements HackathonService {
    @Autowired
    private HackathonRepository hackathonRepository;

    @Override
    public void saveHackathon(Hackathon hackathon) {
        hackathonRepository.save(hackathon);
    }

    @Override
    public Iterable<Hackathon> findHackathons() {
        return hackathonRepository.findAll();
    }

    @Override
    public Hackathon getHackathonId(Long id) {
        return hackathonRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteHackathon(Long id) {
        hackathonRepository.deleteById(id);
    }
}
