package com.khaled.duda.controller;

import com.khaled.duda.ResourceNotFoundException;
import com.khaled.duda.Utilities.Haversine;
import com.khaled.duda.model.Pupil;
import com.khaled.duda.model.School;
import com.khaled.duda.repository.PupilRepository;
import com.khaled.duda.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
            pupilRepository.save(firstPupil);
            pupilRepository.save(secondPupil);
        }
    }

    @PostMapping("/enroll/{pupilId}")
    public void enroll(@PathVariable(value = "pupilId") Long pupilId) {
        Pupil pupil = pupilRepository.findById(pupilId).get();
        List<School> schools = schoolRepository.findAll();
        if (pupil == null || schools == null) {
            System.out.println("BAKK");
            return;
        }
        System.out.println("Scools number = "+schools.size());
        School bestSchool = Haversine.bestSchool(pupil,(ArrayList)schools);
        System.out.println("+++++++best school is :"+bestSchool.getId());
        bestSchool.addPupil(pupil);
        schoolRepository.save(bestSchool);
    }

    @GetMapping("/pupil/{id}")
    public ResponseEntity < Pupil > getPupilById(@PathVariable(value = "id") Long pupilId)
            throws ResourceNotFoundException {
        Pupil pupil = pupilRepository.findById(pupilId)
                .orElseThrow(() -> new ResourceNotFoundException("pupil not found for this id :: " + pupilId));
        return ResponseEntity.ok().body(pupil);
    }

    @GetMapping("/hello/{num}")
    public String hello(@PathVariable(value = "num") int num){
        return "Hi Khaled "+ num;

    }

    @GetMapping("/frinds/{num}")
    public List<Long> frinds(@PathVariable(value = "num") long num){
        return pupilRepository.getOne(num).getFriends();

    }

    @GetMapping("/school/{id}")
    public ResponseEntity < School > getSchoolById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("pupil not found for this id :: " + id));
        return ResponseEntity.ok().body(school);
    }

}
