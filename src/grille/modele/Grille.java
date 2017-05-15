/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.modele;


/**
 *
 * @author Epulapp
 */
public class Grille {

    private Case[][] _cases;
    int _largeur, _hauteur;

    public Grille() {
    }

    public Grille(int largeur, int hauteur) {
        _largeur = largeur;
        _hauteur = hauteur;
        _cases = new Case[_hauteur][_largeur];

    }

    public Boolean setCases(int i, int j, Case c) {
        if (i > 0 && i < _largeur && j > 0 && j < _hauteur) {
            this._cases[i][j] = c;
            return true;
        }
        return false;
    }

    public Case[][] getCases() {
        return _cases;
    }

    public int getLargeur() {
        return _largeur;
    }

    public void setLargeur(int _largeur) {
        this._largeur = _largeur;
    }

    public int getHauteur() {
        return _hauteur;
    }

    public void setHauteur(int _hauteur) {
        this._hauteur = _hauteur;
    }

}
