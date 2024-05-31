package org.veeva.atf.constants;

public enum MenuCategory {

    Men(0),
    Women(1),
    Kids(2),
    Jerseys(3),
    TShirts(4),
    Sweatshirts(5),
    Hats(6),
    Accessories(7),
    HomeOffice(8),
    Collectibles(9),
    Sale(10),
    NewArrivals(11);
    private final int categoryIndex;
    MenuCategory(final int newValue) {
        categoryIndex = newValue;
    }

    public int getValue() { return categoryIndex; }
}
