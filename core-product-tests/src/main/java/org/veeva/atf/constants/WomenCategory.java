package org.veeva.atf.constants;

public enum WomenCategory {

    Accessories(1),
    Bags(1),
    CarAccessories(1),
    Dresses(1),
    Footwear(1),

    Hats(2),
    HomeOffice(2),
    HoodiesSweatshirts(2),
    Jerseys(2),
    Jackets(2),

    LawnOutdoors(3),
    Pants(3),
    Polos(3),
    ShirtsSweaters(3),
    Shorts(3),

    SleepwearUnderwear(4),
    SwimCollection(4),
    TShirts(4),
    PlusSizes(4),
    Tailgating(4);

    private final int categoryIndex;
    WomenCategory(final int newValue) {
        categoryIndex = newValue;
    }

    public int getValue() { return categoryIndex; }

}
