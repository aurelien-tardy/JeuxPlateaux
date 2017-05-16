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
import grille.modele.Piece;
import grille.modele.Rotation;
import grille.modele.Translation;
import javafx.scene.Group;
import static javafx.application.Application.launch;

/**
 *
 * @author Epulapp
 */
public class VueControleur_old extends Application {

    Grille grille;
    Piece piece;
    GridPane gPane;
    BorderPane border;
    
    @Override
    public void start(Stage primaryStage) {
        //initalisation des variables et dessin de la grille
        initialize(primaryStage);

        //add obsserver
        /*grille.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                for (int i = 0; i < grille.getLargeur(); i++) {
                    for (int j = 0; j < grille.getHauteur(); j++) {
                        if (i * grille.getLargeur() + j <= gPane.getChildren().size() && (gPane.getChildren().get(i * grille.getLargeur() + j)).getClass() != Group.class) {
                            ((Rectangle) gPane.getChildren().get(i * grille.getLargeur() + j)).setFill(Color.GREY);
                        }
                    }
                }

                //On dessine la piece
                for (int i = piece.getPosX(); i - piece.getPosX() < piece.getCases().length; i++) {
                    for (int j = piece.getPosY(); j - piece.getPosY() < piece.getCases()[i - piece.getPosX()].length; j++) {
                        ((Rectangle) gPane.getChildren().get(i * grille.getLargeur() + j)).setFill(Color.BLUE);
                    }
                }
            }
        });*/
        
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (grille) {
            grille.MAJ();
        }*/

    }

    private void initialize(Stage primaryStage) {
        gPane = new GridPane();
        int size = 20;
        grille = new Grille(16, 12);
        Case[][] cases = new Case[2][3];
       for (int i = 0; i <2; i++) {
            for (int j = 0; j < 3; j++) {
                cases[i][j] = new Case(Color.YELLOW);
            }
        }
       cases[0][1]=null;
       cases[0][2]=null;
        piece = new Piece(3, 6, cases);
        
        piece.rotation(Rotation.Gauche);
        piece.translation(Translation.Droite);
        
        border = new BorderPane();

        for (int i = 0; i < grille.getHauteur(); i++) {
            for (int j = 0; j < grille.getLargeur(); j++) {
                gPane.add(new Rectangle(size, size, Color.GREY), i, j);
            }
        }

        for (int i = 0; i < piece.getCases().length; i++) {
            for (int j = 0; j < piece.getCases()[i].length; j++) {
                if (piece.getCases()[i][j] != null) {
                    gPane.add(new Rectangle(size, size, piece.getCases()[i][j].getColor()), i + piece.getPosX(), j + piece.getPosY());
                }
            }
        }
        gPane.setGridLinesVisible(true);
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.WHITE);
        primaryStage.setTitle("Grille");
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
