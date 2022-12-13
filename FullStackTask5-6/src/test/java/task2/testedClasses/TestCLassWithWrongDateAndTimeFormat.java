package task2.testedClasses;

import task2.annotation.Property;

import java.time.Instant;

public class TestCLassWithWrongDateAndTimeFormat {

    @Property(name = "timeProperty", format = "YYYY")
    private Instant time;


    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

}
