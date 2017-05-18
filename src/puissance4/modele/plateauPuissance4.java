/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.modele;

import grille.modele.Plateau;
import javafx.beans.property.SimpleIntegerProperty;
import static tetris.modele.PlateauTetris.GAMEOVER;

/**
 *
 * @author Epulapp
 */
public class plateauPuissance4 {

    private Plateau plateau;
    public static Boolean GAMEOVER;

    public plateauPuissance4() {
        GAMEOVER = false;
        plateau = new Plateau(7, 7);
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

}
