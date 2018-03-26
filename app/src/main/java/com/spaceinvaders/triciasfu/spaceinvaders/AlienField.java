package com.spaceinvaders.triciasfu.spaceinvaders;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by triciasfu on 3/13/18.
 */

public class AlienField extends PApplet {
    int x;
    int y;
    int xSpeed = 1;
    int dead = 0;

    int alienSize = 30;
    double speedIncrease = 0.05;
    double alienShootRate = 0.001;
    double alienSpacing = 1.3;

    double shootRate;
    boolean gameOver = false;
    boolean win = false;
    PImage sprite;

    int rows;
    int cols;
    ArrayList<ArrayList<Alien>> aliens;

    //sound
    int playIndex = 0;
    int playTime = 0;


    public AlienField(int posx, int posy, int ro, int co, PImage sprite) {
        x = posx;
        y = posy;
        rows = ro;
        cols = co;
        this.sprite = sprite;
        init();
    }

    public void init() {
        aliens = new ArrayList(rows);
        for (int r = 0; r  < rows; r++) {
            aliens.set(r, new ArrayList<Alien>(cols));
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                (aliens.get(r)).set(c, new Alien((float) (x-(cols*(alienSize*alienSpacing))/2 + (alienSize*alienSpacing)*c, y-(rows*(alienSize*alienSpacing))/2 + (alienSize*alienSpacing)*r), alienSize, sprite);
            }
        }
        shootRate = alienShootRate/(rows*cols);
    }

    public void render() {
        for(int r = 0; r < this.aliens.size(); r++) {
            for(int c = 0; c < aliens.get(r).size(); c++) {
                (aliens.get(r)).get(c).render();
            }
        }
    }

    public void update() {
        boolean edge = false;
        int playTime = 0;
        shootRate = alienShootRate/(rows*cols-dead);
        if(dead == rows*cols) {
            win = true;
        }

        for(int r = 0; r < aliens.size(); r++) {
            for(int c = 0; c < aliens.get(r).size(); c++) {
                aliens.get(r).get(c).x += xSpeed;

                if(aliens.get(r).get(c).y + (aliens.get(r).get(c)).size > height - player.size*2 && !aliens.get(r).get(c).hit()) {
                    gameOver = true;
                }

                if((aliens.get(r).get(c).x + aliens.get(r).get(c).size > width || aliens.get(r).get(c).x - aliens.get(r).get(c).size < 0) && !aliens.get(r).get(c).hit) {
                    edge = true;
                }
            }
        }

        if (edge) {
            for(int r = 0; r < aliens.size(); r++) {
                for(int c = 0; c < aliens.get(r).size(); c++) {
                    aliens.get(r).get(c).y += alienSize;
                }
            }
            xSpeed *= -1;
            y += this.alienSize;
        }
        x += xSpeed;
        playTime += abs(xSpeed);

        if(playTime >=50) {
            playTime = 0;
            move.get(this.playIndex).play();
            this.playIndex += 1;

            if(playIndex > move.size()-1) {
                playIndex = 0;
            }
        }
    }

    public void alienShoot(ArrayList<Bullet> bullets) {
        for(int r = 0; r < aliens.size(); r++) {
            for(int c = 0; c < aliens.get(r).size(); c++) {
                if(!aliens.get(r).get(c).hit) {
                    aliens.get(r).get(c).shoot(bullets, alienShootRate);
                }
            }
        }
    }

    public int alienHit(ArrayList<Bullet> bullets) {
        for(int r = aliens.size()-1; r >= 0; r--) {
            for(int c = aliens.get(r).size()-1; c >= 0; c--) {

                for(int i = bullets.size()-1; i >= 0; i--) {
                    if(bullets.get(i).player) {

                        if((bullets.get(i).x+bullets.get(i).w/2 >= aliens.get(r).get(c).x-aliens.get(r).get(c).size/2 && bullets.get(i).x-bullets.get(i).w/2 <= aliens.get(r).get(c).x+aliens.get(r).get(c).size/2) && (bullets[i].y+bullets[i].h/2 <= aliens.get(r).get(c).y+aliens.get(r).get(c).size/2 && bullets[i].y-bullets[i].h/2 >= aliens.get(r).get(c).y-aliens.get(r).get(c).size/2)) {
                            aliens.get(r).subList(c, 1);
                            invaderDie.play();
                            dead++;
                            if(xSpeed < 0) {
                                xSpeed -= speedIncrease;
                            } else {
                                xSpeed += speedIncrease;
                            }
                            bullets.subList(i,1);
                            return 10;
                        }

                    }

                }

            }
        }
        return 0;
    }

    public void reset(int posx, int posy, int ro, int co, PImage sprite) {
        x = posx;
        y = posy;

        rows = ro;
        cols = co;

        xSpeed = 1;

        alienSize = 30;
        this.sprite = sprite;

        gameOver = false;

        dead = 0;
        win = false;

        playIndex = 0;
        playTime = 0;

        init();
    }
}
