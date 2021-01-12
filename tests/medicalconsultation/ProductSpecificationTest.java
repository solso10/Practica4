package medicalconsultation;

import data.ProductID;
import exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest{

    private ProductSpecification specification;

    @BeforeEach
    void setUp() throws ProductIDException {
        specification = new ProductSpecification(new ProductID("101"),"Frenadol: medicamento para el resfriado", new BigDecimal(15));
    }

    @Test
    void getUPCcode() {
        assertEquals(specification.getUPCcode().getProductID(),"101");
        assertNotEquals(specification.getUPCcode().getProductID(),"404");
    }


    void getPrice() {
        assertEquals();
        assertNotEquals();
    }

 /*   @Test
    void setUPCcode() {
        assertEquals();
        assertNotEquals();
    }

    @Test
    void setDescription() {
        assertEquals();
        assertNotEquals();
    }

    @Test
    void setPrice() {
        assertEquals();
        assertNotEquals();
    }*/
}