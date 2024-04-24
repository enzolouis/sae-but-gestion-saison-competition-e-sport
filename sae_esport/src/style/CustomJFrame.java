package style;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import controleurs.MenuControleur;
import controleurs.MenuMotionControleur;

public class CustomJFrame extends JFrame {
    CustomJPanel contentMenu;
    CustomJPanel contentPanel;
    CustomJPanel contentMenuEtPanel;
    MenuControleur controleurMenu;
    MenuMotionControleur controleurMotionMenu;

    /**
     * Ryan GAUNAND <br>
     * Création d'un JFrame avec l'application automatique
     * de la charte graphique de l'application.
     */
    public CustomJFrame() {
        super();

        this.setupMenuEtMainPanel("");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentMenuEtPanel);
        setResizable(false);
        setFont(Palette.customFont);
        setIconImage(Palette.LOGO.getImage());
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JFrame avec l'ajout d'un titre de fenêtre et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Titre de la fenêtre
     */
    public CustomJFrame(String frameTitle) {
        super(frameTitle);

        this.setupMenuEtMainPanel(frameTitle);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentMenuEtPanel);
        setResizable(false);
        setFont(Palette.customFont);
        setIconImage(Palette.LOGO.getImage());
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JFrame avec la définition d'une dimension et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Dimension de la fenêtre
     */
    public CustomJFrame(Dimension dimension) {
        super();

        this.setupMenuEtMainPanel("");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentMenuEtPanel);
        setResizable(false);
        setFont(Palette.customFont);
        setIconImage(Palette.LOGO.getImage());
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JFrame avec l'ajout d'un titre de fenêtre, la définition d'une
     * dimension et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Dimension de la fenêtre
     * @param Titre     de la fenêtre
     */
    public CustomJFrame(Dimension dimension, String frameTitle) {
        super(frameTitle);

        this.setupMenuEtMainPanel(frameTitle);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(this.contentMenuEtPanel);
        setMinimumSize(dimension);
        setBounds(Palette.SCREEN_WIDTH / 2 - dimension.width / 2, Palette.SCREEN_HEIGHT / 2 - dimension.height / 2,
                dimension.height, dimension.width);

        setResizable(false);
        setFont(Palette.customFont);
        setIconImage(Palette.LOGO.getImage());
    }

    public void setupMenuEtMainPanel(String frameTitle) {
        controleurMenu = new MenuControleur(this);
        controleurMotionMenu = new MenuMotionControleur(this);

        this.setUndecorated(true);

        this.contentMenuEtPanel = new CustomJPanel();
        this.contentMenuEtPanel.setLayout(new BorderLayout());

        this.contentMenu = new CustomJPanel(new EmptyBorder(1, 1, 1, 1));
        this.contentMenu.setBackground(Palette.BLACKLIGHTER);
        this.contentMenu.setLayout(new BorderLayout());
        this.contentMenu.addMouseMotionListener(controleurMotionMenu);
        this.contentMenu.addMouseListener(controleurMotionMenu);

        CustomJLabel logo = new CustomJLabel(frameTitle, 11);
        logo.setBorder(new EmptyBorder(0, 7, 0, 0));
        if (frameTitle.equals("Identification")) {
            logo.setIcon(new ImageIcon(Palette.LOGO.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            List<String> adminPages = Arrays.asList("Accueil Administrateur", "Statistiques de la saison",
                    "Création de tournoi", "Liste des tournois");
            List<String> arbitrePages = Arrays.asList("Accueil Arbitre", "Consultation du classement du tournoi",
                    "Résultat des matchs");

            for (String s : adminPages) {
                if (frameTitle.contains(s)) {
                    logo.setIcon(new ImageIcon(
                            Palette.ADMIN.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
                }
            }
            for (String s : arbitrePages) {
                if (frameTitle.contains(s)) {
                    logo.setIcon(new ImageIcon(
                            Palette.ARBITRE.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
                }
            }
        }

        CustomJPanel fermerEnlever = new CustomJPanel();
        fermerEnlever.setBackground(Palette.BLACKLIGHTER);
        fermerEnlever.setLayout(new FlowLayout());
        CustomJButton enlever = new CustomJButton("", 10);
        enlever.setBorder(new EmptyBorder(0, 10, 0, 10));
        enlever.setIcon(Palette.MINIMIZE);
        enlever.setBackground(Palette.GREEN);
        enlever.addActionListener(controleurMenu);

        CustomJButton fermer = new CustomJButton("", 10);
        fermer.setIcon(Palette.CLOSE);
        fermer.setBackground(Palette.REDERRORFOREGROUND);
        fermer.setBorder(new EmptyBorder(0, 10, 0, 10));
        fermer.addActionListener(controleurMenu);

        fermerEnlever.add(enlever);
        fermerEnlever.add(fermer);

        this.contentMenu.add(logo, BorderLayout.WEST);

        this.contentMenu.add(fermerEnlever, BorderLayout.EAST);
        this.contentMenu.setForeground(Palette.WHITE);

        this.contentPanel = new CustomJPanel(new EmptyBorder(10, 30, 30, 30));

        this.contentMenuEtPanel.add(this.contentMenu, BorderLayout.NORTH);
        this.contentMenuEtPanel.add(this.contentPanel, BorderLayout.CENTER);

    }

    public CustomJPanel getContentPanel() {
        return this.contentPanel;
    }

    public void closeCurrentWindow() {
        this.setVisible(false);
    }
}
