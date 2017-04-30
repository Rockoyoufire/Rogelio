package com.longisland_219408hotmail.mathematicswolf.model;

/**
 * Created by edgararana on 25/04/17.
 */

public class Formulario {
    String name;
    String image;

    public Formulario(String name, String image) {
        this.name= name;
        this.image= image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        image = image;
    }
}
