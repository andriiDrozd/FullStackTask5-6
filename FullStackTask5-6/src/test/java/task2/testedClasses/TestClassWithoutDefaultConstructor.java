package task2.testedClasses;

import java.time.Instant;

public class TestClassWithoutDefaultConstructor {
    private String stringProperty;

    private int numberProperty;

    private Instant timeProperty;

    public TestClassWithoutDefaultConstructor(String stringProperty, int numberProperty, Instant timeProperty) {
        this.stringProperty = stringProperty;
        this.numberProperty = numberProperty;
        this.timeProperty = timeProperty;
    }

    public String getStringProperty() {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public int getNumberProperty() {
        return numberProperty;
    }

    public void setNumberProperty(int numberProperty) {
        this.numberProperty = numberProperty;
    }

    public Instant getTimeProperty() {
        return timeProperty;
    }

    public void setTimeProperty(Instant timeProperty) {
        this.timeProperty = timeProperty;
    }
}
