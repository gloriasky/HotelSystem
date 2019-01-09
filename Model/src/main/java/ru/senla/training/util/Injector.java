package ru.senla.training.util;

import java.lang.reflect.Field;

public  class Injector {

    public static void inject(Object object, Object injectible)
    {
        final Field[] declaredFields = object.getClass().getDeclaredFields();
        final Class injectibleClass = injectible.getClass();

        boolean injected = false;

        for (Field field : declaredFields) {
            if (Object.class.equals(field.getType())) {
                continue;
            }

            if (!field.getType().isAssignableFrom(injectibleClass)) {
                continue;
            }

            field.setAccessible(true);

            try {
                field.set(object, injectible);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                break;
            }
            field.setAccessible(false);

            injected = true;
        }

        if (!injected) {
            throw new IllegalArgumentException();
        }

    }
}
