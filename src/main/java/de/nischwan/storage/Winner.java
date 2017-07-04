package de.nischwan.storage;

/**
 * @author Nico Schwanebeck
 */
public class Winner {
    private String name;
    private int rank;

    public Winner(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
