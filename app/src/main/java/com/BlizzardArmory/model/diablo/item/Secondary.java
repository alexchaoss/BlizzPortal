package com.BlizzardArmory.model.diablo.item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * The type Secondary.
 */
public class Secondary {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("textHtml")
    @Expose
    private String textHtml;

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets text html.
     *
     * @return the text html
     */
    public String getTextHtml() {
        return textHtml;
    }

}
