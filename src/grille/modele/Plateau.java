/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.modele;

import java.util.Observable;

/**
 *
 * @author Epulapp
 */
public class Plateau extends Observable {

    Grille grille;
    Piece piece;

    public Plateau(int largeur, int hauteur) {
        grille = new Grille(largeur, hauteur);
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
        setChanged();
        notifyObservers();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private synchronized Boolean checkPosition(Piece new_piece) {
        for (int i = new_piece.getPosX(); i < new_piece.getPosX() + new_piece.getCases().length; i++) {
            for (int j = new_piece.getPosY(); j < new_piece.getPosY() + new_piece.getCases()[i - new_piece.getPosX()].length; j++) {
                //si la case n'est pas hros grille
                if (i < this.grille.getHauteur() && j < this.grille.getLargeur() && i>=0 && j>=0) {
                    //si la case de la piece est une case instancié (donc est un bout de la forme de la piece)
                    if (new_piece.getCases()[i - new_piece.getPosX()][j - new_piece.getPosY()] != null) {
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
        return true;
    }

    public synchronized Boolean deplacerPiece(Translation translation) {
        Piece new_piece = this.piece.clone();
        new_piece.translation(translation);
        if (checkPosition(new_piece)) {
            piece.translation(translation);
            this.setChanged();
            this.notifyObservers();
            return true;
        }
        return false;
    }

    public synchronized Boolean tournerPiece(Rotation rotation) {
        Piece new_piece = this.piece.clone();
        new_piece.rotation(rotation);
        if (checkPosition(new_piece)) {
            piece.rotation(rotation);
            this.setChanged();
            this.notifyObservers();
            return true;
        }
        return false;
    }

    public synchronized Boolean placerPiece() {
        for (int i = 0; i < piece.getCases().length; i++) {
            for (int j = 0; j < piece.getCases()[i].length; j++) {
                if (piece.getCases()[i][j] != null) {
                    if (!grille.setCases(i + piece.getPosX(), j + piece.getPosY(), piece.getCases()[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void creerNouvellePiece(int posX, int posY, Case[][] cases){
        piece = new Piece(posX, posY, cases);
        setChanged();
        notifyObservers();
    }
    
    public void creerNouvellePiece(Piece piece){
        this.piece = piece;
        setChanged();
        notifyObservers();
    }
    
    public Boolean supprimerLigne(int ligne) {
        //@TODO
        return true;
    }

}
