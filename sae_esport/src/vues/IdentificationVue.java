package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IdentificationVue extends JFrame {

    private JPanel contentPane;
    public JTextField textField_1;
    public JTextField textField;
    public JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IdentificationVue frame = new IdentificationVue();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public IdentificationVue() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JLabel titreFenetre = new JLabel("CONNEXION");
        titreFenetre.setBorder(new EmptyBorder(20, 20, 20, 20));
        titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
        titreFenetre.setFont(new Font("Tahoma", Font.PLAIN, 25));
        contentPane.add(titreFenetre, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 0, 0, 0));
        
        JPanel panel_3 = new JPanel();
        panel.add(panel_3);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel = new JLabel("Nom d'utilisateur");
        lblNewLabel.setPreferredSize(new Dimension(110, 30));
        panel_3.add(lblNewLabel);
        
        textField_2 = new JTextField();
        panel_3.add(textField_2);
        textField_2.setColumns(10);
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        
        JLabel lblNewLabel_1 = new JLabel("Mot de passe     ");
        lblNewLabel_1.setPreferredSize(new Dimension(110, 30));
        panel_2.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        panel_2.add(textField_1);
        textField_1.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnNewButton = new JButton("Quitter");
        panel_1.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Se connecter");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        panel_1.add(btnNewButton_1);
    }
}
