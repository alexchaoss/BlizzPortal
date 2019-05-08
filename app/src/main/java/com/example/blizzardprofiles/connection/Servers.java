package com.example.blizzardprofiles.connection;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Servers {
    SERVER1(0,"339a9ad89d9f4acf889b025ccb439567","T7JgIh6015wydLv8flBwK5epsY4Qbqwf"),
    SERVER2(1,"802e34d5533a49679014e339bd5d53b1","NLmFEg7H3JaEgUFkOcUz769iUx8e5I9L"),
    SERVER3(2,"8a2f47b786e14184833aea244a2bcc90","nzXa9Y13YmNuyTx85LG8yb5GRj4NQYZT"),
    SERVER4(3,"b741d536b91049f98506b0a23f29bae0","Rjh8bzZ05uUtw1iffCEEcZ42F3A29u5K");

    private final String clientKey;
    private final String secretKey;
    private int ordinal;

    private static final Map<Integer, Servers> lookup = new HashMap<Integer, Servers>();

    static {
        int ordinal = 0;
        for (Servers servers : EnumSet.allOf(Servers.class)) {
            lookup.put(ordinal, servers);
            ordinal += 1;
        }
    }


    Servers(int ordinal, String clientKey, String secretKey){
        this.ordinal = ordinal;
        this.clientKey = clientKey;
        this.secretKey = secretKey;
    }

    public static Servers fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
