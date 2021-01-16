package services;

import data.HealthCardID;
import exceptions.HealthCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScheduledVisitAgendaTest {

    private ScheduledVisitAgendaClass scheduledVA;
    private HealthCardID hcID;
    private Date date1, date2;

    @BeforeEach
    void setUP() {
       // scheduledVA = new ScheduledVisitAgendaClass();
        //hcID = new HealthCardID("ABCD1234567890");


    }

    @Test
    void getHealthCardIDTest() throws HealthCardException {

        ScheduledVisitAgendaClass schedAgenda = new ScheduledVisitAgendaClass();
        assertEquals(schedAgenda.getHealthCardID(), new HealthCardID("ABCD1234567890"));
        assertNotEquals(schedAgenda.getHealthCardID(), new HealthCardID("GHJK1234567890"));
        assertEquals(scheduledVA.getHealthCardID(), hcID);
    }
}
