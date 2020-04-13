package com.BlizzardArmory;

import com.BlizzardArmory.connection.URLConstants;
import com.BlizzardArmory.ui.MainActivity;

import junit.framework.TestCase;

import org.junit.jupiter.api.Test;

class URLConstantsTest extends TestCase {

    @Test
    void getBaseURLforUserInformation() {
        MainActivity.selectedRegion = "US";
        String temp = URLConstants.getBaseURLforUserInformation();
        assertEquals("https://us.battle.net", temp);
    }

    @Test
    void getBaseURLforUserInformationCN() {
        MainActivity.selectedRegion = "CN";
        String temp = URLConstants.getBaseURLforUserInformation();
        assertEquals("https://www.battlenet.com.cn", temp);
    }

    @Test
    void getBaseURLforAPI() {
    }

    @Test
    void getRenderZoneURL() {
    }

    @Test
    void getRegion() {
    }

    @Test
    void getOWProfile() {
    }
}