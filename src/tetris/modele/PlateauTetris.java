/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.modele;

import grille.modele.Plateau;
import grille.modele.Translation;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Epulapp
 */
public class PlateauTetris extends Observable implements Runnable{

    private Plateau plateau;

    public PlateauTetris() {
        plateau = new Plateau(16, 16);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            if(plateau.deplacerPiece(Translation.Bas)){
                plateau.placerPiece();
                plateau.creerNouvellePiece(FormePiece.getPieceAleatoire());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PlateauTetris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
