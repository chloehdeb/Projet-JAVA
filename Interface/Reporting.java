/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import static Interface.Accueil.fenetre;
import Interface.Recherche;
import hopital.Connexion;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Admin
 */
public class Reporting implements ActionListener  {
        
    public Panel panneau, panneau2;
    private JButton but1, but2, but3, but4, retour;
    
    public Reporting (){

        //Creation boutons
        but1= new JButton ("Patientelle par médecin");
        but2= new JButton ("Nombre de lits parservice");
        but3= new JButton ("Salaire par Spécialité");
        but4= new JButton ("Nombre de patients par spécialité");
        retour = new JButton ("retour");
        
        panneau = new Panel();
        panneau2 = new Panel();
        
        //ajout sur le panel
        panneau.add(but1);
        panneau.add(but2);
        panneau.add(but3);
        panneau.add(but4);
        panneau.add(panneau2);
        panneau.add(retour);
        
        retour.setVisible(false);
        
        // ajout des listeners
        but1.addActionListener(this);
        but2.addActionListener(this);
        but3.addActionListener(this); 
        but4.addActionListener(this);
        retour.addActionListener(this);
        
        panneau.setPreferredSize(new Dimension(1100, 650));
        panneau2.setPreferredSize(new Dimension(1100, 450));
        fenetre.setVisible(true);
                  
    }

   
    
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Patientelle par médecin")) {
        ResultSet resultat1= null;

        final DefaultPieDataset camembert = new DefaultPieDataset();
        try {
            //Récupération du résultat de la requête mysql
            resultat1 = Connexion.stmt.executeQuery("SELECT employe.nom, COUNT(*) FROM soigne, docteur, employe WHERE docteur.numero=no_docteur AND docteur.numero=employe.numero GROUP BY `no_docteur`");
            while (resultat1.next()) {
                //On récupère les valeurs de la requête qui nous intéresse dans notre camembert
                camembert.setValue(resultat1.getString("nom"), resultat1.getInt("COUNT(*)"));
            }
            //Creation et affichage du graph
            JFreeChart chart = ChartFactory.createPieChart3D("RATIO DE PATIENTELE DES MEDECINS", camembert, true, true, true);
            PiePlot3D P = (PiePlot3D) chart.getPlot();
            
            ChartPanel test = new ChartPanel(chart);
            panneau2.removeAll();
            panneau2.add(test);
            fenetre.setVisible(true);
            panneau2.setVisible(true);
            retour.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
    }                         
        }
        else if (evt.getActionCommand().equals("Nombre de lits parservice")) {
                   ResultSet resultat3= null;

        final DefaultPieDataset camembert2 = new DefaultPieDataset();
        try {
            //Récupération du résultat de la requête mysql
            resultat3 = Connexion.stmt.executeQuery("SELECT service.nom, COUNT(hospitalisation.lit) FROM service, hospitalisation WHERE hospitalisation.code_service=service.code GROUP BY `code_service`");
            while (resultat3.next()) {
                //On récupère les valeurs de la requête qui nous intéresse dans notre camembert
                camembert2.setValue(resultat3.getString("service.nom"), resultat3.getInt("COUNT(hospitalisation.lit)"));
            }
            //Creation et affichage du graph
            JFreeChart chart = ChartFactory.createPieChart3D("NOMBRE DE LITS PAR SERVICE", camembert2, true, true, true);
            PiePlot3D P = (PiePlot3D) chart.getPlot();
            
            ChartPanel test = new ChartPanel(chart);
            panneau2.removeAll();
            panneau2.add(test);
            fenetre.setVisible(true);
            panneau2.setVisible(true);
            retour.setVisible(true);

        } catch (SQLException ex3) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex3);       
    }         
        }
        else if (evt.getActionCommand().equals("Salaire par Spécialité")) {
               ResultSet resultat2 = null;
        

        final DefaultCategoryDataset setvariable = new DefaultCategoryDataset();
        try {
            //Récupération du résultat de la requête mysql
            resultat2 = Connexion.stmt.executeQuery("SELECT `specialite`, AVG(`salaire`) FROM `docteur` GROUP BY `specialite`");
            while (resultat2.next()) {
                //On récupère les valeurs de la requête qui nous intéresse dans notre histogramme
                setvariable.addValue((Number)resultat2.getInt("AVG(`salaire`)"), resultat2.getString("specialite"), 1);
                
            }
            //Creation et affichage du graph
            JFreeChart histo = ChartFactory.createBarChart("SALAIRE MOYEN PAR SPECIALITE", "Specialité", "Salaire", setvariable);
            
            ChartPanel test = new ChartPanel(histo);
            panneau2.removeAll();
            panneau2.add(test);
            fenetre.setVisible(true);
            panneau2.setVisible(true);
            retour.setVisible(true);
            
         } catch(SQLException ex2) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex2);
        }      
        }
        else if (evt.getActionCommand().equals("Nombre de patients par spécialité")) {
            ResultSet resultat4 = null;
        

        final DefaultCategoryDataset setvariable = new DefaultCategoryDataset();
        try {
            //Récupération du résultat de la requête mysql
            resultat4 = Connexion.stmt.executeQuery("SELECT docteur.specialite, COUNT(soigne.no_malade)FROM docteur, soigne WHERE no_docteur=docteur.numero GROUP BY docteur.specialite");
            while (resultat4.next()) {
                //On récupère les valeurs de la requête qui nous intéresse dans notre histogramme
                setvariable.addValue((Number)resultat4.getInt("COUNT(soigne.no_malade)"), resultat4.getString("docteur.specialite"), 1);
                
            }
            //Creation et affichage du graph
            JFreeChart histo = ChartFactory.createBarChart("NOMBRE DE PATIENTS PAR SPECIALITE", "Specialité", "Nombre de patients", setvariable);
            
            ChartPanel test = new ChartPanel(histo);
            panneau2.removeAll();
            panneau2.add(test);
            fenetre.setVisible(true);
            panneau2.setVisible(true);
            retour.setVisible(true);
            
         } catch(SQLException ex2) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex2);
        }
        }
        else if (evt.getActionCommand().equals("retour")) {
            panneau2.removeAll();
            retour.setVisible(false);
            panneau2.setVisible(false);
            fenetre.setVisible(true);
        }
        
        
    }

 
}
