package com.khaled.duda.model;


import com.khaled.duda.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

@Entity
@Table(name = "pupils")
public class Pupil {


    private long id;
    private Double lat;
    private Double lon;

    @ElementCollection
    //private Map<String,Integer> grades;
    private List<Grade> grades;
    private List<Long> friends = new ArrayList<>();



    public Pupil(Double lat, Double lon, List<Grade> grades) {
        this.lat = lat;
        this.lon = lon;
        this.grades = grades;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public Pupil() {
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "lat", nullable = false)
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Column(name = "lon", nullable = false)
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Column(name = "grades", nullable = false)
    @ElementCollection
    @OneToMany(targetEntity=Grade.class, fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Column(name = "friends", nullable = false)
    @ElementCollection
    public List<Long> getFriends() {
        return friends;
    }

    public void setFriends(List<Long> friends) {
        this.friends = friends;
    }

    public void setFriend(Long friendID) {
        friends.add(friendID);
    }

    public boolean isFrindOF(long id) {
        for (Long friendId : friends)
            if (friendId == id)
                return true;
        return false;
    }
}
