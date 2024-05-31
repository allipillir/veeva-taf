package org.veeva.atf.constants;

public enum ProductCategory {

    Men("Men"),
    Women("Women"),
    TShirts("T-Shirts"),
    Jerseys("Jerseys"),
    Sweatshirts("Sweatshirts"),
    Hats("Hats"),
    Accessories("Accessories"),
    HomeOffice("Home & Office"),
    Collectibles("Collectibles"),
    Sale("Sale"),
    NewArrivals("New Arrivals"),
    Kids("Kids");

    private final String productCategory;
    ProductCategory(final String newValue) {
        productCategory = newValue;
    }

    public String getValue() { return productCategory; }

}
