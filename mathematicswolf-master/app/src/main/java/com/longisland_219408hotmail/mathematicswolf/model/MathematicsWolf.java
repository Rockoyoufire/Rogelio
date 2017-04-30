package com.longisland_219408hotmail.mathematicswolf.model;

/**
 * Created by edgararana on 25/04/17.
 */

public class MathematicsWolf {
    String name;
    int image;

    public MathematicsWolf(String name, int image) {
        this.name= name;
        this.image= image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        image = image;
    }
}
