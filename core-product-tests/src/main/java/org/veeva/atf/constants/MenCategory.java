package org.veeva.atf.constants;

public enum MenCategory {

    Accessories(1),
    Bags(1),
    CarAccessories(1),
    Footwear(1),
    Hats(1),

    HomeOffice(2),
    HoodiesSweatshirts(2),
    Jackets(2),
    Jerseys(2),
    LawnOutdoors(2),

    Pants(3),
    Polos(3),
    ShirtsSweaters(3),
    Shorts(3),
    SleepwearUnderwear(3),

    SwimCollection(4),
    TShirts(4),
    Tailgating(4),
    BigTall(4);

    private final int categoryIndex;
    MenCategory(final int newValue) {
        categoryIndex = newValue;
    }

    public int getValue() { return categoryIndex; }

}
