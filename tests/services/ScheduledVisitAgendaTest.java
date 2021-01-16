package services;

import data.HealthCardID;
import exceptions.HealthCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduledVisitAgendaTest {

    private ScheduledVisitAgendaClass scheduledVA;
    private HealthCardID hc1, hc2;
    private Date date1, date2;

    @BeforeEach
    void setUP() {
        scheduledVA = new ScheduledVisitAgendaClass();

        hc1 = new HealthCardID("ABCD1234567890");
        hc2 = new HealthCardID("EFGH2222222222");


    }

    @Test
    void getHealthCardIDTest() throws HealthCardException {

        assertEquals(scheduledVA.getHealthCardID(), hc1);
        assertNotEquals(scheduledVA.getHealthCardID(), hc2););});

    }
}
