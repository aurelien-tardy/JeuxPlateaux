/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.modele;

import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class Player {

    private int score;
    private int color2;
    private Color color;
    private String name;

    public Player(Color color) {
        score = 0;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
