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
    private int _posX,_posY;
    private int _typePiece;
    
    public Piece(int posX, int posY, int typePiece){
        _posX = posX;
        _posY = posY;
        _typePiece = typePiece;
        switch(typePiece){
            case 0:
                _cases = new Case[2][2];
                for(int i=0;i<2;i++){
                    for(int j=0;j<2;j++){
                        _cases[i][j] = new Case();
                    }
                }
                break;
        }
    }

    public int getPosX() {
        return _posX;
    }

    public int getPosY() {
        return _posY;
    }

    public int getTypePiece() {
        return _typePiece;
    }
    
    public Case[][] getCases(){
        return _cases;
    }
    
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
