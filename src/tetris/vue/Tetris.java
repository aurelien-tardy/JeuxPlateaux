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
import grille.vue.VueControleur;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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
    private Plateau plateau;
    private VueControleur vueGrille;
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        plateau = new Plateau(16,30);
        plateau.setPiece(FormePiece.getPieceAleatoire());
        vueGrille = VueControleur.getInstance(plateau);
        border.setCenter(vueGrille);
        Scene scene = new Scene(border, Color.GREY);
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
