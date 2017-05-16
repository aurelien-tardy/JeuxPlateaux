/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.vue;

import grille.modele.Piece;
import grille.modele.Translation;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import tetris.modele.FormePiece;
import grille.modele.Plateau;
import grille.vue.VueControleur;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class Tetris extends Application implements Observer, KeyListener {

    /*
    
        _border.setCenter(_gPane);
        Scene scene = new Scene(_border, Color.GREY);
        primaryStage.setTitle("My Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    */
    private Plateau plateau;
    private static GridPane gPane;
    private Boolean gameOver = false;
    private VueControleur vuePlateau;
    
    @Override
    public void stop() throws Exception {
        vuePlateau.stop();
    }
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        plateau = new Plateau(16,30);
        plateau.setPiece(FormePiece.getPieceAleatoire());
        vuePlateau = new VueControleur(plateau);
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            Thread.sleep(1000);
            plateau.deplacerPiece(Translation.Bas);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tetris.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                plateau.deplacerPiece(Translation.Haut);
                break;                
                
            case KeyEvent.VK_DOWN:
                plateau.deplacerPiece(Translation.Bas);
                break;
                
            case KeyEvent.VK_LEFT:
                plateau.deplacerPiece(Translation.Gauche);
                break;                
                
            case KeyEvent.VK_RIGHT:
                plateau.deplacerPiece(Translation.Droite);
                break;                
        }
    }
}
