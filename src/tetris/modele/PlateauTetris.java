/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.modele;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import grille.modele.Case;
import grille.modele.Grille;
import grille.modele.Plateau;
import grille.modele.Translation;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Epulapp
 */
public class PlateauTetris extends Observable implements Runnable {

    private int point;
    private Plateau plateau;
    public static Boolean GAMEOVER;

    public PlateauTetris() {
        GAMEOVER = false;
        plateau = new Plateau(30, 10);
        (new Thread(this)).start();
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

    public void detruireLigne() {
        final int hauteur = plateau.getGrille().getHauteur();
        final int largeur = plateau.getGrille().getLargeur();
        Case[][] cases = new Case[hauteur][largeur];
        int incremente = 0;
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

        for (int j = largeur - 1; j >= 0; j--) {
            if (j - incremente >= 0) {
                if (lineNumber.size() > 0 && j == lineNumber.get(lineNumber.size() - 1)) {
                    incremente++;
                    lineNumber.remove(lineNumber.size() - 1);
                }
                for (int i = 0; i < hauteur; i++) {
                    cases[i][j] = plateau.getGrille().getCases()[i][j - incremente];
                }
            }
        }
        plateau.setGrille(new Grille(largeur, hauteur, cases));
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
                if (!plateau.deplacerPiece(Translation.Bas)) {
                    plateau.placerPiece();
                    detruireLigne();
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
