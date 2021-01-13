package medicalconsultation;

import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicalPrenscriptioTest {

    MedicalPrescription mp;

    @BeforeEach
    void setUp() throws ProductIDException{
        mp = new MedicalPrescription(new ProductID("1010115"), dayMoment.DURINGDINNER, 4f, "medicamento para el dolor de cabeza",2f,4f, FqUnit.DAY);
    }

    public void addlinetest() throws ProductIDException, IncorrectTakingGuidelinesException {
        //dayMoment dM, float du, String i, float d ,float f, FqUnit u)
         ProductID prodID = new ProductID("1010115");
         String[] array1 = new String[]{"AFTERBREACKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
         String[] array2 = new String[]{"1234", "","222","FA2","4AA",""};
         String[] array3 = new String[]{"A","44AA","A2QQ","QQW"};
         String[] array4 = new String[]{"AFTERBREACKFAST","2","4", "DAY"};
         String[] array5 = new String[]{"", "2",,"2","4", "DAY"};

         mp.addLine(prodID,array1);
         mp.addLine(prodID,array2);
         mp.addLine(prodID,array3);
         mp.addLine(prodID,array4);
         mp.addLine(prodID,array5);
    }

}

