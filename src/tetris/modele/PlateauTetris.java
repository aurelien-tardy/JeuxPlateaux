/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.modele;

import grille.modele.Case;
import grille.modele.Grille;
import grille.modele.Plateau;
import grille.modele.Translation;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Epulapp
 */
public class PlateauTetris extends Observable implements Runnable {

    private int point;
    private Plateau plateau;
    public static Boolean GAMEOVER;
    private final IntegerProperty score;

    public PlateauTetris() {
        GAMEOVER = false;
        score = new SimpleIntegerProperty(0);
        plateau = new Plateau(20, 10);
        plateau.setPiece(FormePiece.getPieceAleatoire());
        (new Thread(this)).start();
    }

    public IntegerProperty getScore() {
        return score;
    }

    synchronized public void addScore(int s) {
        score.set(score.get() + s);
    }

    public void RazScore() {
        score.set(0);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void restart() {
        plateau.setPiece(FormePiece.getPieceAleatoire());
        plateau.setGrille(new Grille(plateau.getGrille().getLargeur(), plateau.getGrille().getHauteur()));
        score.set(0);
        PlateauTetris.GAMEOVER = false;
    }

    public synchronized int detruireLigne() {
        int nbLigne;
        final int hauteur = plateau.getGrille().getHauteur();
        final int largeur = plateau.getGrille().getLargeur();
        Case[][] cases = new Case[hauteur][largeur];
        int retard = 0;
        Boolean destroy;
        List<Integer> lineNumber = new ArrayList<Integer>();

        for (int j = 0; j < largeur; j++) {
            destroy = true;
            for (int i = 0; i < hauteur; i++) {
                if (plateau.getGrille().getCases()[i][j] == null) {
                    destroy = false;
                    break;
                }
            }
            if (destroy) {
                lineNumber.add(j);
            }
        }
        nbLigne = lineNumber.size();
        addScore(nbLigne * 100);
        for (int j = largeur - 1; j >= 0; j--) {
            if (lineNumber.size() > 0 && j == lineNumber.get(lineNumber.size() - 1)) {
                j--;
                retard++;
                lineNumber.remove(lineNumber.size() - 1);
            }
            for (int i = 0; i < hauteur; i++) {
                cases[i][j + retard] = plateau.getGrille().getCases()[i][j];
            }
        }
        plateau.setGrille(new Grille(largeur, hauteur, cases));
        return nbLigne;
    }

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
                addScore(10);
                if (!plateau.deplacerPiece(Translation.Bas)) {
                    plateau.placerPiece();
                    while (detruireLigne() > 0);
                    if (isGameOver()) {
                        break;
                    }
                    plateau.creerNouvellePiece(FormePiece.getPieceAleatoire());

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PlateauTetris.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
