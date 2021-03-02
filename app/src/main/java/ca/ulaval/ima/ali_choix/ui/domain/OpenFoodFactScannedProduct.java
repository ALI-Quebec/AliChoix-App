package ca.ulaval.ima.ali_choix.ui.domain;

public class OpenFoodFactScannedProduct {
    private String status_verbose;
    private int status;
    private String code;
    private Product product;

    public OpenFoodFactScannedProduct(String status_verbose, int status, String code, Product product) {
        this.status_verbose = status_verbose;
        this.status = status;
        this.code = code;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

}
