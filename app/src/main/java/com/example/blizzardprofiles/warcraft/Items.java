package com.example.blizzardprofiles.warcraft;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Items {

    private JSONObject head;
    private JSONObject neck;
    private JSONObject shoulder;
    private JSONObject back;
    private JSONObject chest;
    private JSONObject shirt;
    private JSONObject wrist;
    private JSONObject hands;
    private JSONObject waist;
    private JSONObject legs;
    private JSONObject feet;
    private JSONObject finger1;
    private JSONObject finger2;
    private JSONObject trinket1;
    private JSONObject trinket2;
    private JSONObject mainHand;
    private JSONObject offHand;

    public Items(JSONObject items){

        try{
            head = (JSONObject) items.get("head");
            neck = (JSONObject) items.get("neck");
            shoulder = (JSONObject) items.get("shoulder");
            back = (JSONObject) items.get("back");
            chest = (JSONObject) items.get("chest");
            shirt = (JSONObject) items.get("shirt");
            wrist = (JSONObject) items.get("wrist");
            hands = (JSONObject) items.get("hands");
            waist = (JSONObject) items.get("waist");
            legs = (JSONObject) items.get("legs");
            feet = (JSONObject) items.get("feet");
            finger1 = (JSONObject) items.get("finger1");
            finger2 = (JSONObject) items.get("finger2");
            trinket1 = (JSONObject) items.get("trinket1");
            trinket2 = (JSONObject) items.get("trinket2");
            mainHand = (JSONObject) items.get("mainHand");
            offHand = (JSONObject) items.get("offHand");
        }catch (JSONException e){
            Log.e("Error", e.toString());
        }
    }

    public JSONObject getHead() {
        return head;
    }

    public JSONObject getNeck() {
        return neck;
    }

    public JSONObject getShoulder() {
        return shoulder;
    }

    public JSONObject getBack() {
        return back;
    }

    public JSONObject getChest() {
        return chest;
    }

    public JSONObject getShirt() {
        return shirt;
    }

    public JSONObject getWrist() {
        return wrist;
    }

    public JSONObject getHands() {
        return hands;
    }

    public JSONObject getWaist() {
        return waist;
    }

    public JSONObject getLegs() {
        return legs;
    }

    public JSONObject getFeet() {
        return feet;
    }

    public JSONObject getFinger1() {
        return finger1;
    }

    public JSONObject getFinger2() {
        return finger2;
    }

    public JSONObject getTrinket1() {
        return trinket1;
    }

    public JSONObject getTrinket2() {
        return trinket2;
    }

    public JSONObject getMainHand() {
        return mainHand;
    }

    public JSONObject getOffHand() {
        return offHand;
    }

}
