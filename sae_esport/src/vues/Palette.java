package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Palette {
	public static final Color BLACK = new Color( 6, 40, 50);
	public static final Color WHITE = new Color(205, 250, 250);
	public static final Color BLUE = new Color(200, 150, 60);
	public static final Color BLUEBLUE = new Color(0, 90, 130);
	public static final Color REDRED = new Color(30, 40, 45);
	
	
	public static final Color BLACKMOINS = new Color(5, 35, 47);
	public static final Color BLACKPLUS = new Color(10, 60, 80);
	public static final Color BLACKMOINS2 = new Color(8, 50, 65);
	public static final Color FULLBLACK = new Color(0, 0, 15);
	
	public static final Color GREEN = new Color(10, 200, 185);
	public static final Color ERRORCOLOR = new Color(235, 77, 75);
    
    
    public static final Font customFont;
    
    static {
        Font loadedFont = null;
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/BeaufortforLOL-Heavy.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Gérer l'exception si nécessaire (peut-être fournir une police de secours par défaut, lancer une exception personnalisée, etc.)
        }
        customFont = loadedFont;
    }
    
    public static final Font customTextFont;
    
    static {
        Font loadedFont = null;
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Spiegel_TT_SemiBold.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Gérer l'exception si nécessaire (peut-être fournir une police de secours par défaut, lancer une exception personnalisée, etc.)
        }
        customTextFont = loadedFont;
    }
    
    
    
    
	
	
}
