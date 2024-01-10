package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Palette {
	public static final Color BLACK 			 = new Color(6, 40, 50);
	public static final Color BLACKLIGHTER 		 = new Color(8, 50, 65);
	public static final Color WHITE 			 = new Color(205, 250, 250);
	public static final Color GOLD 				 = new Color(200, 150, 60);
	public static final Color BLUE 				 = new Color(0, 90, 130);
	public static final Color REDQUIT 			 = new Color(30, 40, 45);
	public static final Color GREEN 			 = new Color(10, 200, 185);
	public static final Color REDERRORFOREGROUND = new Color(235, 77, 75);
	public static final Color REDERRORBACKGROUND = new Color(254, 220, 222);
	public static final Color REDERRORBORDER 	 = new Color(255, 144, 144);
	public static final Color BLACKDARKER        = new Color(10, 60, 80);
	
    public static final Font customFont;
    
    static {
        Font loadedFont = null;
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/BeaufortforLOL-Heavy.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
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
        }
        customTextFont = loadedFont;
    }
    
    
    
    
	
	
}
