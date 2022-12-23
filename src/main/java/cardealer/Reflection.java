package cardealer;

import cardealer.carinfo.CarInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author Moussa
 */
public class Reflection {
    private static final Logger LOGGER = LogManager.getLogger(Reflection.class);

    @SuppressWarnings("java:S3011")
    public static void main(String... args) {
        String className = "cardealer.cardealer.StandardCarAndWarrantyDealer";
        Class<?> class1;
        try {
            class1 = Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.fatal(e.getMessage());
            return;
        }

        LOGGER.info("Class:  {}", class1.getCanonicalName());
        String modifiers = Modifier.toString(class1.getModifiers());
        LOGGER.info("Modifiers:  {}", modifiers);

        Class<?> superclass = class1.getSuperclass();
        LOGGER.info("Parent Class:");
        LOGGER.info("{}", superclass.getCanonicalName());

        LOGGER.info("Fields:");
        for (Field field : class1.getDeclaredFields()) {
            String fieldModifiers = Modifier.toString(field.getModifiers());
            LOGGER.info("{} {} {}", fieldModifiers, field.getType(), field.getName());
        }

        LOGGER.info("Constructors:");
        for (java.lang.reflect.Constructor<?> constructor : class1.getDeclaredConstructors()) {
            String constructorModifiers = Modifier.toString(constructor.getModifiers());
            String types = Arrays.toString(constructor.getParameterTypes());
            // Remove the brackets of the array string.
            types = types.substring(1, types.length() - 1);
            LOGGER.info("{} {}({})", constructorModifiers, constructor.getName(), types);
        }

        Object instance;
        try {
            instance = class1.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            LOGGER.fatal(e.getMessage());
            return;
        }

        Method hasCarMethod;
        try {
            hasCarMethod = class1.getMethod("hasCar", CarInfo.class);
        } catch (NoSuchMethodException e) {
            LOGGER.fatal(e.getMessage());
            return;
        }

        CarInfo carInfo = new CarInfo("Test", "Example", 2015, "Black");

        try {
            boolean hasCar = (boolean) hasCarMethod.invoke(instance, carInfo);
            LOGGER.info("hasCar({}): {}", carInfo, hasCar);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.fatal(e.getMessage());
            return;
        }

        Method addCarMethod;
        try {
            addCarMethod = class1.getSuperclass().getSuperclass().getDeclaredMethod("addCar", CarInfo.class);
        } catch (NoSuchMethodException e) {
            LOGGER.fatal(e.getMessage());
            return;
        }

        String addCarModifiers = Modifier.toString(addCarMethod.getModifiers());
        String addCarTypes = Arrays.toString(addCarMethod.getParameterTypes());
        // Remove the brackets of the array string.
        addCarTypes = addCarTypes.substring(1, addCarTypes.length() - 1);
        LOGGER.info("{} {} {}({})", addCarModifiers, addCarMethod.getReturnType(), addCarMethod.getName(), addCarTypes);

        addCarMethod.setAccessible(true);
        LOGGER.info("{} has been made accessible.", addCarMethod.getName());

        try {
            addCarMethod.invoke(instance, carInfo);
            LOGGER.info("addCar({}) has been invoked.", carInfo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.fatal(e.getMessage());
            return;
        }

        try {
            boolean hasCar = (boolean) hasCarMethod.invoke(instance, carInfo);
            LOGGER.info("hasCar({}): {}", carInfo, hasCar);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.fatal(e.getMessage());
        }
    }
}
