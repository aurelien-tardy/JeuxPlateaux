/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.modele;

import grille.modele.Grille;
import grille.modele.Piece;
import java.util.Observable;
import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class Plateau extends Observable {

    Grille grille;
    Piece piece;

    public Plateau(int hauteur, int largeur) {
        grille = new Grille(largeur, hauteur);
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Boolean deplacerPiece(Translation translation) {
        Piece new_piece = this.piece.clone();
        for (int i = new_piece.getPosX(); i < new_piece.getPosX() + new_piece.getCases().length; i++) {
            for (int j = new_piece.getPosY(); j < new_piece.getPosY() + new_piece.getCases()[i - new_piece.getPosX()].length; j++) {
                //si la case n'est pas hros grille
                if (i < this.grille.getLargeur() && j < this.grille.getHauteur()) {
                    //si la case de la piece est une case instancié (donc est un bout de la forme de la piece)
                    if (new_piece.getCases()[i - new_piece.getPosX()][j - new_piece.getPosY()].getColor() != null) {
                        //si la case de la grille au même enplacement est vide alors on peux continuer
                        if (this.grille.getCases()[i][j] != null) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        piece.translation(translation);
        this.setChanged();
        this.notifyObservers();
        return true;
    }

    public Boolean deplacerPiece(Rotation rotation) {
        Piece new_piece = this.piece.clone();
        for (int i = new_piece.getPosX(); i < new_piece.getPosX() + new_piece.getCases().length; i++) {
            for (int j = new_piece.getPosY(); j < new_piece.getPosY() + new_piece.getCases()[i - new_piece.getPosX()].length; j++) {
                //si la case n'est pas hros grille
                if (i < this.grille.getLargeur() && j < this.grille.getHauteur()) {
                    //si la case de la piece est une case instancié (donc est un bout de la forme de la piece)
                    if (new_piece.getCases()[i - new_piece.getPosX()][j - new_piece.getPosY()].getColor() != null) {
                        //si la case de la grille au même enplacement est vide alors on peux continuer
                        if (this.grille.getCases()[i][j] != null) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        //piece.rotation(rotation);
        this.setChanged();
        this.notifyObservers();
        return true;
    }
    
    public Boolean placerPiece() {
        for (int i = 0; i < piece.getCases().length; i++) {
            for (int j = 0; j < piece.getCases()[i].length; j++) {
                if (piece.getCases()[i][j] != null) {
                    if (!grille.setCases(i, j, piece.getCases()[i + piece.getPosX()][j + piece.getPosY()])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Boolean supprimerLigne(int ligne) {
        //@TODO
        return true;
    }

}
