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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAOs.TournoiDAO;
import classes.Equipe;
import classes.Match;
import controleurs.SaisieResultatControleur;
import modeles.TournoiModele;
import style.CustomJButton;
import style.CustomJFrame;
import style.CustomJLabel;
import style.CustomJPanel;
import style.CustomJScrollPane;
import style.CustomJSeparator;
import style.Palette;

public class SaisieResultatVue extends CustomJFrame {
	private CustomJPanel contentPanel;
    private SaisieResultatControleur controleur;
    private TournoiModele tournoi;
    
    private ArrayList<ArrayList<CustomJButton>> matchsUi;
    private ArrayList<CustomJButton> buttonEquipeFinale;
    private CustomJButton openFinalButton;
    private CustomJButton closeFinalButton;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SaisieResultatVue frame = new SaisieResultatVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public SaisieResultatVue() throws Exception {
    	super(new Dimension(550, 500), "Résultat des matchs");
    	contentPanel = this.getContentPanel();
    	
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
    	pack();
    	
    	this.tournoi = TournoiDAO.getInstance().getTournoiOuvert().get();
    	this.controleur = new SaisieResultatControleur(this, this.tournoi);
        
        // Panel Top : Title
    	CustomJPanel panelTop = new CustomJPanel();
        contentPanel.add(panelTop, BorderLayout.NORTH);
        
        CustomJLabel titleTop = new CustomJLabel("RÉSULTAT DES MATCHS", 25);
        titleTop.setFont(Palette.customFont.deriveFont(Font.BOLD, 20));
        panelTop.add(titleTop);
        
        CustomJSeparator separatorTop = new CustomJSeparator();
        panelTop.add(separatorTop, BorderLayout.SOUTH);
        
        // Panel middle : Action et Listes
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.rowHeights = new int[] {50, 0, 50};
        gbl_panelMiddle.columnWidths = new int[] {0};
        gbl_panelMiddle.columnWeights = new double[]{1.0};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 1.0, 0.0};
        JPanel panelMiddle = new CustomJPanel(new EmptyBorder(0, 10, 0, 10));
        panelMiddle.setLayout(gbl_panelMiddle);
        contentPanel.add(panelMiddle);
        
        // Panel Middle Finale
        CustomJPanel panelMiddleFinal = new CustomJPanel(new EmptyBorder(10, 0, 10, 0), new GridLayout(0, 1, 10, 10));
        GridBagConstraints gbc_panelMiddleFinal = new GridBagConstraints();
        gbc_panelMiddleFinal.anchor = GridBagConstraints.NORTH;
        gbc_panelMiddleFinal.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelMiddleFinal.gridx = 0;
        gbc_panelMiddleFinal.gridy = 0;
        panelMiddle.add(panelMiddleFinal, gbc_panelMiddleFinal);
        
        CustomJLabel middleLabeFinale = new CustomJLabel("Finale :", 25);
        middleLabeFinale.setHorizontalAlignment(SwingConstants.LEFT);
        panelMiddleFinal.add(middleLabeFinale);
        
        this.buttonEquipeFinale = new ArrayList<CustomJButton>();
        
