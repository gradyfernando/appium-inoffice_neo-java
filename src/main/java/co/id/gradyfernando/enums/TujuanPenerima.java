package co.id.gradyfernando.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TujuanPenerima {
    
	PenerimaJabatan("Penerima Jabatan"),
	PenerimaPersonal("Penerima Personal"),
    TembusanJabatan("Tembusan Jabatan"),
    TembusanPersonal("Tembusan Personal"),
	External("Penerima Eksternal"),
    Template("Template Penerima");

	// 
	private String name;
    private static final Map<String, TujuanPenerima> ENUM_MAP;

    TujuanPenerima(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

	static {
        Map<String,TujuanPenerima> map = new ConcurrentHashMap<String, TujuanPenerima>();
        for (TujuanPenerima instance : TujuanPenerima.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static TujuanPenerima get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

}
