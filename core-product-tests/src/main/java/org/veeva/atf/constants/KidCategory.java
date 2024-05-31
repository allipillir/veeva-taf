package org.veeva.atf.constants;

public enum KidCategory {
    Accessories(1),
    Bags(1),
    Footwear(1),
    Hats(1),

    HomeOffice(2),
    HoodiesSweatshirts(2),
    Jackets(2),

    Jerseys(3),
    Pants(3),
    Rompers(3),


    Shorts(4),
    SwimCollection(4),
    TShirts(4);

    private final int categoryIndex;
    KidCategory(final int newValue) {
        categoryIndex = newValue;
    }

    public int getValue() { return categoryIndex; }

}
