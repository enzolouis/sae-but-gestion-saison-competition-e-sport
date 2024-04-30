package style;

import java.awt.Color;
import java.awt.Font;

public enum LabelType {
    TITLE(Palette.FONT.deriveFont(Palette.FONT.getStyle(), 16f), Palette.PRIMARY_BACKGROUND),
    SUBTITLE(Palette.FONT.deriveFont(Palette.FONT.getStyle(), 14f), Palette.PRIMARY_BACKGROUND),
    SUBSUBTITLE(Palette.FONT.deriveFont(Palette.FONT.getStyle(), 12f), Palette.PRIMARY_BACKGROUND),
    INPUT(Palette.FONT.deriveFont(Palette.FONT.getStyle(), 9f), Palette.TIERTIARY_BACKGROUND);

    private Font font;
    private Color foreground;

    LabelType(Font font, Color foreground) {
        this.font = font;
        this.foreground = foreground;
    }

    public Font getFont() {
        return font;
    }
    
    public Color getForeground() {
        return foreground;
    }
}
