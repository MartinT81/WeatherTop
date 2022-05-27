package models;

import javax.persistence.Entity;


import play.db.jpa.Model;

import java.util.Date;

@Entity
public class Reading extends Model {
    public int code;
    public double temp;
    public double windspeed;
    public int pressure;
    public double windDirection;
    public Date date;


    public Reading(int code, double temp, double windspeed, int pressure, double windDirection, Date date) {
        this.code = code;
        this.temp = temp;
        this.windspeed = windspeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public double getTemperature() {
        return temp;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public int getPressure() {
        return pressure;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public Date getDate() {
        return date;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public void setTemperature(double temp) {
        this.temp = temp;
    }

    public void setWindSpeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }


    public void setwindDirection(double windDirection) {
        this.windDirection = windDirection;
    }
}