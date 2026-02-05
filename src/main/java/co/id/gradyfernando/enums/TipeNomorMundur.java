package co.id.gradyfernando.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TipeNomorMundur {

	Tanpa("Tanpa"),
	Otomatis("Otomatis"),
	Manual("Manual Nomor Sub");


	// 
	private String name;
    private static final Map<String, TipeNomorMundur> ENUM_MAP;

    TipeNomorMundur(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

	static {
        Map<String,TipeNomorMundur> map = new ConcurrentHashMap<String, TipeNomorMundur>();
        for (TipeNomorMundur instance : TipeNomorMundur.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static TipeNomorMundur get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

}