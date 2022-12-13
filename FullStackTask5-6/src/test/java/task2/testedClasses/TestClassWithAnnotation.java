package task2.testedClasses;

import task2.annotation.Property;

import java.time.Instant;

public class TestClassWithAnnotation {
    @Property(name = "stringProperty")
    private String str;

    @Property(name = "numberProperty")
    private int number;

    @Property(name = "timeProperty", format = "dd.MM.yyyy HH:mm")
    private Instant time;


    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }


}
