/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Observable;

/**
 *
 * @author Epulapp
 */
public class Plateau{
    private Grille _grille;

    public Plateau(int largeur, int hauteur){
        _grille = new Grille(largeur,hauteur);
    }
    
    public Grille getGrille() {
        return _grille;
    }

    public void setGrille(Grille _grille) {
        this._grille = _grille;
    }
}
