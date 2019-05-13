/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

/*
 * 
 * Librairies importées
 */
import hopital.Connexion;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



/**
 *
 * @author Admin
 */
public class Accueil implements ActionListener 
{
        
        public Connexion maconnexion;//pour la connexion a la base
        private Panel panneau;
        private JPanel ligne1, ligne2, ligne3, ligne4, ligne5, ligne6, ligne7;
        private JButton valider;
        private JLabel nomECE, passECE, login, password, titre;
        private JPasswordField passECEText, passwordText;
        private JTextField nomECEText,loginText;
        private JCheckBox test;
        static JFrame fenetre;
        
        public Accueil ()
        {
            
            fenetre = new JFrame();

            fenetre.setTitle("Centre Hopital");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setPreferredSize(new Dimension(1000, 500));
            fenetre.pack();
            fenetre.setLocationRelativeTo(null); // la fenêtre est centrée à l'écran
            
            //Creation labels
            titre = new JLabel("BIENVENUE", JLabel.CENTER);
            nomECE= new JLabel("Identifiant ECE :", JLabel.LEFT);
            passECE = new JLabel("Mot de passe ECE :", JLabel.LEFT);
            login = new JLabel("Identifiant de la BDD :", JLabel.LEFT);
            password = new JLabel("Mot de passe dela BDD :", JLabel.LEFT);
            //Creation TextField
            nomECEText= new JTextField(20);
            passECEText= new JPasswordField(20);
            loginText= new JTextField(20);
            passwordText= new JPasswordField(20);
            //Creation bouton
            valider = new JButton ("Soumettre");
            test = new JCheckBox("Offline");
            
            //Creation panels
            panneau = new Panel();
            ligne1 = new JPanel();
            ligne2 = new JPanel();
            ligne3 = new JPanel();
            ligne4 = new JPanel();
            ligne5 = new JPanel();
            ligne6 = new JPanel();
            ligne7 = new JPanel();

            //ajout sur le panel
            ligne1.add(titre);
            ligne2.add(nomECE);
            ligne2.add(nomECEText);
            ligne3.add(passECE);
            ligne3.add(passECEText);
            ligne4.add(login);
            ligne4.add(loginText);
            ligne5.add(password);
            ligne5.add(passwordText);
            ligne6.add(test);
            ligne7.add(valider);
            
            nomECE.setPreferredSize(new Dimension(140, 40));
            passECE.setPreferredSize(new Dimension(140, 40));
            login.setPreferredSize(new Dimension(140, 40));
            password.setPreferredSize(new Dimension(140, 40));
            
            ligne2.setLayout(new FlowLayout(FlowLayout.LEFT));
            ligne3.setLayout(new FlowLayout(FlowLayout.LEFT));
            ligne4.setLayout(new FlowLayout(FlowLayout.LEFT));
            ligne5.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            ligne1.setBorder(new EmptyBorder(20, 0, 20, 0));
            ligne2.setBorder(new EmptyBorder(0, 400, 0, 0));
            ligne3.setBorder(new EmptyBorder(0, 400, 0, 0));
            ligne4.setBorder(new EmptyBorder(0, 400, 0, 0));
            ligne5.setBorder(new EmptyBorder(0, 400, 0, 0));
            ligne6.setBorder(new EmptyBorder(10, 0, 0, 0));
            
            ligne1.setPreferredSize(new Dimension(1200, 60));
            ligne2.setPreferredSize(new Dimension(1200, 40));
            ligne3.setPreferredSize(new Dimension(1200, 40));
            ligne4.setPreferredSize(new Dimension(1200, 40));
            ligne5.setPreferredSize(new Dimension(1200, 40));
            ligne6.setPreferredSize(new Dimension(1200, 50));
            ligne7.setPreferredSize(new Dimension(1200, 40));
            
            panneau.add(ligne1);
            panneau.add(ligne2);
            panneau.add(ligne3);
            panneau.add(ligne4);
            panneau.add(ligne5);
            panneau.add(ligne6);
            panneau.add(ligne7);
            
            // ajout des listeners
            nomECEText.addActionListener(this);
            passECEText.addActionListener(this);
            loginText.addActionListener(this);
            passwordText.addActionListener(this);
            valider.addActionListener(this);
            test.addActionListener(this);

            //ajout panel sur la frame
            fenetre.add(panneau);
            panneau.setPreferredSize(new Dimension(1200, 700));
            fenetre.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent evt) 
        {
            if (evt.getActionCommand().equals("Valider")) 
            {
                //On se connecte avec les infos dans les variables
                
                String passwdECEString = new String(passECEText.getPassword());
                String passwdBDDString = new String(passwordText.getPassword());

                try 
                {
                    try 
                    {
                        // tentative de connexion si les 4 attributs sont remplis
                        fenetre.dispose(); //fermer la fenetre de connexion 
                        //maconnexion = new Connexion(nomECEText.getText(), passwdECEString,
                        //loginText.getText(), passwdBDDString, !test.isSelected());
                        maconnexion = new Connexion("ecourtem", "TMP-GffRH3", "ecourtem-rw", "6qX55kcg", !test.isSelected());
                        
                    } 
                    
                    catch (ClassNotFoundException cnfe) 
                    {
                        System.out.println("Connexion echouee : probleme de classe");
                        cnfe.printStackTrace();
                    }   
                } 
                
                catch (SQLException e) 
                {
                    System.out.println("Connexion echouee : probleme SQL");
                    e.printStackTrace();
                }
            } 
        }        
}
