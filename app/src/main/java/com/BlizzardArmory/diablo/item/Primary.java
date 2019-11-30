package com.BlizzardArmory.diablo.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Primary {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("textHtml")
    @Expose
    private String textHtml;

    public String getText() {
        return text;
    }

    public String getTextHtml() {
        return textHtml;
    }

}
