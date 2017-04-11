/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modele.Plateau;

/**
 *
 * @author Epulapp
 */
public class VueControleur extends Application {

    Plateau plateau;

    @Override
    public void start(Stage primaryStage) {
        GridPane gPane = new GridPane();
        int size = 100;
        plateau = new Plateau(5, 8);
        BorderPane border = new BorderPane();

        for (int i = 0; i < plateau.getGrille().getHauteur(); i++) {
            for (int j = 0; j < plateau.getGrille().getLargeur(); j++) {
                gPane.add(new Rectangle(size, size, Color.GREY), i, j);
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
