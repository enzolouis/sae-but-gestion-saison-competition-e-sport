package style;

import java.awt.Color;

import javax.swing.JSeparator;

public class CustomJSeparator extends JSeparator {

    /**
     * Ryan GAUNAND <br>
     * Création d'un JSeparator avec l'application automatique de la charte
     * graphique de l'application.
     *
     */
    public CustomJSeparator() {
        super();

        //setBackground(Palette.SMOOTH_GREY);
        setForeground(Palette.SMOOTH_GREY);
    }
}
