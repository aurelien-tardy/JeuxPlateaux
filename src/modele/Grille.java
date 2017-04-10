/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Epulapp
 */
public class Grille {
    private Case[][] _cases;
    int _largeur,_hauteur;
    
    
    public Grille(){}
    public Grille(int largeur,int hauteur){
        _largeur = largeur;
        _hauteur = hauteur;
        
        _cases = new Case[_hauteur][_largeur];
        for(int i=0;i<_largeur;i++) _cases[_hauteur-1][i] = new Case();
    }
    
    public Case[][] getCases(){
        return _cases;
    }
    
    
 
}
