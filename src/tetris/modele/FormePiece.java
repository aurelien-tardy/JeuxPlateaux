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
        int randInt = (int) (Math.random() * 7);
        switch (randInt) {
            // Brick I
            case 0:
                cases = new Case[1][4];
                for (int i = 0; i < 4; i++) {
                    cases[0][i] = new Case(Color.CYAN);
                }
                break;

            // Brick O
            case 1:
                cases = new Case[2][2];
                cases[0][0] = new Case(Color.YELLOW);
                cases[0][1] = new Case(Color.YELLOW);
                cases[1][0] = new Case(Color.YELLOW);
                cases[1][1] = new Case(Color.YELLOW);
                break;

            // Brick T
            case 2:
                cases = new Case[3][2];
                cases[0][1] = new Case(Color.PURPLE);
                cases[1][0] = new Case(Color.PURPLE);
                cases[1][1] = new Case(Color.PURPLE);
                cases[2][0] = new Case(Color.PURPLE);
                break;

            // Brick S
            case 3:
                cases = new Case[3][2];
                cases[0][1] = new Case(Color.GREEN);
                cases[1][0] = new Case(Color.GREEN);
                cases[1][1] = new Case(Color.GREEN);
                cases[2][0] = new Case(Color.GREEN);
                break;

            // Brick Z
            case 4:
                cases = new Case[3][2];
                cases[0][0] = new Case(Color.RED);
                cases[1][0] = new Case(Color.RED);
                cases[1][1] = new Case(Color.RED);
                cases[2][1] = new Case(Color.RED);
                break;
                
            // Brick J
            case 5:
                cases = new Case[3][2];
                cases[0][0] = new Case(Color.BLUE);
                cases[0][1] = new Case(Color.BLUE);
                cases[1][1] = new Case(Color.BLUE);
                cases[2][1] = new Case(Color.BLUE);
                break;
             
            // Brick L
            case 6:
                cases = new Case[3][2];
                cases[0][1] = new Case(Color.RED);
                cases[1][1] = new Case(Color.RED);
                cases[2][0] = new Case(Color.RED);
                cases[2][1] = new Case(Color.RED);
        }

        return new Piece(4, 0, cases);
    }
}
