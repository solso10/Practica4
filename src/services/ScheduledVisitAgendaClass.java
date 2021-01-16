package services;

import data.HealthCardID;
import exceptions.HealthCardException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ScheduledVisitAgendaClass implements ScheduledVisitAgenda{

    private Map<Date, HealthCardID> schedule = new HashMap<>();
    private final Date today = new Date(2021, 1, 16);


    public ScheduledVisitAgendaClass() {
        setUpSchedule();
    }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        if (!schedule.containsKey(today)) throw new HealthCardException("error");
        return schedule.get(today);
    }

    public void setUpSchedule() {

        Date date1 = new Date(2021, 1, 16);
        Date date2 = new Date(2019, 10, 3);
        Date date3 = new Date(2020, 12, 1);


        HealthCardID hc1 = new HealthCardID("ABCD1234567890");
        HealthCardID hc2 = new HealthCardID("EFGH2345678901");
        HealthCardID hc3 = new HealthCardID("EFGH2345678901");

        schedule.put(date1, hc1);
        schedule.put(date2, hc2);
        schedule.put(date3, hc3);
        
    }

}