        CustomJPanel panelMiddleFinalEquipe = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(1, 2, 10, 10));
        panelMiddleFinal.add(panelMiddleFinalEquipe);
        CustomJButton button1Finale = new CustomJButton("Équipe n°x", 15);
        button1Finale.setEnabled(false);
        button1Finale.setActionCommand("IdMatch,IdEquipe");
        button1Finale.addActionListener(this.controleur);
        panelMiddleFinalEquipe.add(button1Finale);
        this.buttonEquipeFinale.add(button1Finale);
    	
       	CustomJButton button2Finale = new CustomJButton("Équipe n°x", 15);
       	button2Finale.setEnabled(false);
       	button2Finale.setActionCommand("IdMatch,IdEquipe");
       	button2Finale.addActionListener(this.controleur);
       	panelMiddleFinalEquipe.add(button2Finale);
       	this.buttonEquipeFinale.add(button2Finale);
        
       	// Panel Middle Match
       	CustomJPanel panelMiddleMatch = new CustomJPanel(new EmptyBorder(10, 0, 10, 0));
        GridBagLayout gbl_panelMiddleMatch = new GridBagLayout();
        gbl_panelMiddleMatch.rowHeights = new int[] {30, 0};
        gbl_panelMiddleMatch.columnWidths = new int[] {0};
        gbl_panelMiddleMatch.columnWeights = new double[]{1.0};
        gbl_panelMiddleMatch.rowWeights = new double[]{0.0, 1.0};
        panelMiddleMatch.setLayout(gbl_panelMiddleMatch);
        GridBagConstraints gbc_panelMiddleHeaderCustomJPanel = new GridBagConstraints();
        gbc_panelMiddleHeaderCustomJPanel.weighty = 1.0;
        gbc_panelMiddleHeaderCustomJPanel.fill = GridBagConstraints.BOTH;
        gbc_panelMiddleHeaderCustomJPanel.gridx = 0;
        gbc_panelMiddleHeaderCustomJPanel.gridy = 1;
        panelMiddle.add(panelMiddleMatch, gbc_panelMiddleHeaderCustomJPanel);
        
        CustomJLabel middleLabelMatch = new CustomJLabel("Matchs :", 25);
        GridBagConstraints gbc_middleLabelMatch = new GridBagConstraints();
        gbc_middleLabelMatch.anchor = GridBagConstraints.NORTHWEST;
        gbc_middleLabelMatch.gridx = 0;
        gbc_middleLabelMatch.gridy = 0;
        panelMiddleMatch.add(middleLabelMatch, gbc_middleLabelMatch);
        
        CustomJPanel panelRoundList = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(1, 0, 10, 10)); 
        CustomJScrollPane scrollpanelRoundList = new CustomJScrollPane(panelRoundList);
        scrollpanelRoundList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpanelRoundList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_panelRoundList = new GridBagConstraints();
        gbc_panelRoundList.fill = GridBagConstraints.BOTH;
        gbc_panelRoundList.gridx = 0;
        gbc_panelRoundList.gridy = 1;
        panelMiddleMatch.add(scrollpanelRoundList, gbc_panelRoundList);
        
        GridBagLayout gbl_matchCustomJPanel = new GridBagLayout();
        gbl_matchCustomJPanel.rowHeights = new int[] {30, 0};
        gbl_matchCustomJPanel.columnWidths = new int[] {0};
        gbl_matchCustomJPanel.columnWeights = new double[]{1.0};
        gbl_matchCustomJPanel.rowWeights = new double[]{0.0, 0.0};
        
        GridBagConstraints gbc_titleMatchTop = new GridBagConstraints();
        gbc_titleMatchTop.fill = GridBagConstraints.BOTH;
        gbc_titleMatchTop.gridx = 0;
        gbc_titleMatchTop.gridy = 0;
        
        GridBagConstraints gbc_roundListEquipe = new GridBagConstraints();
        gbc_roundListEquipe.fill = GridBagConstraints.BOTH;
        gbc_roundListEquipe.gridx = 0;
        gbc_roundListEquipe.gridy = 1;
        
        this.matchsUi = new ArrayList<>();
        boolean inFinale = false;
        boolean canFinale = true;
        Match finalMatch = null;
        Equipe finalEquipe1 = null;
        Equipe finalEquipe2 = null;
        int nbMatch = 0;
        
        for (Match match : this.tournoi.getMatchs()) {
        	
        	nbMatch++;
        	
        	if (!match.IsItFinale()) {
        		
            	ArrayList<CustomJButton> equipesList = new ArrayList<CustomJButton>();

            	// Panel Match : Titre + Panel Liste Equipe
            	CustomJPanel MatchCustomJPanel = new CustomJPanel(new EmptyBorder(0, 0, 0, 0), new GridLayout(2, 1, 0, 0));
            	MatchCustomJPanel.setLayout(gbl_matchCustomJPanel);
            	panelRoundList.add(MatchCustomJPanel);
            	
            	// Numero Round
                CustomJLabel titleMatchTop = new CustomJLabel("Match n°"+nbMatch, 25);
                MatchCustomJPanel.add(titleMatchTop, gbc_titleMatchTop);
                
                // Panel Equipe Liste
                CustomJPanel MatchEquipeListJPanel = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new GridLayout(0, 2, 5, 5));
                MatchCustomJPanel.add(MatchEquipeListJPanel, gbc_roundListEquipe);
                
                for (Equipe equipe : match.getEquipes()) {
                	
                	CustomJButton button1 = new CustomJButton(equipe.getNom(), 15);
                	equipesList.add(button1);
                	button1.addActionListener(this.controleur);
                	button1.setActionCommand(match.getIDMatch() + "," + equipe.getIdEquipe());
                	
                	if ((Integer) match.getVainqueur() != 0) {
                    	if (match.getVainqueur() == equipe.getIdEquipe()) {
        					button1.setBackground(new Color(50, 168, 80));
        				} else {
        					button1.setBackground(new Color(231, 76, 60));
    					}
                	} else {
                		canFinale = false;
                	}
                	
                	MatchEquipeListJPanel.add(button1);
    			}
                
            	this.matchsUi.add(equipesList);
            	
        	} else {
        		
        		inFinale = true;
        		finalMatch = match;
        		
        		for (Equipe equipe : finalMatch.getEquipes()) {
					if (finalEquipe1 == null) {
						System.out.println("Equipe 1 - " + equipe.getIdEquipe());
						finalEquipe1 = equipe;
					} else {
						System.out.println("Equipe 2 - " + equipe.getIdEquipe());
						finalEquipe2 = equipe;
					}
				}
			}
		}
        
        // Panel middle bottom : Action tournoi
        CustomJPanel panelMiddleBottom = new CustomJPanel(new EmptyBorder(10, 10, 10, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc_panelMiddleBottom = new GridBagConstraints();
        gbc_panelMiddleBottom.anchor = GridBagConstraints.SOUTHEAST;
        gbc_panelMiddleBottom.gridx = 0;
        gbc_panelMiddleBottom.gridy = 2;
        panelMiddle.add(panelMiddleBottom, gbc_panelMiddleBottom);
    	
        this.openFinalButton = new CustomJButton("Ouvrir la finale", 15);
        this.openFinalButton.setEnabled(false);
        GridBagConstraints gbc_buttonOpen = new GridBagConstraints();
        gbc_buttonOpen.fill = GridBagConstraints.CENTER;
        gbc_buttonOpen.gridx = 0;
        gbc_buttonOpen.gridy = 0;
        gbc_buttonOpen.anchor = GridBagConstraints.EAST;
        this.openFinalButton.addActionListener(this.controleur);
    	panelMiddleBottom.add(this.openFinalButton, gbc_buttonOpen);
    	
    	this.closeFinalButton = new CustomJButton("Fermer le tournoi", 15);
        this.closeFinalButton.setEnabled(true);
        GridBagConstraints gbc_buttonClose = new GridBagConstraints();
        gbc_buttonClose.anchor = GridBagConstraints.EAST;
        gbc_buttonClose.gridx = 1;
        gbc_buttonClose.gridy = 0;
        this.closeFinalButton.addActionListener(this.controleur);
    	panelMiddleBottom.add(this.closeFinalButton, gbc_buttonClose);
        
        // Panel bottom
        CustomJPanel panelBottom = new CustomJPanel(new EmptyBorder(10, 10, 0, 10), new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.add(panelBottom, BorderLayout.SOUTH);
        
        // Bouton quitter
        CustomJButton btnQuit = new CustomJButton("Quitter", 10);
        btnQuit.setBackground(new Color(231, 76, 60));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.addActionListener(this.controleur);
        panelBottom.add(btnQuit);
        
        if (canFinale && !inFinale) {
        	this.openFinalButton.setEnabled(true);
		} else if (inFinale) {
        	OpenButtonFinal(finalMatch.getIDMatch(), finalEquipe1, finalEquipe2);
        }
    }
    
    public void RefreshMatch(CustomJButton buttonVainqueur) {
    	
    	boolean canFinale = true;
    	boolean inFinale = false;
		String[] originalIds = buttonVainqueur.getActionCommand().split(",");
		int idmatchVainqueur = Integer.valueOf(originalIds[0]);
		int idequipeVainqueur = Integer.valueOf(originalIds[1]);
    
		for (ArrayList<CustomJButton> match : matchsUi) {
			for (CustomJButton customJButton : match) {
				String[] newIds = customJButton.getActionCommand().split(",");
				int idmatch = Integer.valueOf(newIds[0]);
				int idequipe = Integer.valueOf(newIds[1]);
				
				System.out.println(idmatchVainqueur+""+idmatch);
				if (idmatchVainqueur == idmatch) {
					if (idequipeVainqueur == idequipe) {
						customJButton.setBackground(new Color(50, 168, 80));
					} else {
						customJButton.setBackground(new Color(231, 76, 60));
					}
				}
			}
		}
		
		for (CustomJButton customJButton : buttonEquipeFinale) {
			System.out.println(customJButton.getActionCommand());
			String[] newIds = customJButton.getActionCommand().split(",");
			if (!newIds[0].equals("IdMatch")) {
				inFinale = true;
				int idmatch = Integer.valueOf(newIds[0]);
				int idequipe = Integer.valueOf(newIds[1]);
					
				System.out.println(idmatchVainqueur+""+idmatch);
				if (idmatchVainqueur == idmatch) {
					if (idequipeVainqueur == idequipe) {
						customJButton.setBackground(new Color(50, 168, 80));
					} else {
						customJButton.setBackground(new Color(231, 76, 60));
					}
				}
			}
			
		}
		
		try {
			for (Match match : TournoiDAO.getInstance().getById(this.tournoi.getIDTournoi()).get().getMatchs()) {
				System.out.println(this.tournoi.getIDTournoi() + " - " + match.getIDMatch() + " " + (match.getVainqueur()));
				if ((Integer) match.getVainqueur() == 0) {
					canFinale = false;
					break;
				}
			}
			
			if (canFinale && !inFinale) {
				this.openFinalButton.setEnabled(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void OpenButtonFinal(Integer matchId, Equipe equipe1, Equipe equipe2) {
    	this.openFinalButton.setEnabled(false);
        this.closeFinalButton.setEnabled(true);
        boolean equipeFinale1Set = false;
        
    	for (CustomJButton customJButton : buttonEquipeFinale) {
			customJButton.setEnabled(true);
			
			if (equipeFinale1Set == false) {
				equipeFinale1Set = true;
				customJButton.setText(equipe1.getNom());
				customJButton.setActionCommand(matchId + "," + equipe1.getIdEquipe());
			} else {
				customJButton.setText(equipe2.getNom());
				customJButton.setActionCommand(matchId + "," + equipe2.getIdEquipe());
			}
		}
    	
		for (ArrayList<CustomJButton> match : matchsUi) {
			for (CustomJButton customJButton : match) {
				customJButton.setEnabled(false);
			}
		}
    }

    public TournoiModele getTournoi() {
    	return this.tournoi;
    }
    
    public void closeCurrentWindow() {
    	
		super.closeCurrentWindow();
		AccueilArbitreVue frame = new AccueilArbitreVue();
		frame.setVisible(true);
	}
    
    public void disableButtons() {
    	
    	for (ArrayList<CustomJButton> l : matchsUi) {
    		for (CustomJButton bouton : l) {
    			bouton.setEnabled(false);
    		}
    	}
    	buttonEquipeFinale.get(0).setEnabled(false);
    	buttonEquipeFinale.get(1).setEnabled(false);
    	closeFinalButton.setEnabled(false);
    	openFinalButton.setEnabled(false);
    }
    
}
