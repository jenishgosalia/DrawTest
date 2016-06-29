package com.springer.test;

/**
 * Created by jenish on 29/06/2016.
 */
public class Element {
    public enum DEFAULT_COLOR {
        CANVAS_ROW("|"),
        CANVAS_COLUMN("-"),
        LINE("x"),
        RECTANGLE("x"),
        BUCKET_FILL("o"),
        EMPTY(" ");

        String color;

        private DEFAULT_COLOR(String c) {
            color = c;
        }

        public String getColor(){
            return color;
        }
    }

    private  String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
