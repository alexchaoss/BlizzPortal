package com.BlizzardArmory.warcraft;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Gear {

    private JSONObject head;
    private JSONObject neck;
    private JSONObject shoulder;
    private JSONObject back;
    private JSONObject chest;
    private JSONObject shirt;
    private JSONObject tabard;
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

    public Gear(JSONObject items){

        try{
            head = (JSONObject) items.get("head");
        }catch (JSONException e){
            head = null;
            Log.e("Error", e.toString());
        }
        try{
            neck = (JSONObject) items.get("neck");
        }catch (JSONException e){
            neck  = null;
        Log.e("Error", e.toString());
         }
        try{
            shoulder = (JSONObject) items.get("shoulder");
        }catch (JSONException e){
            shoulder = null;
        Log.e("Error", e.toString());
        }
        try{
            back = (JSONObject) items.get("back");
        }catch (JSONException e){
            back = null;
        Log.e("Error", e.toString());
        }
        try{
            chest = (JSONObject) items.get("chest");
        }catch (JSONException e){
            chest = null;
        Log.e("Error", e.toString());
        }
        try{
            shirt = (JSONObject) items.get("shirt");
        }catch (JSONException e){
            shirt = null;
        Log.e("Error", e.toString() + ", " +  shirt);
        }
        try{
            tabard = (JSONObject) items.get("tabard");
        }catch (JSONException e){
            tabard = null;
        Log.e("Error", e.toString() + ", " + tabard);
        }
        try{
            wrist = (JSONObject) items.get("wrist");
        }catch (JSONException e){
            wrist = null;
        Log.e("Error", e.toString());
        }
        try{
            hands = (JSONObject) items.get("hands");
        }catch (JSONException e){
            hands = null;
        Log.e("Error", e.toString());
        }
        try{
            waist = (JSONObject) items.get("waist");
        }catch (JSONException e){
            waist = null;
        Log.e("Error", e.toString());
        }
        try{
            legs = (JSONObject) items.get("legs");
        }catch (JSONException e){
            legs = null;
        Log.e("Error", e.toString());
        }
        try{
            feet = (JSONObject) items.get("feet");
        }catch (JSONException e){
            feet = null;
        Log.e("Error", e.toString());
        }
        try{
            finger1 = (JSONObject) items.get("finger1");
        }catch (JSONException e){
            finger1 = null;
        Log.e("Error", e.toString());
        }
        try{
            finger2 = (JSONObject) items.get("finger2");
        }catch (JSONException e){
            finger2 = null;
        Log.e("Error", e.toString());
        }
        try{
            trinket1 = (JSONObject) items.get("trinket1");
        }catch (JSONException e){
            trinket1 = null;
        Log.e("Error", e.toString());
        }
        try{
            trinket2 = (JSONObject) items.get("trinket2");
        }catch (JSONException e){
            trinket2 = null;
        Log.e("Error", e.toString() + ", " + trinket2);
        }
        try{
            mainHand = (JSONObject) items.get("mainHand");
        }catch (JSONException e){
            mainHand = null;
        Log.e("Error", e.toString() + ", " +  mainHand);
        }
        try{
            offHand = (JSONObject) items.get("offHand");
        }catch (JSONException e){
            offHand = null;
        Log.e("Error", e.toString() + ", " + offHand);
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

    public JSONObject getTabard() {
        return tabard;
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
