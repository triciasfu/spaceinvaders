package com.spaceinvaders.triciasfu.spaceinvaders;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by triciasfu on 3/13/18.
 */

public class Alien extends PApplet{
    float x;
    float y;
    int size;
    PImage sprite;

    public void Alien(float posx, float posy, int s, PImage sprite) {
        x = posx;
        y = posy;
        size = s;
        sprite = sprite;
    }

    public void render() {
        imageMode(CENTER);
        image(sprite, x, y, size, size);
    }

    public void shoot(ArrayList bullets, double shootRate) {
        if (Math.random() < shootRate) {
            bullets.add(new Bullet(x, y, false));
        }
    }
}
