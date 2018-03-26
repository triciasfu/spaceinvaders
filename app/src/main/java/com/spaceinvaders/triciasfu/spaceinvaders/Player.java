package com.spaceinvaders.triciasfu.spaceinvaders;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Created by triciasfu on 3/13/18.
 */

public class Player extends PApplet {
    int size = 30;
    int x = width/2;
    int y = height-(20 + size);
    int h = 10;
    int lives = 3;
    int score = 0;

    int speed = 5;
    boolean moveLeft = false;
    boolean moveRight = false;

    boolean gameOver = false;
    
    public void render() {
        rectMode(CENTER);

        fill(0, 255, 0);
        noStroke();
        rect(x, y, size, h);
        rect(x, y-6, 5, 5);

        for(int i = 0; i < lives; i++) {
            rect(size + i*(size+5), y+size, size, h);
            rect(size + i*(size+5), y-6+size, 5, 5);
        }

        fill(255);
        textAlign(LEFT, TOP);
        textSize(20);
        text(new String("Score: " + score), 0, 0);
    }
    
    public void update() {
        if(moveLeft) {
            move(-1);
        } else if(moveRight) {
            move(1);
        }

        if(x < 0) {
            x = width;
        }

        if(x > width) {
            x = 0;
        }

        if(lives <= 0) {
            gameOver = true;
        }
    }
    
    public void move(int speedMult) {
        x += (speed*speedMult);
    }
    
    public Bullet shoot() {
        shootSound.play();
        return new Bullet(x, y, true);
    }
    
    public void hit(ArrayList<Bullet> bullets) {
        for(int i = bullets.size()-1; i >= 0; i--) {
            if(!bullets.get(i).player) {
                if((bullets.get(i).x > x-size/2 && bullets.get(i).x < x+size/2) && (bullets.get(i).y > y-h/2 && bullets.get(i).y < y+h/2)) {
                    bullets.subList(i, 1);
                    lives--;
                    hitSound.play();
                }
            }
        }
    }
    
    public void reset() {
        size = 30;
        x = width/2;
        y = height-(20 + size);
        h = 10;
        lives = 3;
        score = 0;

        speed = 5;

        moveLeft = false;
        moveRight = false;

        gameOver = false;
    }
}


