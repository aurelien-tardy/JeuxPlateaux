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
public class Piece {
    private Case[][] _cases;
    int _posX,_posY;
    
    
    
    
    public Boolean translation(Translation direction){
        boolean ok = false;
        switch(direction){
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
}
