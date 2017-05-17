/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.modele;

import grille.modele.Plateau;
import grille.modele.Translation;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Epulapp
 */
public class PlateauTetris extends Observable implements Runnable {

    private Plateau plateau;
    public static Boolean GAMEOVER;

    public PlateauTetris() {
        GAMEOVER = false;
        plateau = new Plateau(15, 10);
        (new Thread(this)).start();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    /*public Boolean detruireLine() {
        Case[][] cases = new Case[plateau.getGrille().getHauteur()][plateau.getGrille().getLargeur()];
        int incremente = -1;
        int lastIndex;
        Boolean destroy;

        ArrayList<int> lineNumber = new ArrayList<int>();
        for (int i = 0; i < plateau.getGrille().getHauteur(); i++) {
            destroy = true;
            for (int j = 0; j < plateau.getGrille().getLargeur(); j++) {
                if (plateau.getGrille()[i][j] == null) {
                    destroy = false;
                }
            }
            if (destroy) {
                lineNumber.add(i);
            }
        }

        for (Int i : lineNumber) {
            for (int j = 0; j < plateau.getGrille().getLargeur(); j++) {
                if(lastIndex == i){
                    incremente++;
                } else{ incremente = 0;}
            }
        }
    }*/
    public Boolean isGameOver() {
        for (int i = 0; i < plateau.getGrille().getHauteur(); i++) {
            if (plateau.getGrille().getCases()[i][0] != null) {
                PlateauTetris.GAMEOVER = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (!PlateauTetris.GAMEOVER) {
            try {
                Thread.sleep(1000);
                if (!plateau.deplacerPiece(Translation.Bas)) {
                    plateau.placerPiece();
                    if (isGameOver()) {
                        break;
                    }
                    plateau.creerNouvellePiece(FormePiece.getPieceAleatoire());
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PlateauTetris.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
