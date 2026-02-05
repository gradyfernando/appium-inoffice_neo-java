package co.id.gradyfernando.config;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Route {

	Login("/login"),
	Dashboard("/dashboard"),
	AddSuratKeluar("/letter/outgoing-letter/create");


	// 
	private String name;
    private static final Map<String, Route> ENUM_MAP;

    Route(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

	static {
        Map<String,Route> map = new ConcurrentHashMap<String, Route>();
        for (Route instance : Route.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Route get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

}