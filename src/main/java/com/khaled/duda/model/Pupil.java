package com.khaled.duda.model;


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
    private Map<String,Integer> grades;
    private List<Long> friends;


    public Pupil(Double lat, Double lon, Map<String, Integer> grades) {
        this.lat = lat;
        this.lon = lon;
        this.grades = grades;
    }

    public Pupil(long id, Double lat, Double lon, Map<String, Integer> grades, List<Long> friends) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.grades = grades;
        this.friends = friends;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
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
    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    @Column(name = "friends", nullable = false)
    @ElementCollection
    public List<Long> getFriends() {
        return friends;
    }

    public void setFriends(List<Long> friends) {
        if (friends == null)
            friends = new ArrayList<>();
        this.friends = friends;
    }

    public void setFriend(Long friendID) {
        friends.add(friendID);
    }

    public boolean isFrindOF(long id) {
        for (Long frindId : friends)
            if (frindId == id)
                return true;
        return false;
    }
}
