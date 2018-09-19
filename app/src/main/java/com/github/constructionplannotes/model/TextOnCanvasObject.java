package com.github.constructionplannotes.model;

import io.realm.RealmObject;

import java.util.Objects;

public class TextOnCanvasObject extends RealmObject {
    private String text;
    private float x;
    private float y;

    public TextOnCanvasObject() { }

    public TextOnCanvasObject(String text, float x, float y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextOnCanvasObject that = (TextOnCanvasObject) o;
        return Float.compare(that.x, x) == 0 &&
                Float.compare(that.y, y) == 0 &&
                text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, x, y);
    }
}
