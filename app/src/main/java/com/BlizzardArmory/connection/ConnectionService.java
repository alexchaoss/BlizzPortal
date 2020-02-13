package com.BlizzardArmory.connection;

import java.io.IOException;


public class ConnectionService {

    public static boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 us.battle.net";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }
}


