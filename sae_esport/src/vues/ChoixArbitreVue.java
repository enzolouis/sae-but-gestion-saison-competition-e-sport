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
import style.CustomJTextField;
import style.CustomScrollBarUI;
import style.RoundBtn;

public class ChoixArbitreVue extends JFrame {

    private JPanel contentPanel;
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
    	
    	// JFrame
    	contentPanel = new JPanel();
    	contentPanel.setBackground(new Color(44, 47, 51));
    	contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
    	contentPanel.setLayout(new BorderLayout(0, 0));
    	setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setContentPane(contentPanel);
        
        // Panel Top : Title
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(44, 47, 51));
        panelTop.setLayout(new BorderLayout(0, 0));
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        JLabel titleTop = new JLabel("Choix des arbitres");
        titleTop.setHorizontalAlignment(SwingConstants.CENTER);
        titleTop.setForeground(new Color(102, 173, 221));
        titleTop.setFont(new Font("Tahoma", Font.BOLD, 25));
        titleTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTop.add(titleTop);
        
        JSeparator separatorTop = new JSeparator();
        separatorTop.setBackground(new Color(102, 173, 221));
        separatorTop.setForeground(new Color(102, 173, 221));
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        
        JPanel panelMiddle = new JPanel();
        panelMiddle.setBorder(new EmptyBorder(10, 0, 0, 0));
        panelMiddle.setBackground(new Color(44, 47, 51));
        contentPanel.add(panelMiddle);
        
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {266, 133};
        gbl_panelMiddle.columnWidths = new int[] {399};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 0.0};
        panelMiddle.setLayout(gbl_panelMiddle);
        
        // Panel middle --> Panel Liste : Ligne 1 et 2
        JPanel panelArbitreList = new JPanel();
        panelArbitreList.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelArbitreList.setBackground(new Color(44, 47, 51));
        
        GridLayout panelArbitreListLayout = new GridLayout(0, 4, 10, 10);
        panelArbitreList.setLayout(panelArbitreListLayout);  
        
        JScrollPane scrollpanelArbitreList = new JScrollPane(panelArbitreList);
        scrollpanelArbitreList.setBorder(BorderFactory.createEmptyBorder());
        scrollpanelArbitreList.setViewportBorder(BorderFactory.createEmptyBorder());
        scrollpanelArbitreList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpanelArbitreList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        BasicScrollBarUI scrollBarArbitreListBarUI = new CustomScrollBarUI();
        scrollpanelArbitreList.getVerticalScrollBar().setUI(scrollBarArbitreListBarUI);
        
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
        	button.setBorder(new RoundBtn(5));
        	button.addActionListener(this.controleur);
        	panelArbitreList.add(button);
		}
        
        // Panel middle --> Panel Action : Ligne 3
        JPanel panelArbitreActions = new JPanel();
        panelArbitreActions.setBackground(new Color(44, 47, 51));
        GridBagConstraints gbc_panelArbitreActions = new GridBagConstraints();
        gbc_panelArbitreActions.fill = GridBagConstraints.BOTH;
        gbc_panelArbitreActions.gridx = 0;
        gbc_panelArbitreActions.gridy = 1;
        panelMiddle.add(panelArbitreActions, gbc_panelArbitreActions);
        
        GridLayout panelArbitreActionsLayout = new GridLayout(2, 3, 10, 10);
        panelArbitreActions.setLayout(panelArbitreActionsLayout); 
        
        JLabel labelSelectedTournoiJLabel = new JLabel("Tournoi sélectioné : Aucun");
        labelSelectedTournoiJLabel.setForeground(new Color(102, 173, 221));
        panelArbitreActions.add(labelSelectedTournoiJLabel);
        
        JLabel labelSelectedArbitreJLabel = new JLabel("Arbitre sélectioné : Aucun");
        labelSelectedArbitreJLabel.setForeground(new Color(102, 173, 221));
        panelArbitreActions.add(labelSelectedArbitreJLabel);
        
        // Panel for Login (Label + TextField)
        JPanel panelActionLoginArbitre = new JPanel();
        panelActionLoginArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelActionLoginArbitre.setBackground(new Color(44, 47, 51));
        GridBagLayout gbl_panelActionLoginArbitre = new GridBagLayout();
        gbl_panelActionLoginArbitre.rowHeights = new int[] {0};
        gbl_panelActionLoginArbitre.columnWidths = new int[] {0, 0};
        gbl_panelActionLoginArbitre.columnWeights = new double[]{0.05, 0.95};
        gbl_panelActionLoginArbitre.rowWeights = new double[]{0.0};
        panelActionLoginArbitre.setLayout(gbl_panelActionLoginArbitre); 
        
        // Login Label
        JLabel loginArbitreJLabel = new JLabel("Login : ");
        loginArbitreJLabel.setForeground(new Color(102, 173, 221));
        GridBagConstraints gbc_loginArbitreJLabel = new GridBagConstraints();
        gbc_loginArbitreJLabel.insets = new Insets(0, 0, 0, 0);
        gbc_loginArbitreJLabel.fill = GridBagConstraints.BOTH;
        gbc_loginArbitreJLabel.gridx = 0;
        gbc_loginArbitreJLabel.gridy = 0;
        panelActionLoginArbitre.add(loginArbitreJLabel, gbc_loginArbitreJLabel);
        
        // Login text field
        CustomJTextField textFieldLoginArbitre = new CustomJTextField(5);
        textFieldLoginArbitre.addActionListener(controleur);
        textFieldLoginArbitre.setPlaceholder("Aucun login");
        textFieldLoginArbitre.setForeground(Color.WHITE);
        textFieldLoginArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        textFieldLoginArbitre.setBackground(new Color(29, 88, 129));
        GridBagConstraints gbc_loginArbitreTextField = new GridBagConstraints();
        gbc_loginArbitreTextField.insets = new Insets(0, 0, 0, 0);
        gbc_loginArbitreTextField.fill = GridBagConstraints.BOTH;
        gbc_loginArbitreTextField.gridx = 1;
        gbc_loginArbitreTextField.gridy = 0;
        panelActionLoginArbitre.add(textFieldLoginArbitre, gbc_loginArbitreTextField);
        
        panelArbitreActions.add(panelActionLoginArbitre);
        
        // Panel for MDP (Label + TextField)
        JPanel panelActionMDPArbitre = new JPanel();
        panelActionMDPArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelActionMDPArbitre.setBackground(new Color(44, 47, 51));
        GridBagLayout gbl_panelActionMDPArbitre = new GridBagLayout();
        gbl_panelActionMDPArbitre.rowHeights = new int[] {0};
        gbl_panelActionMDPArbitre.columnWidths = new int[] {0, 0};
        gbl_panelActionMDPArbitre.columnWeights = new double[]{0.05, 0.95};
        gbl_panelActionMDPArbitre.rowWeights = new double[]{0.0};
        panelActionMDPArbitre.setLayout(gbl_panelActionMDPArbitre); 
        
        // Login Label
        JLabel mdpArbitreJLabel = new JLabel("MDP : ");
        mdpArbitreJLabel.setForeground(new Color(102, 173, 221));
        GridBagConstraints gbc_mdpArbitreJLabel = new GridBagConstraints();
        gbc_mdpArbitreJLabel.insets = new Insets(0, 0, 0, 0);
        gbc_mdpArbitreJLabel.fill = GridBagConstraints.BOTH;
        gbc_mdpArbitreJLabel.gridx = 0;
        gbc_mdpArbitreJLabel.gridy = 0;
        panelActionMDPArbitre.add(mdpArbitreJLabel, gbc_mdpArbitreJLabel);
        
        // Login text field
        CustomJTextField textFieldMDPArbitre = new CustomJTextField(5);
        textFieldMDPArbitre.addActionListener(controleur);
        textFieldMDPArbitre.setPlaceholder("Aucun MDP");
        textFieldMDPArbitre.setForeground(Color.WHITE);
        textFieldMDPArbitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        textFieldMDPArbitre.setBackground(new Color(29, 88, 129));
        GridBagConstraints gbc_mdpArbitreTextField = new GridBagConstraints();
        gbc_mdpArbitreTextField.insets = new Insets(0, 0, 0, 0);
        gbc_mdpArbitreTextField.fill = GridBagConstraints.BOTH;
        gbc_mdpArbitreTextField.gridx = 1;
        gbc_mdpArbitreTextField.gridy = 0;
        panelActionMDPArbitre.add(textFieldMDPArbitre, gbc_mdpArbitreTextField);
        
        panelArbitreActions.add(panelActionMDPArbitre);
        
        // Panel bottom : Quit
        JPanel panelBottom = new JPanel();
        panelBottom.setBackground(new Color(44, 47, 51));
        panelBottom.setBorder(new EmptyBorder(10, 10, 0, 10));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setBorder(new RoundBtn(5));
        btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
        
        JButton btnValid = new JButton("Valider le login");
        btnValid.setBackground(new Color(76, 231, 60));
        btnValid.setForeground(new Color(255, 255, 255));
        btnValid.setBorder(new RoundBtn(5));
        btnValid.addActionListener(this.controleur);
        panelBottom.add(btnValid);
    }
    
    public void SetArbitreList() {
    	
    }
}
