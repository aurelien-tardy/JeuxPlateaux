/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.vue;

import grille.modele.Case;
import grille.modele.Grille;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import grille.modele.Plateau;
import static javafx.application.Application.launch;

/**
 *
 * @author Epulapp
 */
public class VueControleur extends GridPane implements Observer {

    private Plateau _plateau;
    private GridPane _gPane;
    private BorderPane _border;
    private int _size;

    public VueControleur() {
        initialize();
    }

    @Override
    public void update(Observable o, Object arg) {
        // on vide la grille
        for (int i = 0; i < _plateau.getGrille().getLargeur(); i++) {
            for (int j = 0; j < _plateau.getGrille().getHauteur(); j++) {
                ((Rectangle) _gPane.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(Color.GREY);
            }
        }

        //on dessine la grille                
        for (int i = 0; i < _plateau.getGrille().getLargeur(); i++) {
            for (int j = 0; j < _plateau.getGrille().getHauteur(); j++) {
                if (_plateau.getGrille().getCases()[i][j] != null) {
                    ((Rectangle) _gPane.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(_plateau.getGrille().getCases()[i][j].getColor());
                }
            }
        }

        //On dessine la piece
        for (int i = _plateau.getPiece().getPosX(); i - _plateau.getPiece().getPosX() < _plateau.getPiece().getCases().length; i++) {
            for (int j = _plateau.getPiece().getPosY(); j - _plateau.getPiece().getPosY() < _plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()].length; j++) {
                ((Rectangle) _gPane.getChildren().get(i * _plateau.getGrille().getLargeur() + j)).setFill(_plateau.getPiece().getCases()[i - _plateau.getPiece().getPosX()][j - _plateau.getPiece().getPosY()].getColor());
            }
        }
    }

    private void initialize() {
        _gPane = new GridPane();
        _border = new BorderPane();
        _size = 20;
        _plateau.setGrille(new Grille(16, 12));
        Case[][] cases = new Case[3][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cases[i][j] = new Case(Color.YELLOW);
            }
        }

        _border = new BorderPane();

        for (int i = 0; i < _plateau.getGrille().getHauteur(); i++) {
            for (int j = 0; j < _plateau.getGrille().getLargeur(); j++) {
                _gPane.add(new Rectangle(_size, _size, Color.GREY), i, j);
            }
        }

        for (int i = 0; i < _plateau.getPiece().getCases().length; i++) {
            for (int j = 0; j < _plateau.getPiece().getCases()[i].length; j++) {
                if (_plateau.getPiece().getCases()[i][j] != null) {
                    _gPane.add(new Rectangle(_size, _size, _plateau.getPiece().getCases()[i][j].getColor()), i + _plateau.getPiece().getPosX(), j + _plateau.getPiece().getPosY());
                }
            }
        }

        _gPane.setGridLinesVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
