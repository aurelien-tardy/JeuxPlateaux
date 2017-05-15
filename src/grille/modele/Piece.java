/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.modele;

import java.util.Observable;
import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class Piece extends Observable {

    private Case[][] _cases;
    private int _posX, _posY;

    public Piece(int posX, int posY, Case[][] cases) {
        _posX = posX;
        _posY = posY;
        _cases = cases;
    }
    
    public Boolean translation(Translation direction) {
        boolean ok = false;
        switch (direction) {
            case Gauche:
                _posX--;
                break;
            case Droite:
                _posX++;
                break;
            case Haut:
                _posY--;
                break;
            case Bas:
                _posY++;
                break;
        }
        return ok;
    }

    public Case[][] getCases() {
        return _cases;
    }

    public void setCases(Case[][] _cases) {
        this._cases = _cases;
    }

    public int getPosX() {
        return _posX;
    }

    public void setPosX(int _posX) {
        this._posX = _posX;
    }

    public int getPosY() {
        return _posY;
    }

    public void setPosY(int _posY) {
        this._posY = _posY;
    }

    public Piece clone() {
        Piece new_piece = new Piece(this.getPosX(), this.getPosY(), this.getCases());
        new_piece.setCases(this.getCases());
        return new_piece;
    }
}
