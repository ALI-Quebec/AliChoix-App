package ca.ulaval.ima.ali_choix.domain.product;

import java.io.Serializable;

public class ProductId implements Serializable {
    private final String code;

    public ProductId(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        ProductId productId = (ProductId) object;

        return code.equals(productId.toString());
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
