package ca.ulaval.ima.ali_choix.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static ServiceLocator locator;
    private static final Map<String, Object> ServiceMap = new HashMap<>();

    public static ServiceLocator getInstance() {
        if (locator == null) {
            locator = new ServiceLocator();
        }
        return locator;
    }

    public void subscribeService(Class<?> interfaceClass, Object interfaceImplementation) {
        ServiceMap.put(interfaceClass.getName(), interfaceImplementation);
    }

    public Object get(Class<?> interfaceClass) {
        return ServiceMap.get(interfaceClass.getName());
    }
}
