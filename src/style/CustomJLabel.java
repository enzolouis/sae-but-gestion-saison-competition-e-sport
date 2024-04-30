package style;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CustomJLabel extends JLabel {

    /**
     * Ryan GAUNAND <br>
     * Création d'un JLabel avec la définition d'un texte, la taille du texte et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param text Widget text
     * @param fontSize Widget font size
     */
    public CustomJLabel(String text, int fontSize) {
        super(text);

        setFont(Palette.FONT);

        setHorizontalAlignment(SwingConstants.CENTER);
        //setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JLabel avec la définition d'un texte, la taille du texte, de
     * bordure vide et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param text Widget text
     * @param fontSize Widget font size
     * @param border Widget empty border
     */
    public CustomJLabel(String text, int fontSize, EmptyBorder border) {
        super(text);

        setFont(Palette.FONT);

        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(border);
    }

    public void setupLabel(LabelType type) {
        setForeground(type.getForeground());
        setFont(type.getFont());       
    }
}
