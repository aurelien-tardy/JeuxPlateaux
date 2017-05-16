/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.modele;

import grille.modele.Plateau;
import grille.modele.Rotation;
import grille.modele.Translation;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 *
 * @author Epulapp
 */
public class PlateauTetris extends Observable {

    private Plateau plateau;

    public PlateauTetris() {
        plateau = new Plateau(16, 30);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}
