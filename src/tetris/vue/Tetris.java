/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.vue;

import grille.modele.Piece;
import grille.modele.Translation;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import tetris.modele.FormePiece;
import grille.modele.Plateau;
import grille.modele.Rotation;
import grille.vue.VueControleur;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import tetris.modele.PlateauTetris;

/**
 *
 * @author Epulapp
 */
public class Tetris extends Application implements Observer {

    /*
    
        _border.setCenter(_gPane);
        Scene scene = new Scene(_border, Color.GREY);
        primaryStage.setTitle("My Game");
        primaryStage.setScene(scene);
        primaryStage.show();
     */
    private PlateauTetris plateauTetris;
    private VueControleur vueGrille;

    @Override
    public void start(Stage primaryStage) throws Exception {
        plateauTetris = new PlateauTetris();
        BorderPane border = new BorderPane();
        plateauTetris.setPlateau(new Plateau(10, 10));
        plateauTetris.getPlateau().setPiece(FormePiece.getPieceAleatoire());
        vueGrille = VueControleur.getInstance(plateauTetris.getPlateau());
        plateauTetris.getPlateau().addObserver(vueGrille);
        border.setCenter(vueGrille);
        Scene scene = new Scene(border, Color.GREY);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        if (!plateauTetris.getPlateau().deplacerPiece(Translation.Bas)) {
                            plateauTetris.getPlateau().placerPiece();
                        }
                        break;

                    case RIGHT:
                        plateauTetris.getPlateau().deplacerPiece(Translation.Droite);
                        break;

                    case LEFT:
                        plateauTetris.getPlateau().deplacerPiece(Translation.Gauche);
                        break;

                        
                    case ENTER:
                        while(plateauTetris.getPlateau().tournerPiece(Rotation.Droite)){
                            plateauTetris.getPlateau().placerPiece();
                        }
                        break;
                        
                    case W:
                        plateauTetris.getPlateau().tournerPiece(Rotation.Gauche);
                        break;

                    case X:
                        plateauTetris.getPlateau().tournerPiece(Rotation.Droite);
                        break;
                }

            }
        });
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
