package controllers;

import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;


public class Dashboard extends Controller {


    public static void index() {


        Logger.info("Rendering Dashboard");
        Member member = Account.getLoggedInMember();
        List<Station> stations = Station.findAll();
        Collections.sort(stations, Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));
        render("dashboard.html", stations);
    }

    public static void deleteStation(Long id) {
        Logger.info("Removing");
        Station station = Station.findById(id);
        Member member = Account.getLoggedInMember();
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }

    public static void addStation(String title, double latitude, double longitude) {
        Logger.info("Adding a new station called " + title + latitude + longitude);
        Member member = Account.getLoggedInMember();
        Station station = new Station(title, latitude, longitude);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }
}

