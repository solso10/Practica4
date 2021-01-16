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

    public ScheduledVisitAgendaClass() {
        setUpSchedule();
    }

    public HealthCardID getHealthCardID() throws HealthCardException {
        if (!schedule.containsKey(new Date(year, month, day))) throw new HealthCardException("error1222");
        return schedule.get(new Date(year, month, day));
    }

    public void setUpSchedule() {
        Date date1 = new Date(2020, 10, 3);
        Date date2 = new Date(2020, 12, 1);
        Date date3 = new Date(2020, 12, 1);

        HealthCardID hc1 = new HealthCardID("ABCD1234567890");
        HealthCardID hc2 = new HealthCardID("EFGH2345678901");
        HealthCardID hc3 = new HealthCardID("EFGH2345678901");

        schedule.put(date1, hc1);
        schedule.put(date2, hc2);
        schedule.put(date3, hc3);
        
    }
}

