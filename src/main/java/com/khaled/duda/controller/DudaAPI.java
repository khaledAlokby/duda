package com.khaled.duda.controller;

import com.khaled.duda.Utilities.Haversine;
import com.khaled.duda.model.Pupil;
import com.khaled.duda.model.School;
import com.khaled.duda.repository.PupilRepository;
import com.khaled.duda.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Utilities;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class DudaAPI {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @PostMapping("/pupil")
    public Long createPupil(@Valid @RequestBody Pupil pupil) {
        pupilRepository.save(pupil);
        return pupil.getId();
    }

    @PostMapping("/school")
    public Long createSchool(@Valid @RequestBody School school) {
        schoolRepository.save(school);
        return school.getId();
    }

    @PostMapping("/setFriendShip/{firstPupilId}/{secondPupilId}")
    public void setFriendShip(@PathVariable(value = "firstPupilId") Long firstPupilId,
                              @PathVariable(value = "secondPupilId") Long secondPupilId) {
        Pupil firstPupil = pupilRepository.findById(firstPupilId).get();
        Pupil secondPupil = pupilRepository.findById(secondPupilId).get();
        if (secondPupil != null && firstPupil != null){
            firstPupil.setFriend(secondPupilId);
            secondPupil.setFriend(firstPupilId);
        }
    }

    @PostMapping("/enroll/{pupilId}")
    public void enroll(@PathVariable(value = "pupilId") Long pupilId) {
        Pupil pupil = pupilRepository.getOne(pupilId);
        List<School> schools = schoolRepository.findAll();
        School bestSchool = Haversine.bestSchool(pupil,(ArrayList)schools);
        bestSchool.addPupil(pupil);
    }

}
