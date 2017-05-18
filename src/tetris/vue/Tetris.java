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
import java.awt.Image;
import java.util.Observer;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tetris.modele.PlateauTetris;

/**
 *
 * @author Epulapp
 */
public class Tetris extends Application implements Observer {

    private PlateauTetris plateauTetris;
    private VueControleur vueGrille;
    private Text scoreValue;

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        PlateauTetris.GAMEOVER = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        plateauTetris = new PlateauTetris();
        scoreValue = new Text("0");
        scoreValue.setFont(new Font(50));
        BorderPane border_buttons = new BorderPane();
        Button b_restart = new Button("Retart");
        b_restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                plateauTetris.restart();
            }
        });
        border_buttons.setCenter(b_restart);

        //scoreValue.textProperty().bindBidirectional(plateauTetris.getScore(), new NumberStringConverter());
        bindScore(plateauTetris.getScore());

        BorderPane border = new BorderPane();

        Pane root = new Pane();
        ImageView background = new ImageView(new javafx.scene.image.Image("test.png", 400, 900, false, true));
        root.getChildren().add(background);

        vueGrille = VueControleur.getInstance(plateauTetris.getPlateau());
        plateauTetris.getPlateau().addObserver(vueGrille);

        border.setCenter(vueGrille);
        border.setLeft(border_buttons);
        border.setRight(scoreValue);

        Scene scene = new Scene(border, 500, plateauTetris.getPlateau().getGrille().getLargeur() * vueGrille.getSize(), Color.BEIGE);
        primaryStage.setResizable(false);
        //controleur
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!PlateauTetris.GAMEOVER) {
                    switch (event.getCode()) {
                        case DOWN:
                            plateauTetris.deplacerPiece(Translation.Bas);
                            break;

                        case RIGHT:
                            plateauTetris.deplacerPiece(Translation.Droite);
                            break;

                        case LEFT:
                            plateauTetris.deplacerPiece(Translation.Gauche);
                            break;

                        case ENTER:
                            plateauTetris.placerPiece();
                            break;

                        case R:
                        case O:
                            plateauTetris.tournerPiece(Rotation.Gauche);
                            break;
                    }
                } else {

                    border.setCenter(root);
                    plateauTetris.restart();
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

    public void bindScore(IntegerProperty score) {
        scoreValue.textProperty().bind(score.asString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
