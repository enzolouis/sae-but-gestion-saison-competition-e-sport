package style;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Palette {

    // couleurs de la palette 39, 60, 117
    // bleu violet : 52, 31, 151
    public static final Color BLACKLIGHTERDARKER = new Color(0, 113, 235); /* header */
    public static final Color GOLD = new Color(25,103,210);
    public static final Color GREEN = new Color(10, 200, 185); /* valid button create tournoi, minimize button */
    public static final Color GREENLIGHTER = new Color(210, 253, 250);
    public static final Color REDERRORBACKGROUND = new Color(254, 220, 222);
    public static final Color REDERRORBORDER = new Color(255, 144, 144);
    public static final Color BLACKDARKER = new Color(25,103,210);


    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(255, 255, 255); /* fond */
    public static final Color BLACKLIGHTER = new Color(0, 123, 255); /* header */
    public static final Color BLUE = new Color(0, 123, 255); /* bouton, input, fond liste page create tournoi */
    public static final Color REDQUIT = new Color(60, 70, 75);

    public static final Color SMOOTH_GREY = new Color(225, 228, 232);
    public static final Color GREY = new Color(177, 180, 184);


    /*public static final Color DARKBLUE = new Color(37, 89, 168);
    public static final Color DARKBLUE2 = new Color(11, 24, 67);*/


    public static final Color REDERRORFOREGROUND = new Color(235, 77, 75);

    

    // polices
    public static final Font customFont;
    public static final Font customTextFont;
    public static final Font customTextFont2;

    static {
        Font loadedFont = null;
        // fonts/BeaufortforLOL-Heavy.ttf
        // fonts/Montserrat-Black.ttf pour les titres des page
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Montserrat-Medium.ttf"))
                    .deriveFont(11f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        customFont = loadedFont;
    }

    static {
        Font loadedFont = null;
        // fonts/Spiegel_TT_SemiBold.ttf
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Montserrat-Medium.ttf")).deriveFont(11f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        customTextFont = loadedFont;
    }

    static {
        Font loadedFont = null;
        // fonts/Spiegel_TT_SemiBold.ttf
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/BeaufortforLOL-Heavy.ttf")).deriveFont(11f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        customTextFont2 = loadedFont;
    }

    public static final ImageIcon OEIL_INVISIBLE_ICON = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/hidden-eye.png")).getImage()
                    .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon OEIL_VISIBLE_ICON = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/visible-eye.png")).getImage()
                    .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon ACCOUNT_LOGO = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/account.png")).getImage()
                    .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon LOGO = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/app-logo-header.png")).getImage()
                    .getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon LOGO_LARGE = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/app-logo-windows.png")).getImage());


    public static final ImageIcon MINIMIZE = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/minimize.png")).getImage()
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon CLOSE = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/close.png")).getImage()
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon ADMIN = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/app-logo-admin.png")).getImage()
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon ARBITRE = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/app-logo-referee.png")).getImage()
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

    public static final ImageIcon LOGINIMAGE = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/login-image.jpg")).getImage()
                    .getScaledInstance(426, 325, java.awt.Image.SCALE_SMOOTH));
    
    public static final ImageIcon CONNECT = new ImageIcon(
            new ImageIcon(Palette.class.getClassLoader().getResource("assets/connect.png")).getImage()
                    .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));


    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
}
