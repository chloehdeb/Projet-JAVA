/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Traitement.Chambre;
import Traitement.Docteur;
import Traitement.Hospitalisation;
import Traitement.Infirmier;
import Traitement.Malade;
import Traitement.Secretaire;
import Traitement.Service;
import Traitement.Soigne;
import hopital.Connexion;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class Maj implements ActionListener {
    public Panel panneau;
    private JPanel ligne, ligne2, ligne1;
    private JButton supprimer, modifier,ajouter;
    private JLabel elem;
    private String nom;
    private GridLayout grid = new GridLayout();
    private ArrayList<ArrayList<String>> liste;
    
    public Maj(String nom) {
        
        this.nom = nom;
        //Creation boutons
        ajouter = new JButton ("Ajouter une nouvelle ligne");
        
        panneau = new Panel();

        //////////////////SI ON DOIT SELECTIONNER CE QU'ON VEUT MODIFIER

        //Creation Labels 
        //Creation tableau selon la table à modifier
        switch (nom) {
            case "Soigne":
                tabSoigne();
                break;
            case "Service":
                tabService();
                break;
            case "Docteur":
                tabDocteur();
                break;
            case "Malade":
                tabMalade();
                break;
            case "Infirmier":
                tabInfirmier();
                break;
            case "Secretaire":
                tabSecretaire();
                break;
            case "Hospitalisation":
                tabHospitalisation();
                break;
            case "Chambre":
                System.out.println("Affichage des malades");
                tabChambre();
                break;
        }

        //Creation du GridLayout
        //grid.setColumns(liste.get(0).size() +2);
        //grid.setRows(liste.size() +1);
        //panneau.setLayout(grid);

        System.out.println("nbcol grid= " + liste.get(0).size());
        System.out.println("nblig grid= " + liste.size());       
        //ajout sur le ContentPane
                
        for (int i=0;i<liste.size();i++){
            ligne = new JPanel();
            ligne.setLayout(new FlowLayout(FlowLayout.LEFT));
            ligne.setBorder(new EmptyBorder(0, 20, 0, 0));
            ligne.setPreferredSize(new Dimension(1100, 35));
            
            ligne1 = new JPanel();
            ligne1.setLayout(new FlowLayout(FlowLayout.LEFT));
            ligne1.setBorder(new EmptyBorder(0, 0, 0, 0));
            ligne1.setPreferredSize(new Dimension(690, 30));
            
            ligne2 = new JPanel();
            ligne2.setLayout(new FlowLayout(FlowLayout.LEFT));
            ligne2.setBorder(new EmptyBorder(0, 0, 0, 0));
            ligne2.setPreferredSize(new Dimension(300, 30));
            
            for (int j=0; j<liste.get(0).size();j++){
                elem = new JLabel(liste.get(i).get(j), JLabel.CENTER);
                ligne1.add(elem);
            } 
            supprimer = new JButton ("supprimer " +i);
            modifier = new JButton ("modifier " +i);
            
            //Ajout les boutons pour chaque ligne
            ligne2.add(modifier); 
            ligne2.add(supprimer);  
            
            ligne.add(ligne1);
            ligne.add(ligne2);
            
            // ajout des listeners pour les 2 boutons a chaque ligne             
            supprimer.addActionListener(this);
            modifier.addActionListener(this);
            
            panneau.add(ligne);
        }
        
        panneau.add(ajouter);
        panneau.setPreferredSize(new Dimension(1100, 550));

        //ajout des listeners 
        ajouter.addActionListener(this);

        
    }
    
    
         
    public void actionPerformed(ActionEvent evt) {
        JOptionPane show = new JOptionPane();           
        if (evt.getActionCommand().equals("Ajouter une nouvelle ligne")) {
            switch(nom)
                {                    
                    case "Service":
                        Service service= new Service("","","","",false,true);                       
                        break;
                    case "Docteur":
                        Docteur doc= new Docteur("","","","","","","",false,true);                       
                        break;
                    case "Malade":
                        Malade mal= new Malade("","","","","","",false,true); 
                        break;
                    case "Infirmier":
                        Infirmier inf= new Infirmier("","","","","","","","",false,true); 
                        break;
                    case "Secretaire":
                        Secretaire secret= new Secretaire("","","","","","",false,true); 
                       break;
                    case "Hospitalisation":
                        Hospitalisation hospi = new Hospitalisation("","","","",false,true);
                        break;
                    case "Chambre":
                        Chambre chambre= new Chambre("","","","",false,true); 
                        break;
                    case "Soigne":
                        Soigne soigne= new Soigne("","",false,true);                       
                        break;
                            
                }
        }
        for (int i=0;i<liste.size();i++){
            //System.out.println(evt.getActionCommand());
            if (evt.getActionCommand().equals("supprimer " +i)) {
                System.out.println("Supression demandé");
                switch(nom)
                {
                    
                    case "Service":
                        Service serv= new Service(liste.get(i).get(0),null,null,null,false,false);
                        try {        
                            Connexion.requetesManip(serv.suppService());
                            show.showMessageDialog(null,"Service supprimé !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Docteur":
                        Docteur doc= new Docteur(liste.get(i).get(0),null,null,null,null,null,null,false,false);
                        try {        
                            Connexion.requetesManip(doc.suppDoc());
                            show.showMessageDialog(null,"Docteur supprimé !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Malade":
                        Malade malade= new Malade(liste.get(i).get(0),null,null,null,null,null,false,false);
                        System.out.println("Suppression du Malade...");
                        try {        
                            Connexion.requetesManip(malade.suppMalade());
                            show.showMessageDialog(null,"Patient supprimé !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Infirmier":
                        
                        Infirmier inf = new Infirmier(liste.get(i).get(0), null, null, null, null, null, null, null, false,false);
                        System.out.println("Suppression de l'infirmier...");
                        try {        
                            Connexion.requetesManip(inf.suppInf());
                            show.showMessageDialog(null,"Infirmié supprimé !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Secretaire":
                        Secretaire secretaire = new Secretaire(liste.get(i).get(0), null, null, null, null, null,false,false);
                        try {        
                            Connexion.requetesManip(secretaire.suppSec());
                            show.showMessageDialog(null,"Secretaire supprimé !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Hospitalisation":
                        Hospitalisation hospi = new Hospitalisation(liste.get(i).get(0), liste.get(i).get(1), liste.get(i).get(2), null, false,false);
                        try {        
                            Connexion.requetesManip(hospi.suppHospitalisation());
                            show.showMessageDialog(null,"Hospitalisation supprimée !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Chambre":
                        Chambre room= new Chambre(null,liste.get(i).get(1),null,null,false,false);
                        System.out.println("Suppression de la chambre...");
                        try {        
                            Connexion.requetesManip(room.suppChambre());
                            show.showMessageDialog(null,"Chambre supprimée !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break; 
                    case "Soigne":
                        Soigne soigne= new Soigne(liste.get(i).get(0),liste.get(i).get(3),false,false);
                        System.out.println("Suppression du Soigne...");
                        try {        
                            Connexion.requetesManip(soigne.suppSoigne());
                            show.showMessageDialog(null,"Soigne supprimé !", "Supression ", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Maj.class.getName()).log(Level.SEVERE, null, ex);
                            show.showMessageDialog(null,"Erreur lors de la suppression !", "Supression ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
       
            }
            if (evt.getActionCommand().equals("modifier " +i)) {
                switch(nom)
                {
                    
                    case "Service":
                        Service service= new Service(liste.get(i).get(0),liste.get(i).get(1),liste.get(i).get(2),liste.get(i).get(3),true,false);                       
                        break;
                    case "Docteur":
                        Docteur doc= new Docteur(liste.get(i).get(0),liste.get(i).get(1),liste.get(i).get(2),liste.get(i).get(3),liste.get(i).get(4),liste.get(i).get(5),liste.get(i).get(6),true,false);                       
                        break;
                    case "Malade":
                        Malade mal= new Malade(liste.get(i).get(0),liste.get(i).get(1),liste.get(i).get(2),liste.get(i).get(3),liste.get(i).get(4),liste.get(i).get(5),true,false); 
                        break;
                    case "Infirmier":
                        Infirmier inf= new Infirmier(liste.get(i).get(0),liste.get(i).get(1),liste.get(i).get(2),liste.get(i).get(3),liste.get(i).get(4),liste.get(i).get(5),liste.get(i).get(6),liste.get(i).get(7),true,false); 
                        break;
                    case "Secretaire":
                       Secretaire secret= new Secretaire(liste.get(i).get(0),liste.get(i).get(1),liste.get(i).get(2),liste.get(i).get(3),liste.get(i).get(4),liste.get(i).get(5),true,false); 
                       break;
                    case "Hospitalisation":
                        Hospitalisation hospi = new Hospitalisation(liste.get(i).get(0), liste.get(i).get(1), liste.get(i).get(2),liste.get(i).get(3),true,false);
                        break;
                    case "Chambre":
                        Chambre chambre= new Chambre(liste.get(i).get(0),liste.get(i).get(1),liste.get(i).get(2),liste.get(i).get(3),true,false); 
                        break;
                    case "Soigne":
                        Soigne soigne= new Soigne(liste.get(i).get(0),liste.get(i).get(3),true,false);                       
                        break;
                }
                
            }
        }   
    }
    
    public void tabDocteur(){
        Docteur doc= new Docteur(null,null,null,null,null,null,null,false,false);
        try {
        liste = Connexion.requetes(doc.rechercherDoc());
        } catch (SQLException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    
    public void tabHospitalisation(){
        Hospitalisation hospi = new Hospitalisation(null, null, null, null,false,false);
    
        try {
        liste = Connexion.requetes(hospi.rechercherHospitalisation());
            } catch (SQLException ex) {
                Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
            }
    }
    
    public void tabSecretaire(){
        Secretaire secretaire = new Secretaire(null, null, null, null, null, null,false,false);
    
        try {
        liste = Connexion.requetes(secretaire.rechercherSec());
            } catch (SQLException ex) {
                Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
            }
    }
    
    public void tabMalade(){
        Malade mal= new Malade(null,null,null,null,null,null,false,false);
        try {
        liste = Connexion.requetes(mal.rechercherMalade());
        } catch (SQLException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    public void tabService(){
        Service service= new Service(null,null,null,null,false,false);
        try {
        liste = Connexion.requetes(service.rechercherService());
        } catch (SQLException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    public void tabChambre(){
        Chambre room = new Chambre(null,null,null,null,false,false);    
        try {
        liste = Connexion.requetes(room.rechercherChambre());
            } catch (SQLException ex) {
                Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
            }
    }
    
    public void tabInfirmier(){ 
       Infirmier infirmier = new Infirmier(null, null, null, null, null, null, null, null,false,false); 
        try { 
        liste = Connexion.requetes(infirmier.rechercherInf()); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    }
    
    public void tabSoigne(){
        Soigne soigne = new Soigne(null, null,false,false);
        try { 
        liste = Connexion.requetes(soigne.rechercherSoigne()); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

}