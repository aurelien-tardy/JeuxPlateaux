/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.vue;

import grille.modele.Piece;
import grille.modele.Translation;
import grille.vue.VueControleur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import puissance4.modele.PlateauPuissance4;
import tetris.modele.FormePiece;

/**
 *
 * @author Epulapp
 */
public class Puissance4 extends Application {

    private PlateauPuissance4 plateauPuissance4;
    private static VueControleur vueGrille;

    @Override
    public void start(Stage primaryStage) throws Exception {
        plateauPuissance4 = new PlateauPuissance4();
        BorderPane border = new BorderPane();
        vueGrille = VueControleur.getInstance(plateauPuissance4.getPlateau());
        plateauPuissance4.getPlateau().addObserver(vueGrille);
        border.setCenter(vueGrille);
        Scene scene = new Scene(border, plateauPuissance4.getPlateau().getGrille().getHauteur() * vueGrille.getSize(), plateauPuissance4.getPlateau().getGrille().getLargeur() * vueGrille.getSize(), Color.BEIGE);
        primaryStage.setResizable(false);

        //controleur
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!PlateauPuissance4.GAMEOVER) {
                    switch (event.getCode()) {
                        case DOWN:
                            while (plateauPuissance4.getPlateau().deplacerPiece(Translation.Bas));
                            if (plateauPuissance4.placer()) {
                                if (plateauPuissance4.win()) {
                                    break;
                                }
                            }
                            break;

                        case RIGHT:
                            plateauPuissance4.getPlateau().deplacerPiece(Translation.Droite);
                            break;

                        case LEFT:
                            plateauPuissance4.getPlateau().deplacerPiece(Translation.Gauche);
                            break;

                        case ENTER:
                            while (plateauPuissance4.getPlateau().deplacerPiece(Translation.Bas));
                            if (plateauPuissance4.placer()) {
                                if (plateauPuissance4.win()) {
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
        });

        primaryStage.setTitle("Puissance4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
