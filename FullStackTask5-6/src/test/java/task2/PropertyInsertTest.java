package task2;

import org.junit.Test;
import task2.exceptions.DefaultConstructorNotFoundException;
import task2.exceptions.SetterNotFoundException;
import task2.testedClasses.*;

import java.nio.file.Path;
import java.time.Instant;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PropertyInsertTest {

    private final Path pathToProperties= Path.of("src/main/resources/task2/File.properties");

    @Test
    public void createClassWithoutAnnotation()  {
        TestClassWithoutAnnotation testClass=PropertyInsert.loadFromProperties(TestClassWithoutAnnotation.class,pathToProperties);
        assertEquals("value", testClass.getStringProperty());
        assertEquals(10, testClass.getNumberProperty());
        assertEquals(Instant.parse("2022-11-29T18:30:00Z"), testClass.getTimeProperty());
    }

    @Test
    public void createClassWithAnnotation(){
        TestClassWithAnnotation testClass=PropertyInsert.loadFromProperties(TestClassWithAnnotation.class,pathToProperties);
        assertEquals("value", testClass.getStr());
        assertEquals(10, testClass.getNumber());
        assertEquals(Instant.parse("2022-11-29T18:30:00Z"), testClass.getTime());
    }

    @Test
    public void createClassWithTwoAnnotation()  {
        TestClassWithTwoAnnotation testClass=PropertyInsert.loadFromProperties(TestClassWithTwoAnnotation.class,pathToProperties);
        assertEquals("value", testClass.getStringProperty());
        assertEquals(10, testClass.getNumber());
        assertEquals(Instant.parse("2022-11-29T18:30:00Z"), testClass.getTime());
    }

    @Test
    public void createClassWithPrivateDefaultConstructor()  {
        TestClassWithPrivateDefaultConstructor testClass=PropertyInsert.loadFromProperties(TestClassWithPrivateDefaultConstructor.class,pathToProperties);
        assertEquals("value", testClass.getStringProperty());
        assertEquals(10, testClass.getNumberProperty());
        assertEquals(Instant.parse("2022-11-29T18:30:00Z"), testClass.getTimeProperty());
    }

    @Test
    public void createClassWithComplexProperty()  {
        TestClassWithComplexProperty testClass=PropertyInsert.loadFromProperties(TestClassWithComplexProperty.class,pathToProperties);
        assertEquals("value", testClass.getStringProperty());
        assertEquals(10, testClass.getNumber());
        assertEquals(Instant.parse("2022-11-29T18:30:00Z"), testClass.getTime());
    }

    @Test
    public void createClassWithoutSetter(){
        assertThrows(SetterNotFoundException.class, () -> PropertyInsert.loadFromProperties(TestClassWithoutSetter.class,pathToProperties));
    }

    @Test
    public void createClassWithoutFormatOfDateAndTimeInAnnotation(){
        TestClassWithoutFormatOfDateAndTimeInAnnotation testClass=PropertyInsert.loadFromProperties(TestClassWithoutFormatOfDateAndTimeInAnnotation.class,pathToProperties);
        assertEquals(Instant.parse("2022-11-29T18:30:00Z"), testClass.getTime());
    }

    @Test
    public void createClassWithWrongDateAndTimeFormat(){
        assertThrows(DateTimeParseException.class, () -> PropertyInsert.loadFromProperties(TestCLassWithWrongDateAndTimeFormat.class,pathToProperties));
    }

    @Test
    public void createClassWithoutDefaultConstructor(){
        assertThrows(DefaultConstructorNotFoundException.class, () -> PropertyInsert.loadFromProperties(TestClassWithoutDefaultConstructor.class,pathToProperties));
    }

    @Test
    public void createClassWithNullPath(){
        assertThrows(IllegalArgumentException.class, () -> PropertyInsert.loadFromProperties(TestClassWithoutDefaultConstructor.class,null));
    }

    @Test
    public void createClassWithNullClass(){
        assertThrows(IllegalArgumentException.class, () -> PropertyInsert.loadFromProperties(null,pathToProperties));
    }
}