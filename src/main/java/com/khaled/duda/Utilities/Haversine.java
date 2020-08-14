package com.khaled.duda.Utilities;

import com.khaled.duda.model.Pupil;
import com.khaled.duda.model.School;

import java.util.ArrayList;

public class Haversine {

    static double haversine(double lat1, double lon1,
                            double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    public static School bestSchool(Pupil pupil, ArrayList<School> schools){
        School bestSchool = null;
        double maxScore = 0;
        for (School school : schools){
            if (school.isAvailable()){
                int numOfFriends = school.numOfFriends(pupil.getId());
                double dis = haversine(pupil.getLat(),pupil.getLon(),school.getLat(),school.getLon());
                double score = numOfFriends/ dis;
                if (score > maxScore){
                    maxScore = score;
                    bestSchool = school;
                }
            }

        }
        return bestSchool;
    }
}
