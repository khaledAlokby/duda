package com.khaled.duda.service;


import com.khaled.duda.model.Pupil;
import com.khaled.duda.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PupilService {
    @Autowired
    private PupilRepository pupilRepository;

    @Transactional
    public Pupil saveUser(Pupil pupil) {
        return pupilRepository.save(pupil);
    }
}
