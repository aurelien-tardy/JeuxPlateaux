/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grille.modele;

import javafx.scene.paint.Color;

/**
 *
 * @author Epulapp
 */
public class Case {

    private Color _color;

    public Case(Color color) {
        this._color = color;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color _color) {
        this._color = _color;
    }

}
