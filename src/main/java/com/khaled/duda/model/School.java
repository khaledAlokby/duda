package com.khaled.duda.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name = "schools")
public class School {

    private long id;
    private Double lat;
    private Double lon;
    private Integer minimumGpa;
    private Integer maxNumberOfPupils;

    public School() {
    }

    private List<Pupil> pupils = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private boolean available;


    public School(Double lat, Double lon, Integer minimumGpa, Integer maxNumberOfPupils) {
        this.lat = lat;
        this.lon = lon;
        this.minimumGpa = minimumGpa;
        this.maxNumberOfPupils = maxNumberOfPupils;
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

    @Column(name = "min_gpa", nullable = false)
    public Integer getMinimumGpa() {
        return minimumGpa;
    }

    public void setMinimumGpa(Integer minimumGpa) {
        this.minimumGpa = minimumGpa;
    }

    @Column(name = "max_pupils", nullable = false)
    public Integer getMaxNumberOfPupils() {
        return maxNumberOfPupils;
    }

    public void setMaxNumberOfPupils(Integer maxNumberOfPupils) {
        this.maxNumberOfPupils = maxNumberOfPupils;
    }


    @Column(name = "pupils", nullable = false)
    @ElementCollection
    public List<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        if (pupils == null){
            System.out.println("******no pupils yet******");
            pupils = new ArrayList<>();
        }
        this.pupils = pupils;
    }

    public void addPupil(Pupil pupil){
        lock.lock();
        if (!pupils.contains(pupil))
            pupils.add(pupil);
        lock.unlock();
    }

    @Column(name = "available", nullable = false)
    public boolean isAvailable() {
        return pupils.size() <= maxNumberOfPupils;
    }
    public void setAvailable(boolean available) {
        this.available=available;
    }

    public int numOfFriends(long id) {
        int result = 0;
        lock.lock();
        for (Pupil pupil : pupils){
            if (pupil.isFrindOF(id))
                result++;
        }
        lock.unlock();
        return result;
    }
}
