package com.spaceinvaders.triciasfu.spaceinvaders;

import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by triciasfu on 3/13/18.
 */

public class SpaceInvaders extends PApplet{

    Player player;
    ArrayList<Bullet> bullets = new ArrayList();
    ArrayList shields = new ArrayList();
    int shieldNum = 4;
    int invaderCols = 10;
    int invaderRows = 5;

    AlienField alienField;
    PImage invaderSprite;

    Button restartButton;

    //Sound
//    var shootSound;
    ArrayList move = new ArrayList();
//    var invaderDie;
//    var hitSound;

    @Override
    public void setup() {

        player = new Player();

        alienField = new AlienField(width/2, height/4, invaderRows, invaderCols, invaderSprite);
        alienField.init();

        for(int i = 0; i < shieldNum; i++) {
            shields.push(new Shield(width*0.1+(width*0.8)/(shieldNum-1)*i, height-150));
            shields.get(i).init();
        }
    }

    @Override
    public void draw() {
        background(0);

        if(!player.gameOver && !alienField.gameOver && !alienField.win) {
            for(int i = bullets.size()-1; i >= 0; i--) {
                bullets.get(i).update();
                bullets.get(i).render();

                if(bullets.get(i).despawn()) {
                    bullets = (ArrayList) bullets.subList(i,1);
                }
            }

            for(int i = 0; i < shieldNum; i++) {
                shields.get(i).render();
                shields.get(i).update(bullets);
            }

            alienField.update();
            player.score += alienField.alienHit(bullets);
            alienField.alienShoot(bullets);
            alienField.render();

            player.render();
            player.update();
            player.hit(bullets);
        } else if(!alienField.win && (player.gameOver || alienField.gameOver)) {
            textSize(width/10);
            fill(255);
            textAlign(CENTER, CENTER);
            text("Game Over!", width/2, height/2-width/10);
            text("Score: " + player.score, width/2, height/2);
//            restartButton.show();
        } else if(alienField.win) {
            textSize(width/10);
            fill(255);
            textAlign(CENTER, CENTER);
            text("You Win", width/2, height/2-width/10);
            text("Score: " + player.score, width/2, height/2);
//            restartButton.show();
        }


    }
}
