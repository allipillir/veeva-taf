package org.veeva.atf.constants;

public enum ProductType {
    Accessories("Accessories"),
    Bags("Bags"),
    CarAccessories("Car Accessories"),
    Footwear("Footwear"),
    Hats("Hats"),
    HomeOffice("Home & Office"),
    HoodiesSweatshirts("Hoodies & Sweatshirts"),
    Jackets("Jackets"),
    Jerseys("Jerseys"),
    Pants("Pants"),
    Rompers("Rompers"),
    Shorts("Shorts"),
    SwimCollection("Swim Collection"),
    TShirts("T-Shirts"),
    Dresses("Dresses"),
    LawnOutdoors("Lawn & Outdoors"),
    Polos("Polos"),
    ShirtsSweaters("Shirts & Sweaters"),
    SleepwearUnderwear("Sleepwear & Underwear"),
    PlusSizes("Plus Sizes"),
    Tailgating("Tailgating"),
    BigTall("Big & Tall");

    private final String productType;
    ProductType(final String newValue) {
        productType = newValue;
    }

    public String getValue() { return productType; }

}
