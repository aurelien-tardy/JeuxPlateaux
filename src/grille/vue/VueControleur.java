/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.vue;

import grille.modele.Case;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import grille.modele.Plateau;
import static javafx.application.Application.launch;
import javafx.scene.Group;

/**
 *
 * @author Epulapp
 */
public class VueControleur extends GridPane implements Observer {

    private Plateau _plateau;
    private int _size;
    private static VueControleur instance;

    private VueControleur(Plateau plateau) {
        initialize(plateau);
    }

    @Override
    public void update(Observable o, Object arg) {
        // on vide la grille
        for (int i = 0; i < _plateau.getGrille().getHauteur(); i++) {
            for (int j = 0; j < _plateau.getGrille().getLargeur(); j++) {
                if (Rectangle.class.isAssignableFrom(this.getChildren().get(i * _plateau.getGrille().getLargeur() + j).getClass())) {
                    ((Rectangle) this.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(Color.GREY);
                }
            }
        }

        //on dessine la grille                
        for (int i = 0; i < _plateau.getGrille().getHauteur(); i++) {
            for (int j = 0; j < _plateau.getGrille().getLargeur(); j++) {
                if (_plateau.getGrille().getCases()[i][j] != null) {
                    ((Rectangle) this.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(_plateau.getGrille().getCases()[i][j].getColor());
                }
            }
        }

        //On dessine la piece
        for (int i = _plateau.getPiece().getPosX(); i - _plateau.getPiece().getPosX() < _plateau.getPiece().getCases().length; i++) {
            for (int j = _plateau.getPiece().getPosY(); j - _plateau.getPiece().getPosY() < _plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()].length; j++) {
                if (_plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()][j - _plateau.getPiece().getPosY()] != null) {
                    ((Rectangle) this.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(_plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()][j - _plateau.getPiece().getPosY()].getColor());
                }
            }
        }
    }

    private void initialize(Plateau plateau) {
        _plateau = plateau;
        _size = 30;

        for (int i = 0; i < _plateau.getGrille().getHauteur(); i++) {
            for (int j = 0; j < _plateau.getGrille().getLargeur(); j++) {
                this.add(new Rectangle(_size, _size, Color.GREY), i, j);
            }
        }

        for (int i = _plateau.getPiece().getPosX(); i - _plateau.getPiece().getPosX() < _plateau.getPiece().getCases().length; i++) {
            for (int j = _plateau.getPiece().getPosY(); j - _plateau.getPiece().getPosY() < _plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()].length; j++) {
                if (_plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()][j - _plateau.getPiece().getPosY()] != null) {
                    ((Rectangle) this.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(_plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()][j - _plateau.getPiece().getPosY()].getColor());
                }
            }
        }

        this.setGridLinesVisible(true);

    }

    public static VueControleur getInstance(Plateau plateau) {
        if (instance == null) {
            instance = new VueControleur(plateau);
        }
        return instance;
    }
}
