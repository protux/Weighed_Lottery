package de.nischwan.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents an entry of the decider.
 *
 * @author Nico Schwanebeck
 */
public class DeciderEntry {
    private StringProperty entryName;
    private IntegerProperty entryWeight;
    private float cumulativeWeight;

    public DeciderEntry(final String entryName, final int entryWeight) {
        setEntryName(entryName);
        setEntryWeight(entryWeight);
    }

    public String getEntryName() {
        return entryName.getValue();
    }

    public StringProperty getNameProperty() {
        return entryName;
    }

    public void setEntryName(final String entryName) {
        this.entryName = new SimpleStringProperty(entryName);
    }

    public int getEntryWeight() {
        return entryWeight.getValue();
    }

    public IntegerProperty getWeightProperty() {
        return entryWeight;
    }

    public void setEntryWeight(final int entryWeight) {
        this.entryWeight = new SimpleIntegerProperty(entryWeight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final DeciderEntry that = (DeciderEntry) o;

        if (getEntryWeight() != that.getEntryWeight()) {
            return false;
        }
        return getEntryName().equals(that.getEntryName());
    }

    public float getCumulativeWeight() {
        return cumulativeWeight;
    }

    public void setCumulativeWeight(final float cumulativeWeight) {
        this.cumulativeWeight = cumulativeWeight;
    }

    @Override
    public int hashCode() {
        int result = getEntryName().hashCode();
        result = 31 * result + getEntryWeight();
        return result;
    }

    @Override
    public String toString() {
        return "DeciderEntry{" +
                "entryName='" + entryName + '\'' +
                ", entryWeight='" + entryWeight + '\'' +
                '}';
    }
}
