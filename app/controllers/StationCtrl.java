package controllers;

import Utilities.Utilities;
import play.Logger;
import models.Station;
import models.Reading;
import play.mvc.Controller;

import java.util.Date;

import static models.Station.convertWindDirection;
import static models.Station.convertBeaufort;
import static Utilities.Utilities.weatherCode;

import static Utilities.Utilities.weatherIcons;


public class StationCtrl extends Controller {
    public static void main(String[] args) {
        StationCtrl c = new StationCtrl();
    }


    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id=" + id);

        if (station.readings.size() > 0) {
            station.minTempReading = Utilities.getminTemp(station.readings);
            station.maxTempReading = Utilities.getmaxTemp(station.readings);
            station.latestTemperature = Utilities.getlatestTemp(station.readings);
            station.latestTemperatureF = Utilities.getlatestTempF(station.readings);
            station.latestPressure = Utilities.getlatestPressure(station.readings);
            station.latestwindSpeed = Utilities.getlatestwindSpeed(station.readings);
            station.minPressureReading = Utilities.getminPressure(station.readings);
            station.maxPressureReading = Utilities.getmaxPressure(station.readings);
            station.latestWeather = weatherIcons(Utilities.getlatestWeather(station.readings));
            station.latestWeatherCode = weatherCode(Utilities.getlatestWeather(station.readings));
            station.minWindReading = Utilities.getminWind(station.readings);
            station.maxWindReading = (Utilities.getmaxWind(station.readings));
            station.windChill = Math.round((13.12 + 0.6215 * station.latestTemperature - 11.37 * Math.pow(station.latestwindSpeed, 0.16) + 0.3965 * station.latestTemperature * Math.pow(station.latestwindSpeed, 0.16)) * 100.00) / 100.00;
            station.wind = convertBeaufort(Utilities.getlatestwindSpeed(station.readings));
            station.windDirection = convertWindDirection(Utilities.getlatestwindDirection(station.readings));
        }
        render("station.html", station);
    }

    public static void deletereading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing" + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }

    public static void addReading(Long id, int code, double temp, double windspeed, int windDirection,
                                  int pressure) {
        Date date = new Date(System.currentTimeMillis());
        Reading reading = new Reading(code, temp, windspeed, windDirection, pressure, date);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }


    public static void addLatestReading(Long id, int code, double temp, double windspeed, int windDirection,
                                        int pressure) {
        Date date = new Date(System.currentTimeMillis());
        Reading reading = new Reading(code, temp, windspeed, windDirection, pressure, date);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);

    }
}

