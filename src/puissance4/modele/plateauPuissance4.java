/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.modele;

import grille.modele.Case;
import grille.modele.Plateau;
import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class plateauPuissance4 {

    private Plateau plateau;
    public static Boolean GAMEOVER;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    public plateauPuissance4() {
        GAMEOVER = false;
        plateau = new Plateau(7, 7);
        player1 = new Player(Color.RED);
        player2 = new Player(Color.YELLOW);
        currentPlayer = player1;
        Case[][] cases = new Case[1][1];
        cases[0][0] = new Case(currentPlayer.getColor());
        plateau.creerNouvellePiece(2, 0, cases);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public static Boolean getGAMEOVER() {
        return GAMEOVER;
    }

    public static void setGAMEOVER(Boolean GAMEOVER) {
        plateauPuissance4.GAMEOVER = GAMEOVER;
    }

    public Boolean cherche4() {
        for (int ligne = 1; ligne < plateau.getGrille().getLargeur(); ligne++) {
            // Vérifie les horizontales ( - )
            if (cherche4alignes(0, ligne, 1, 0)) {
                return true;
            }
            // Première diagonale ( \ ) inférieur
            if (cherche4alignes(0, ligne, 1, 1)) {
                return true;
            }
            // Deuxième diagonale ( / ) inférieur
            if (cherche4alignes(plateau.getGrille().getHauteur() - 1, ligne, -1, 1)) {
                return true;
            }
        }

        for (int col = 0; col < plateau.getGrille().getLargeur(); col++) {
            // Vérifie les verticales ( ¦ )
            if (cherche4alignes(col, 1, 0, 1)) {
                return true;
            }
            // Première diagonale ( / ) supérieur
            if (cherche4alignes(col, 1, -1, 1)) {
                return true;
            }
            // Deuxième diagonale ( \ ) supérieur
            if (cherche4alignes(col, 1, 1, 1)) {
                return true;
            }
        }
        // On n'a rien trouvé
        return false;
    }

    private Boolean cherche4alignes(int oCol, int oLigne, int dCol, int dLigne) {
        int couleur = 0;
        int compteur = 0;

        int curCol = oCol;
        int curRow = oLigne;

        while ((curCol >= 0) && (curCol < plateau.getGrille().getHauteur()) && (curRow >= 1) && (curRow < plateau.getGrille().getLargeur())) {
            if (plateau.getGrille().getCases()[curCol][curRow].getColor() != currentPlayer.getColor()) {
                // Si la couleur change, on réinitialise le compteur
                compteur = 1;
            } else {
                // Sinon on l'incrémente
                compteur++;
            }

            // On sort lorsque le compteur atteint 4
            if ((couleur != 0) && (compteur == 4)) {
                return true;
            }

            // On passe à l'itération suivante
            curCol += dCol;
            curRow += dLigne;
        }

        // Aucun alignement n'a été trouvé
        return false;
    }
}
