package com.lezv;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.lezvinskyi
 * Date: 9/11/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Restaurant {
    private String name = "";
    private String address = "";
    private String type = "";
    private Calendar date ;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String notes ="";

    public Calendar getDate() {
        return  date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return(type);
    }
    public void setType(String type) {
        this.type=type;
    }
    public String toString() {
        return(getName());
    }

    public Calendar convertStringToCalendar (String date)  {
        Calendar cal = Calendar.getInstance();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date  myDate= new Date();
        try {
           myDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        cal.setTime(myDate);
        return cal;
    }

    public String convertCalendarToString(Calendar date){
         String month = date.getDisplayName(date.MONTH, date.SHORT, Locale.US);
         int day = date.get(date.DATE);
         int year = date.get(date.YEAR);
        StringBuilder sb = new StringBuilder();
        sb.append(month).append(" " + day).append(", " + year);
         String  dateString = sb.toString();
        return dateString;
    }

}
