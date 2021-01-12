package data;

import exceptions.ProductIDException;

import java.util.Objects;

final public class ProductID {

    private final String productID;

    public ProductID(String code) throws ProductIDException {
        Objects.requireNonNull(code, "El código no puede ser null");
        if (code.isEmpty()){ throw new IllegalArgumentException("El código no puede ser vacío");}
        if (!isNumeric(code)){ throw new ProductIDException("El código debe estar formado por numeros");}
        this.productID = code;
    }

    public String getProductID() { return productID; }

    public boolean isNumeric(String code) {
        boolean valid;
        try {
            Integer.parseInt(code);
            valid = true;
        } catch (NumberFormatException excepcion) {
            valid = false;
        }
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID prodID = (ProductID) o;
        return productID.equals(prodID.productID);
    }

    @Override
    public int hashCode() { return productID.hashCode(); }

    @Override
    public String toString() {
        return "ProductID{" + "product code='" + productID + '\'' + '}';
    }

}
