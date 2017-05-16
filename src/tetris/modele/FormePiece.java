/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.modele;

import grille.modele.Case;
import grille.modele.Piece;
import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class FormePiece {

    public static Piece getPieceAleatoire() {
        Case[][] cases = null;
        int randInt = (int) (Math.random() * 2);
        switch (randInt) {
            case 0:
                cases = new Case[1][4];
                for (int i = 0; i < 4; i++) {
                    cases[0][i] = new Case(Color.AZURE);
                }
                break;

            //
            case 1:
                cases = new Case[2][3];
                cases[0][0] = new Case(Color.RED);
                cases[0][1] = new Case(Color.RED);
                cases[1][1] = new Case(Color.RED);
                cases[1][2] = new Case(Color.RED);
                break;
                
            case 2:
                
                break;
                
            case 3:
                
                break;

            default:
                cases = new Case[2][2];
                cases[0][0] = new Case(Color.RED);
                cases[0][1] = new Case(Color.RED);
                cases[1][1] = new Case(Color.RED);
                cases[1][2] = new Case(Color.RED);
                break;
        }
        
        return new Piece(4, 0, cases);
    }
}