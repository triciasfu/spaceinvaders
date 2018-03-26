package com.spaceinvaders.triciasfu.spaceinvaders;

import processing.core.PApplet;

/**
 * Created by triciasfu on 3/13/18.
 */

public class Bullet extends PApplet {
    float x;
    float y;
    int w = 5;
    int h = 10;
    int speed = 8;
    boolean player;

    public Bullet(float posx, float posy, boolean p) {
        x = posx;
        y = posy;
        player = p;
    }

    public void render() {
        rectMode(CENTER);
        noStroke();

        if (player) {
            fill(0, 255, 0);
        } else {
            fill(255);
        }

        rect(this.x, this.y, this.w, this.h);
    }

    public void update() {
        if(player) {
            y -= speed;
        } else {
            y += speed/2 ;
        }
    }

    public boolean despawn() {
        if(player) {
            if(y < - h) {
                return true;
            }
        } else {
            if(this.y > height+this.h) {
                return true;
            }
        }

        return false;
    }
}