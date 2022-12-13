package task2.testedClasses;

import task2.annotation.Property;

import java.time.Instant;

public class TestClassWithoutFormatOfDateAndTimeInAnnotation {

    @Property(name = "timeProperty")
    private Instant time;


    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }


}
