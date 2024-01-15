package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Palette {
	
	//couleurs de la palette
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
	
	//police de titre
    public static final Font customFont;
    public static final Font customTextFont;
    
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
    
    public static final ImageIcon OEIL_INVISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseInvisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon OEIL_VISIBLE_ICON = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("oeilMotDePasseVisible.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon LOGO = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("logoApp.png")).getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon MINIMIZE = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("minimize.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
	
	public static final ImageIcon CLOSE = new ImageIcon(new ImageIcon(IdentificationVue.class.getClassLoader().getResource
			("close.png")).getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));
}
