package task2;

import task2.annotation.Property;
import task2.exceptions.DefaultConstructorNotFoundException;
import task2.exceptions.SetterNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Properties;

/**
 * Main class for receiving object with inserted fields
 */
public class PropertyInsert {

    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private static final Properties prop = new Properties();

    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) {
        if (cls == null || propertiesPath == null) {
            throw new IllegalArgumentException("Class or properties Path is null");
        }
        T obj = null;
        try {
            obj = createInstance(cls);
            prop.load(new FileReader(propertiesPath.toString()));
        } catch (IOException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        for (Field field : cls.getDeclaredFields()) {
            String fieldName = field.getName();

            if (field.isAnnotationPresent(Property.class)) {
                Property annotation = field.getAnnotation(Property.class);
                if (prop.containsKey(annotation.name())) {
                    setInstanceFields(obj, field, annotation.name(), annotation.format().isEmpty() ? DEFAULT_DATE_FORMAT : annotation.format());
                }
            } else {
                if (prop.containsKey(fieldName)) {
                    setInstanceFields(obj, field, fieldName, DEFAULT_DATE_FORMAT);
                }
            }
        }
        return obj;
    }

    private static <T> T createInstance(Class<T> cls) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Object instance = null;
        Constructor<?> defaultConstructor = null;

        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == 0) {
                defaultConstructor = constructor;
                break;
            } else {
                throw new DefaultConstructorNotFoundException("Cannot find constructor without parameters");
            }
        }

        defaultConstructor.setAccessible(true);
        instance = defaultConstructor.newInstance();
        return (T) instance;
    }

    private static void setInstanceFields(Object obj, Field field, String setterName, String dateAndTimeFormat) {
        Method setter = getSetter(obj, field.getName());
        try {
            setter.invoke(obj, GetTypeOfFieldAndCastValue.cast(field, PropertyInsert.prop.getProperty(setterName), dateAndTimeFormat));
        } catch (IllegalAccessException | InvocationTargetException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method getSetter(Object object, String fieldName) {
        Method[] methods = object.getClass().getDeclaredMethods();
        Method setter = null;
        for (Method m : methods) {
            if (m.getName().equals(getMethodName(fieldName))) {
                setter = m;
            }
        }
        if (setter == null)
            throw new SetterNotFoundException(String.format("Cannot found setter for the field %s", fieldName));
        return setter;
    }

    private static String getMethodName(String fieldName) {
        return String.format("set%s%s", fieldName.substring(0, 1).toUpperCase(), fieldName.substring(1));
    }
}
