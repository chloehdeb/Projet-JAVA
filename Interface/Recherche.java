/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.Accueil.fenetre;
import Traitement.Employe;
import Traitement.Malade;
import hopital.Connexion;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class Recherche implements ActionListener, ItemListener {
    
    public Panel panneau;
    private JPanel ligne1, ligne2, ligne3, ligne4, ligne5, ligne6, ligne7, ligne8, ligne9, ligne10, ligne11, ligne12;
    private JLabel titre, info;
    private JButton rechercher;
    private JComboBox listeType, listeInfo;
    private JTextField infoText;
    private String type, form;
    private ArrayList<ArrayList<String>> liste;
    private int typeemp;
    private JLabel nom, prenom, adresse, telephone, specialite, mutuelle, codeservice, salaire, rotation;

    public Recherche() {

        //Creation label
        titre = new JLabel("Qui recherchez-vous ?", JLabel.CENTER);
        info = new JLabel ("Rechercher par :",JLabel.CENTER);
        nom = new JLabel ("",JLabel.CENTER);
        prenom = new JLabel ("",JLabel.CENTER);
        adresse = new JLabel ("",JLabel.CENTER);
        telephone = new JLabel ("",JLabel.CENTER);
        specialite = new JLabel ("",JLabel.CENTER);
        mutuelle = new JLabel ("",JLabel.CENTER);
        codeservice = new JLabel ("",JLabel.CENTER);
        salaire = new JLabel ("",JLabel.CENTER);
        rotation = new JLabel ("",JLabel.CENTER);
        liste = new ArrayList<ArrayList<String>>();
        
        //Creation TextField
        infoText = new JTextField(20);

        //Creation JComboBox
        String[] tab = {"Patient", "Employe"};
        listeType = new JComboBox(tab);
        listeType.setPreferredSize(new Dimension(100, 20));
        
        String[] tab2 = {"Numero", "Nom"};
        listeInfo = new JComboBox(tab2);
        listeInfo.setPreferredSize(new Dimension(100, 20));

        //Creation bouton
        rechercher = new JButton("Rechercher");


        //Creation panels
        panneau = new Panel();
        ligne1 = new JPanel();
        ligne2 = new JPanel();
        ligne3 = new JPanel();
        ligne4 = new JPanel();
        ligne5 = new JPanel();
        ligne6 = new JPanel();
        ligne7 = new JPanel();
        ligne8 = new JPanel();
        ligne9 = new JPanel();
        ligne10 = new JPanel();
        ligne11 = new JPanel();
        ligne12 = new JPanel();
        
        //ajout sur le panel
        ligne1.add(titre);
        ligne1.add(listeType);
        ligne2.add(info);
        ligne2.add(listeInfo);
        ligne3.add(infoText);
        ligne3.add(rechercher);
        ligne4.add(nom);
        ligne5.add(prenom);
        ligne6.add(adresse);
        ligne7.add(telephone);
        ligne8.add(specialite);
        ligne9.add(codeservice);
        ligne10.add(rotation);
        ligne11.add(salaire);
        ligne12.add(mutuelle);
        
        ligne1.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne2.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne4.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne5.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne6.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne7.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne8.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne9.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne10.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne11.setLayout(new FlowLayout(FlowLayout.LEFT));
        ligne12.setLayout(new FlowLayout(FlowLayout.LEFT));
        

        ligne1.setBorder(new EmptyBorder(10, 420, 0, 0));
        ligne2.setBorder(new EmptyBorder(10, 420, 0, 0));
        ligne3.setBorder(new EmptyBorder(0, 0, 20, 0));
        ligne4.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne5.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne6.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne7.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne8.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne9.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne10.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne11.setBorder(new EmptyBorder(0, 420, 0, 0));
        ligne12.setBorder(new EmptyBorder(0, 420, 0, 0));
        
        
        ligne1.setPreferredSize(new Dimension(1100, 50));
        ligne2.setPreferredSize(new Dimension(1100, 50));
        ligne3.setPreferredSize(new Dimension(1100, 60));
        ligne4.setPreferredSize(new Dimension(1100, 40));
        ligne5.setPreferredSize(new Dimension(1100, 40));
        ligne6.setPreferredSize(new Dimension(1100, 40));
        ligne7.setPreferredSize(new Dimension(1100, 40));
        ligne8.setPreferredSize(new Dimension(1100, 40));
        ligne9.setPreferredSize(new Dimension(1100, 40));
        ligne10.setPreferredSize(new Dimension(1100, 40));
        ligne11.setPreferredSize(new Dimension(1100, 40));
        ligne12.setPreferredSize(new Dimension(1100, 40));
        
        panneau.add(ligne1);
        panneau.add(ligne2);
        panneau.add(ligne3);
        panneau.add(ligne4);
        panneau.add(ligne5);
        panneau.add(ligne6);
        panneau.add(ligne7);
        panneau.add(ligne8);
        panneau.add(ligne9);
        panneau.add(ligne10);
        panneau.add(ligne11);
        panneau.add(ligne12);

        // ajout des listeners
        rechercher.addActionListener(this);
        listeType.addItemListener(this);
        listeInfo.addItemListener(this);

        type = "Patient";
        form = "Numero";
        typeemp = 0;
        
        ligne8.setVisible(false);
        ligne9.setVisible(false);
        ligne10.setVisible(false);
        ligne11.setVisible(false);
        ligne12.setVisible(false);
        panneau.setPreferredSize(new Dimension(1100, 650));
        fenetre.setVisible(true);
    }

    public void itemStateChanged(ItemEvent evt) {
        type = listeType.getSelectedItem().toString();
        System.out.println ("type"+ type);
        
        form = listeInfo.getSelectedItem().toString();
        System.out.println ("form"+ form);

    }
    
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Rechercher")) {
            
            //Si l'utlisateur recherche un patient
            if(type.equals("Patient")){
                System.out.println ("C'est un patient");
                //Si on recherche la personne grace a son numero
                if(form.equals("Numero")){
                    Malade malade= new Malade(infoText.getText(),null,null,null,null,null,false,false);
                    try {
                        String requete = malade.rechercherMalNum();
                        liste = Connexion.requetes(malade.rechercherMalNum());
                        typeemp = 4;
                    } catch (SQLException ex) {
                        Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Si on recherche la personne grace a son nom
                if(form.equals("Nom")){
                    Malade malade= new Malade(null,infoText.getText(),null,null,null,null,false,false);
                    try {
                        String requete = malade.rechercherMalNom();
                        liste = Connexion.requetes(malade.rechercherMalNom());
                        typeemp = 4;
                    } catch (SQLException ex) {
                        Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                           
            }
            //Si l'utlisateur recherche un employe
            else{
                System.out.println ("C'est un employé");
                //Si on recherche la personne grace a son numero
                if(form.equals("Numero")){
                    Employe emp= new Employe(infoText.getText(),null,null,null,null);
                    try {
                        liste = Connexion.requetes(emp.rechercherEmpNumDoc());
                        typeemp = 1;
                        if (liste.size() == 0) {
                            liste = Connexion.requetes(emp.rechercherEmpNumInf());
                            typeemp = 2;
                        }
                        if (liste.size() == 0) {
                            liste = Connexion.requetes(emp.rechercherEmpNumSec());
                            typeemp = 3;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Si on recherche la personne grace a son nom
                if(form.equals("Nom")){
                    Employe emp= new Employe(null,infoText.getText(),null,null,null);
                    try {
                        liste = Connexion.requetes(emp.rechercherEmpNomDoc());
                        typeemp = 1;
                        if (liste.size() == 0) {
                            liste = Connexion.requetes(emp.rechercherEmpNomInf());
                            typeemp = 2;
                        }
                        if (liste.size() == 0) {
                            liste = Connexion.requetes(emp.rechercherEmpNomSec());
                            typeemp = 3;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            //AFFICHER RESULTAT REQUETE
            afficheReq ();   
            
        }
        if (evt.getActionCommand().equals("Retour")) {
            //fenetre.dispose();
        }
    }
    
    public void afficheReq (){
        if (liste.size() == 0){
            nom.setText("Cette personne n'existe pas");
            ligne5.setVisible(false);
            ligne6.setVisible(false);
            ligne7.setVisible(false);
            ligne8.setVisible(false);
            ligne9.setVisible(false);
            ligne10.setVisible(false);
            ligne11.setVisible(false);
            ligne12.setVisible(false);
        }
        else{
            ligne5.setVisible(true);
            ligne6.setVisible(true);
            ligne7.setVisible(true);
            switch (typeemp) {
                case 1:
                    nom.setText("Nom : "+liste.get(0).get(1));
                    prenom.setText("Prénom : "+liste.get(0).get(2));
                    adresse.setText("Adresse : "+liste.get(0).get(3));
                    telephone.setText("N° de téléphone : "+liste.get(0).get(4));
                    specialite.setText("Spécialité : "+liste.get(0).get(5));
                    ligne8.setVisible(true);
                    ligne9.setVisible(false);
                    ligne10.setVisible(false);
                    ligne11.setVisible(false);
                    ligne12.setVisible(false);
                break;
                
                case 2:
                    nom.setText("Nom : "+liste.get(0).get(1));
                    prenom.setText("Prénom : "+liste.get(0).get(2));
                    adresse.setText("Adresse : "+liste.get(0).get(3));
                    telephone.setText("N° de téléphone : "+liste.get(0).get(4));
                    codeservice.setText("Code Service : "+liste.get(0).get(5));
                    rotation.setText("Rotation : "+liste.get(0).get(6));
                    salaire.setText("Salaire : "+liste.get(0).get(7)+" euros");
                    ligne8.setVisible(false);
                    ligne9.setVisible(true);
                    ligne10.setVisible(true);
                    ligne11.setVisible(true);
                    ligne12.setVisible(false);
                break;
                    
                case 3:
                    nom.setText("Nom : "+liste.get(0).get(1));
                    prenom.setText("Prénom : "+liste.get(0).get(2));
                    adresse.setText("Adresse : "+liste.get(0).get(3));
                    telephone.setText("N° de téléphone : "+liste.get(0).get(4));
                    codeservice.setText("Code Service : "+liste.get(0).get(5));
                    ligne8.setVisible(false);
                    ligne9.setVisible(true);
                    ligne10.setVisible(false);
                    ligne11.setVisible(false);
                    ligne12.setVisible(false);
                break;
                    
                case 4:
                    nom.setText("Nom : "+liste.get(0).get(1));
                    prenom.setText("Prénom : "+liste.get(0).get(2));
                    adresse.setText("Adresse : "+liste.get(0).get(3));
                    telephone.setText("N° de téléphone : "+liste.get(0).get(4));
                    mutuelle.setText("N° de téléphone : "+liste.get(0).get(5));
                    ligne8.setVisible(false);
                    ligne9.setVisible(false);
                    ligne10.setVisible(false);
                    ligne11.setVisible(false);
                    ligne12.setVisible(true);
                break;
            }        
        }
    }
}
