/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import static Interface.Accueil.fenetre;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Admin
 */
public class Choix {
        
    private JTabbedPane Onglets = null;
    private Recherche larecherche;
    private preMaj lamaj;
    private Reporting lereport;
    
    public Choix (){
        
        larecherche = new Recherche();  
        lamaj = new preMaj();
        lereport = new Reporting();
        
        //ajout panel sur la frame
        Onglets = new JTabbedPane();
        Onglets.addTab("Recherche", null, larecherche.panneau, null); //
        Onglets.addTab("Mise à jour", null, lamaj.panneau, null); //
        Onglets.addTab("Reporting", null, lereport.panneau, null);
        fenetre.setContentPane(Onglets);
        fenetre.setVisible(true);
    } 
}
