package com.BlizzardArmory;

import com.BlizzardArmory.ui.MainActivity;

import junit.framework.TestCase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class URLConstantsTest extends TestCase {

    @Test
    void getD3URLBtagProfile() {
        String test = URLConstants.getD3URLBtagProfile("Alexchaos#1714");
        assertEquals("/d3/profile/Alexchaos-1714/?locale=en_US&", test);
    }

    @Test
    void getD3HeroURL() {
        String test = URLConstants.getD3HeroURL(143534636, "Alexchaos#1714");
        assertEquals("/d3/profile/Alexchaos-1714/hero/143534636?locale=en_US&", test);
        String testNotEqual = URLConstants.getD3HeroURL(56856634, "BoB#1714");
        assertNotEquals("/d3/profile/Alexchaos-1714/hero/143534636?locale=en_US&", testNotEqual);
    }

    @Test
    void getD3HeroItemsURL() {
        String test = URLConstants.getD3HeroItemsURL(143534636, "Alexchaos#1714");
        assertEquals("/d3/profile/Alexchaos-1714/hero/143534636/items?locale=en_US&", test);
        String testNotEqual = URLConstants.getD3HeroURL(56856634, "BoB#1714");
        assertNotEquals("/d3/profile/Alexchaos-1714/hero/143534636/items?locale=en_US&", testNotEqual);
    }

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