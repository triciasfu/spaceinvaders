package com.spaceinvaders.triciasfu.spaceinvaders;


import processing.core.PApplet;

public class Sketch extends PApplet {
    public void settings() {
        size(600, 600);
    }

    public void setup() { }

    public void draw() {
        if (mousePressed) {
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}