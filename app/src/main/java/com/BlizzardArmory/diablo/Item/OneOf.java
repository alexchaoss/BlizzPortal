package com.BlizzardArmory.diablo.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OneOf {

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
