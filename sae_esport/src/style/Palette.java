package style;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

class Rgb extends Color {
    public Rgb(int r, int g, int b) {
        super(r, g, b);
    }
}

public class Palette {

    // --------------------------------------- Colors ---------------------------------------

    public static final Color PRIMARY_BACKGROUND    = new Rgb(255, 255, 255); // WHITE BLACK
    public static final Color SECONDARY_BACKGROUND  = new Rgb(  0, 123, 255); // BLACKLIGHTER BLUE
    public static final Color TIERTIARY_BACKGROUND  = new Rgb( 60,  70,  75); // REDQUIT
    public static final Color QUATERNARY_BACKGROUND = new Rgb( 25, 103, 210); // Rgb(37, 89, 168) Rgb(11, 24, 67)
    public static final Color ERROR_BACKGROUND      = new Rgb(254, 220, 222);
    public static final Color SUCCESS_BACKGROUND    = new Rgb(210, 253, 250);

    public static final Color PRIMARY_FOREGROUND    = new Rgb(  0, 123, 255); // BLACKLIGHTER BLUE
    public static final Color SECONDARY_FOREGROUND  = new Rgb(255, 255, 255); // WHITE BLACK
    public static final Color TIERTIARY_FOREGROUND  = new Rgb(177, 180, 184); // GREY
    public static final Color QUATERNARY_FOREGROUND = new Rgb(225, 228, 232); // SMOOTH_GREY
    public static final Color ERROR_FOREGROUND      = new Rgb(235,  77,  75);
    public static final Color SUCCESS_FOREGROUND    = new Rgb( 10, 200, 185);
    
    public static final Color ERROR_BORDER          = new Rgb(255, 144, 144);
    public static final Color SUCCESS_BORDER        = new Rgb( 10, 200, 185);

    // --------------------------------------- Fonts ---------------------------------------

    public static final Font FONT;

    static {
        Font loadedFont = null;
        try {
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Montserrat-Medium.ttf"))
                    .deriveFont(11f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        FONT = loadedFont;
    }

    // --------------------------------------- Icons ---------------------------------------
    
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


    // --------------------------------------- Screen properties ---------------------------------------

    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
}
