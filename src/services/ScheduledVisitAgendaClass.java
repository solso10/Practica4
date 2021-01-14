package services;

import data.HealthCardID;
import exceptions.HealthCardException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScheduledVisitAgendaClass implements ScheduledVisitAgenda{

    private Map<Date, HealthCardID> schedule = new HashMap<>();
    private final Date today = new Date();
    private final int year = today.getYear();
    private final int month = today.getMonth();
    private final int day = today.getDay();

    public class ScheduledVisitAgendaClass(){
        public HealthCardID getHealthCardID() throws HealthCardException {
            if (!schedule.containsKey(new Date(year, month, day))) throw new HealthCardException();
            return schedule.get(new Date(year, month, day));
        }
    }
}

