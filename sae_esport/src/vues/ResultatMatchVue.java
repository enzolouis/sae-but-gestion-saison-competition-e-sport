package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAOs.ArbitreDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import modeles.TournoiModele;
import style.CustomJButton;
import style.CustomJComboBox;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJScrollPane;
import style.CustomJSeparator;
import style.CustomJTextField;

public class ResultatMatchVue extends CustomJFrame {
	private CustomJPanel contentPanel;
    //private TournoiListeControleur controleur;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	ResultatMatchVue frame = new ResultatMatchVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ResultatMatchVue() throws Exception {
    	super(new Dimension(950, 600), "Modification de tournois");
    	
    	pack();
    	
    	//this.controleur = new TournoiListeControleur(this);
    	contentPanel = this.getContentPanel();
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("Modification de tournois", 25);
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {50, 200, 50};
        gbl_panelMiddle.columnWidths = new int[] {0};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{1.0, 1.0, 1.0};
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(0, 0, 10, 10));
        panelMiddle.setLayout(gbl_panelMiddle);
        contentPanel.add(panelMiddle);
        
        CustomJPanel panelMiddleHeaderCustomJPanel = new CustomJPanel(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_panelMiddleHeaderCustomJPanel = new GridBagConstraints();
        gbc_panelMiddleHeaderCustomJPanel.anchor = GridBagConstraints.BASELINE_LEADING;
        panelMiddle.add(panelMiddleHeaderCustomJPanel, gbc_panelMiddleHeaderCustomJPanel);
        
        CustomJLabel panelMiddleTitle = new CustomJLabel("Modifications des points", 15);
        panelMiddleHeaderCustomJPanel.add(panelMiddleTitle);
        
        GridBagConstraints gbc_panelPointList = new GridBagConstraints();
        gbc_panelPointList.fill = GridBagConstraints.BOTH;
        gbc_panelPointList.gridx = 0;
        gbc_panelPointList.gridy = 1;
        CustomJPanel panelPointList = new CustomJPanel(new EmptyBorder(5, 5, 5, 5), new GridLayout(0, 3, 5, 5)); 
        CustomJScrollPane scrollpanelPointList = new CustomJScrollPane(panelPointList);
        scrollpanelPointList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpanelPointList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelMiddle.add(scrollpanelPointList, gbc_panelPointList);
        
        CustomJLabel labelPouleOneCustomJLabel = new CustomJLabel("Poule 1", 15);
        panelPointList.add(labelPouleOneCustomJLabel);
        
        CustomJLabel labelPouleTwoCustomJLabel = new CustomJLabel("Poule 2", 15);
        panelPointList.add(labelPouleTwoCustomJLabel);
        
        CustomJLabel labelResultCustomJLabel = new CustomJLabel("Résultat", 15);

        panelPointList.add(labelResultCustomJLabel);
        
        for (int i = 0; i < 5; i++) {
            CustomJLabel labelIdPouleOne = new CustomJLabel(i + "", 15);
            panelPointList.add(labelIdPouleOne);
            
            CustomJLabel labelIdPouleTwo = new CustomJLabel(i + "", 15);
            panelPointList.add(labelIdPouleTwo);
            
            CustomJTextField textFieldPlaceHolderResult = new CustomJTextField(i*i + "", 4);
            panelPointList.add(textFieldPlaceHolderResult);
		}
        
        // Panel middle bottom : Action tournoi
        GridBagConstraints gbc_panelMiddleBottom = new GridBagConstraints();
        gbc_panelMiddleBottom.anchor = GridBagConstraints.SOUTHWEST;
        gbc_panelMiddleBottom.gridx = 0;
        gbc_panelMiddleBottom.gridy = 2;
        GridBagLayout gbl_panelMiddleBottom = new GridBagLayout();
        gbl_panelMiddleBottom.rowHeights = new int[] {0};
        gbl_panelMiddleBottom.columnWidths = new int[] {750, 100};
        gbl_panelMiddleBottom.columnWeights = new double[]{0.0};
        gbl_panelMiddleBottom.rowWeights = new double[]{0.0};
        CustomJPanel panelMiddleBottom = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMiddleBottom.setLayout(gbl_panelMiddleBottom);
        panelMiddle.add(panelMiddleBottom, gbc_panelMiddleBottom);
    	
    	CustomJButton buttonOpen = new CustomJButton("Annuler", 15);
        GridBagConstraints gbc_buttonOpen = new GridBagConstraints();
        gbc_buttonOpen.fill = GridBagConstraints.CENTER;
        gbc_buttonOpen.gridx = 0;
        gbc_buttonOpen.gridy = 0;
        gbc_buttonOpen.anchor = GridBagConstraints.EAST;
    	//button.addActionListener(this.controleur);
    	panelMiddleBottom.add(buttonOpen, gbc_buttonOpen);
    	
    	CustomJButton buttonClose = new CustomJButton("Valider", 15);
        GridBagConstraints gbc_buttonClose = new GridBagConstraints();
        gbc_buttonClose.anchor = GridBagConstraints.EAST;
        gbc_buttonClose.gridx = 1;
        gbc_buttonClose.gridy = 0;
    	//button.addActionListener(this.controleur);
    	panelMiddleBottom.add(buttonClose, gbc_buttonClose);
        
        // Panel bottom
        CustomJPanel panelBottom = new CustomJPanel(new EmptyBorder(10, 10, 0, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        //Bouton quitter
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        //btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
    }
}
