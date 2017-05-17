/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.vue;

import grille.modele.Translation;
import java.util.Observable;
import javafx.application.Application;
import javafx.stage.Stage;
import tetris.modele.FormePiece;
import grille.modele.Rotation;
import grille.vue.VueControleur;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import tetris.modele.PlateauTetris;

/**
 *
 * @author Epulapp
 */
public class Tetris extends Application implements Observer {

    private PlateauTetris plateauTetris;
    private VueControleur vueGrille;

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        PlateauTetris.GAMEOVER = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        plateauTetris = new PlateauTetris();
        BorderPane border = new BorderPane();
        plateauTetris.getPlateau().setPiece(FormePiece.getPieceAleatoire());
        vueGrille = VueControleur.getInstance(plateauTetris.getPlateau());
        plateauTetris.getPlateau().addObserver(vueGrille);
        border.setCenter(vueGrille);
        Scene scene = new Scene(border, Color.GREY);

        //controleur
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!PlateauTetris.GAMEOVER) {
                    switch (event.getCode()) {
                        case DOWN:
                            if (!plateauTetris.getPlateau().deplacerPiece(Translation.Bas)) {
                                plateauTetris.getPlateau().placerPiece();
                                plateauTetris.detruireLigne();
                                if (plateauTetris.isGameOver()) {
                                    break;
                                }
                                plateauTetris.getPlateau().creerNouvellePiece(FormePiece.getPieceAleatoire());
                            }
                            break;

                        case RIGHT:
                            plateauTetris.getPlateau().deplacerPiece(Translation.Droite);
                            break;

                        case LEFT:
                            plateauTetris.getPlateau().deplacerPiece(Translation.Gauche);
                            break;

                        case ENTER:
                            while (plateauTetris.getPlateau().deplacerPiece(Translation.Bas)) {
                            }
                            plateauTetris.getPlateau().placerPiece();
                            plateauTetris.detruireLigne();
                            if (plateauTetris.isGameOver()) {
                                break;
                            }
                            plateauTetris.getPlateau().creerNouvellePiece(FormePiece.getPieceAleatoire());
                            break;

                        case O:
                            plateauTetris.getPlateau().tournerPiece(Rotation.Gauche);
                            break;
                    }
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
