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
    void TestgetUPCcode() {
        assertEquals(specification.getUPCcode().getProductID(),"101");
        assertNotEquals(specification.getUPCcode().getProductID(),"404");
    }

    @Test
    void TestgetDescription(){
        assertEquals(specification.getDescription(),"Frenadol: medicamento para el resfriado");
        assertNotEquals(specification.getDescription(),"Ibuprofeno: medicamento para la fiebre");
    }


    @Test
    void TestgetPrice() {
        assertEquals(specification.getPrice(),new BigDecimal(15));
        assertNotEquals(specification.getPrice(), new BigDecimal(20));
    }

    @Test
    void TestsetUPCcode() throws ProductIDException {
        ProductID product = new ProductID("202");
        specification.setUPCcode(product);
        assertEquals(specification.getUPCcode(),product);
    }

    @Test
    void TestsetDescription() {
        String description =  "PharmalGrip: medicamento para la gripe";
        specification.setDescription(description);
        assertEquals(specification.getDescription(),description);
    }

    @Test
    void TestsetPrice() {
        BigDecimal price = new BigDecimal(200);
        specification.setPrice(price);
        assertEquals(specification.getPrice(),price);
    }
}