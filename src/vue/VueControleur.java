/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modele.Grille;
import modele.Piece;
import modele.Plateau;
import modele.Translation;

/**
 *
 * @author Epulapp
 */
public class VueControleur extends Application {

    Plateau plateau;

    @Override
    public void start(Stage primaryStage) {

        GridPane gPane = new GridPane();
        int size = 20;
        plateau = new Plateau(16, 12);
        Piece piece = new Piece(7, 6, 0);
        Piece piece_old = piece;
        BorderPane border = new BorderPane();

        for (int i = 0; i < plateau.getGrille().getHauteur(); i++) {
            for (int j = 0; j < plateau.getGrille().getLargeur(); j++) {
                gPane.add(new Rectangle(size, size, Color.GREY), i, j);
            }
        }

        for (int i = 0; i < piece.getCases().length; i++) {
            for (int j = 0; j < piece.getCases()[i].length; j++) {
                if (piece.getCases()[i][j] != null) {
                    gPane.add(new Rectangle(size, size, Color.YELLOW), i + piece.getPosX(), j + piece.getPosY());
                }
            }
        }

        plateau.getGrille().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                for (int i = 0; i < piece.getCases().length; i++) {
                    for (int j = 0; j < piece.getCases()[i].length; j++) {
                        ((Rectangle) gPane.getChildren().get(i * j)).setFill(Color.YELLOW);
                    }
                }
            }
        });

        gPane.setGridLinesVisible(true);

        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.WHITE);

        primaryStage.setTitle("Grille");
        primaryStage.setScene(scene);
        primaryStage.show();

        piece_old = piece;
        piece.translation(Translation.Bas);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (plateau.getGrille()) {
            plateau.getGrille().MAJ();
        }

    }

    private Boolean checkTranslationCase() {
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
