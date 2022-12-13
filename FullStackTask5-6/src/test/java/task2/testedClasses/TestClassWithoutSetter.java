package task2.testedClasses;

import task2.annotation.Property;

import java.time.Instant;

public class TestClassWithoutSetter {
    private String stringProperty;

    @Property(name = "number.Property")
    private int number;

    @Property(name = "timeProperty")
    private Instant time;

}
