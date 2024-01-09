package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleurs.ChoixArbitreControleur;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJScrollPane;
import style.CustomJSeparator;
import style.CustomJTextField;

public class ChoixArbitreVue extends CustomJFrame {

	private CustomJPanel contentPanel;
    private ChoixArbitreControleur controleur;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
        			ChoixArbitreVue frame = new ChoixArbitreVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ChoixArbitreVue() throws Exception {
    	super(new Dimension(600, 600), "Choix d'arbitre");
    	
    	ImageIcon icon = new ImageIcon("src\\logo_app.png");
    	this.setIconImage(icon.getImage());
    	
    	this.controleur = new ChoixArbitreControleur(this);
    	contentPanel = this.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Choix des arbitres", 25);
        titleTop.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(10, 0, 0, 0));
        contentPanel.add(panelMiddle);
        
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {266, 133};
        gbl_panelMiddle.columnWidths = new int[] {399};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 0.0};
        panelMiddle.setLayout(gbl_panelMiddle);
        
        // Panel middle --> Panel Liste : Ligne 1 et 2
        CustomJPanel panelArbitreList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 4, 10, 10)); 
        CustomJScrollPane scrollpanelArbitreList = new CustomJScrollPane(panelArbitreList);
        
        GridBagConstraints gbc_scrollpanelArbitreList = new GridBagConstraints();
        gbc_scrollpanelArbitreList.insets = new Insets(0, 0, 0, 0);
        gbc_scrollpanelArbitreList.fill = GridBagConstraints.BOTH;
        gbc_scrollpanelArbitreList.gridx = 0;
        gbc_scrollpanelArbitreList.gridy = 0;
        panelMiddle.add(scrollpanelArbitreList, gbc_scrollpanelArbitreList);
        
        for (int i = 1; i <= 100; i++) {
        	CustomJButton button = new CustomJButton("Arbitre n°" + i, 15);
        	button.addActionListener(this.controleur);
        	panelArbitreList.add(button);
		}
        
        // Panel middle --> Panel Action : Ligne 3
        CustomJPanel panelArbitreActions = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(2, 3, 10, 10));
        GridBagConstraints gbc_panelArbitreActions = new GridBagConstraints();
        gbc_panelArbitreActions.fill = GridBagConstraints.BOTH;
        gbc_panelArbitreActions.gridx = 0;
        gbc_panelArbitreActions.gridy = 1;
        panelMiddle.add(panelArbitreActions, gbc_panelArbitreActions);
        
        CustomJLabel labelSelectedTournoiJLabel = new CustomJLabel("Tournoi sélectioné : Aucun", 12);
        panelArbitreActions.add(labelSelectedTournoiJLabel);
        
        CustomJLabel labelSelectedArbitreJLabel = new CustomJLabel("Arbitre sélectioné : Aucun", 12);
        panelArbitreActions.add(labelSelectedArbitreJLabel);
        
        // Panel for Login (Label + TextField)
        CustomJPanel panelActionLoginArbitre = new CustomJPanel(new EmptyBorder(10, 10, 10, 10));
        GridBagLayout gbl_panelActionLoginArbitre = new GridBagLayout();
        gbl_panelActionLoginArbitre.rowHeights = new int[] {0};
        gbl_panelActionLoginArbitre.columnWidths = new int[] {0, 0};
        gbl_panelActionLoginArbitre.columnWeights = new double[]{0.05, 0.95};
        gbl_panelActionLoginArbitre.rowWeights = new double[]{0.0};
        panelActionLoginArbitre.setLayout(gbl_panelActionLoginArbitre); 
        
        // Login Label
        CustomJLabel loginArbitreJLabel = new CustomJLabel("Login : ", 12);
        GridBagConstraints gbc_loginArbitreJLabel = new GridBagConstraints();
        gbc_loginArbitreJLabel.insets = new Insets(0, 0, 0, 0);
        gbc_loginArbitreJLabel.fill = GridBagConstraints.BOTH;
        gbc_loginArbitreJLabel.gridx = 0;
        gbc_loginArbitreJLabel.gridy = 0;
        panelActionLoginArbitre.add(loginArbitreJLabel, gbc_loginArbitreJLabel);
        
        // Login text field
        CustomJTextField textFieldLoginArbitre = new CustomJTextField(5, new EmptyBorder(10, 10, 10, 10), "Aucun login");
        GridBagConstraints gbc_loginArbitreTextField = new GridBagConstraints();
        gbc_loginArbitreTextField.insets = new Insets(0, 0, 0, 0);
        gbc_loginArbitreTextField.fill = GridBagConstraints.BOTH;
        gbc_loginArbitreTextField.gridx = 1;
        gbc_loginArbitreTextField.gridy = 0;
        panelActionLoginArbitre.add(textFieldLoginArbitre, gbc_loginArbitreTextField);
        panelArbitreActions.add(panelActionLoginArbitre);
        
        // Panel for MDP (Label + TextField)
        CustomJPanel panelActionMDPArbitre = new CustomJPanel(new EmptyBorder(10, 10, 10, 10));
        GridBagLayout gbl_panelActionMDPArbitre = new GridBagLayout();
        gbl_panelActionMDPArbitre.rowHeights = new int[] {0};
        gbl_panelActionMDPArbitre.columnWidths = new int[] {0, 0};
        gbl_panelActionMDPArbitre.columnWeights = new double[]{0.05, 0.95};
        gbl_panelActionMDPArbitre.rowWeights = new double[]{0.0};
        panelActionMDPArbitre.setLayout(gbl_panelActionMDPArbitre); 
        
        // Login Label
        CustomJLabel mdpArbitreJLabel = new CustomJLabel("MDP : ", 12);
        GridBagConstraints gbc_mdpArbitreJLabel = new GridBagConstraints();
        gbc_mdpArbitreJLabel.insets = new Insets(0, 0, 0, 0);
        gbc_mdpArbitreJLabel.fill = GridBagConstraints.BOTH;
        gbc_mdpArbitreJLabel.gridx = 0;
        gbc_mdpArbitreJLabel.gridy = 0;
        panelActionMDPArbitre.add(mdpArbitreJLabel, gbc_mdpArbitreJLabel);
        
        // Login text field
        CustomJTextField textFieldMDPArbitre = new CustomJTextField(5, new EmptyBorder(10, 10, 10, 10), "Aucun MDP");
        GridBagConstraints gbc_mdpArbitreTextField = new GridBagConstraints();
        gbc_mdpArbitreTextField.insets = new Insets(0, 0, 0, 0);
        gbc_mdpArbitreTextField.fill = GridBagConstraints.BOTH;
        gbc_mdpArbitreTextField.gridx = 1;
        gbc_mdpArbitreTextField.gridy = 0;
        panelActionMDPArbitre.add(textFieldMDPArbitre, gbc_mdpArbitreTextField);
        panelArbitreActions.add(panelActionMDPArbitre);
        
        // Panel bottom
        CustomJPanel panelBottom = new CustomJPanel(new EmptyBorder(10, 10, 0, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        //Bouton quitter
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
        
        // Bouton de validation
        CustomJButton btnValid = new CustomJButton("Valider le login", 10);
        btnValid.setBackground(new Color(76, 231, 60));
        btnValid.setForeground(new Color(255, 255, 255));
        btnValid.addActionListener(this.controleur);
        panelBottom.add(btnValid);
    }
    
    public void SetArbitreList() {
    	
    }
}
