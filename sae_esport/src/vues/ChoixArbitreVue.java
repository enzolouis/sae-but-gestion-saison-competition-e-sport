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
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import controleurs.ChoixArbitreControleur;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJScrollPane;
import style.CustomJSeparator;
import style.CustomJTextField;
import style.CustomScrollBarUI;
import style.CustomBorder;

public class ChoixArbitreVue extends CustomJFrame {

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
    	
    	this.controleur = new ChoixArbitreControleur(this);
    	CustomJPanel contentPanel = this.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Choix des arbitres", 25);
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        JPanel panelMiddle = new CustomJPanel(new int[]{10, 0, 0, 0});
        contentPanel.add(panelMiddle);
        
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {266, 133};
        gbl_panelMiddle.columnWidths = new int[] {399};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 0.0};
        panelMiddle.setLayout(gbl_panelMiddle);
        
        // Panel middle --> Panel Liste : Ligne 1 et 2
        CustomJPanel panelArbitreList = new CustomJPanel(new int[]{10, 10, 10, 10});
        GridLayout panelArbitreListLayout = new GridLayout(0, 4, 10, 10);
        panelArbitreList.setLayout(panelArbitreListLayout);  
        
        CustomJScrollPane scrollpanelArbitreList = new CustomJScrollPane(panelArbitreList);
        
        GridBagConstraints gbc_scrollpanelArbitreList = new GridBagConstraints();
        gbc_scrollpanelArbitreList.insets = new Insets(0, 0, 0, 0);
        gbc_scrollpanelArbitreList.fill = GridBagConstraints.BOTH;
        gbc_scrollpanelArbitreList.gridx = 0;
        gbc_scrollpanelArbitreList.gridy = 0;
        panelMiddle.add(scrollpanelArbitreList, gbc_scrollpanelArbitreList);
        
        for (int i = 1; i <= 35; i++) {
        	JButton button = new JButton("Arbitre n°" + i);
        	button.setBackground(new Color(29, 88, 129));
        	button.setForeground(new Color(255, 255, 255));
        	button.setBorder(new CustomBorder(5));
        	button.addActionListener(this.controleur);
        	panelArbitreList.add(button);
		}
        
        // Panel middle --> Panel Action : Ligne 3
        CustomJPanel panelArbitreActions = new CustomJPanel();
        
        GridBagConstraints gbc_panelArbitreActions = new GridBagConstraints();
        gbc_panelArbitreActions.fill = GridBagConstraints.BOTH;
        gbc_panelArbitreActions.gridx = 0;
        gbc_panelArbitreActions.gridy = 1;
        panelMiddle.add(panelArbitreActions, gbc_panelArbitreActions);
        
        GridLayout panelArbitreActionsLayout = new GridLayout(2, 3, 10, 10);
        panelArbitreActions.setLayout(panelArbitreActionsLayout); 
        
        CustomJLabel labelSelectedTournoiJLabel = new CustomJLabel("Tournoi sélectioné : Aucun", 12);
        panelArbitreActions.add(labelSelectedTournoiJLabel);
        
        CustomJLabel labelSelectedArbitreJLabel = new CustomJLabel("Arbitre sélectioné : Aucun", 12);
        panelArbitreActions.add(labelSelectedArbitreJLabel);
        
        // Panel for Login (Label + TextField)
        CustomJPanel panelActionLoginArbitre = new CustomJPanel(new int[]{10, 10, 10, 10});
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
        CustomJTextField textFieldLoginArbitre = new CustomJTextField(5, new int[] {10, 10, 10, 10}, "Aucun login");
        textFieldLoginArbitre.addActionListener(controleur);
        GridBagConstraints gbc_loginArbitreTextField = new GridBagConstraints();
        gbc_loginArbitreTextField.insets = new Insets(0, 0, 0, 0);
        gbc_loginArbitreTextField.fill = GridBagConstraints.BOTH;
        gbc_loginArbitreTextField.gridx = 1;
        gbc_loginArbitreTextField.gridy = 0;
        panelActionLoginArbitre.add(textFieldLoginArbitre, gbc_loginArbitreTextField);
        panelArbitreActions.add(panelActionLoginArbitre);
        
        // Panel for MDP (Label + TextField)
        CustomJPanel panelActionMDPArbitre = new CustomJPanel(new int[] {10, 10, 10, 10});
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
        CustomJTextField textFieldMDPArbitre = new CustomJTextField(5, new int[] {10, 10, 10, 10}, "Aucun MDP");
        textFieldMDPArbitre.addActionListener(controleur);
        GridBagConstraints gbc_mdpArbitreTextField = new GridBagConstraints();
        gbc_mdpArbitreTextField.insets = new Insets(0, 0, 0, 0);
        gbc_mdpArbitreTextField.fill = GridBagConstraints.BOTH;
        gbc_mdpArbitreTextField.gridx = 1;
        gbc_mdpArbitreTextField.gridy = 0;
        panelActionMDPArbitre.add(textFieldMDPArbitre, gbc_mdpArbitreTextField);
        panelArbitreActions.add(panelActionMDPArbitre);
        
        // Panel bottom
        CustomJPanel panelBottom = new CustomJPanel(new int[] {10, 10, 0, 10});
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        //Bouton quitter
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new CustomBorder(5));
        btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
        
        // Bouton de validation
        JButton btnValid = new JButton("Valider le login");
        btnValid.setBackground(new Color(76, 231, 60));
        btnValid.setForeground(new Color(255, 255, 255));
        btnValid.setBorder(new CustomBorder(5));
        btnValid.addActionListener(this.controleur);
        panelBottom.add(btnValid);
    }
    
    public void SetArbitreList() {
    	
    }
}
