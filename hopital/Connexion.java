/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hopital;

/*
 * 
 * Librairies importées
 */
import Interface.Accueil;
import Interface.Choix;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


    
public class Connexion {
    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat requete
     */
    private Connection conn;
    public static Statement stmt;
    public static ResultSet rset;
    public static ResultSetMetaData rsetMeta;
    
    public static int nbColonne, nbLigne=0;
    public String requete;
    public static String champs="";
    public static String infos [][]={};
    //public static String infos [];
    
    public Connexion(String usernameECE, String passwordECE, String loginDatabase, String passwordDatabase, boolean test) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion via le tunnel SSH avec le username et le password ECE
        SSHTunnel ssh = new SSHTunnel(usernameECE, passwordECE);

        if (ssh.connect() && test) {
            System.out.println("Connexion reussie");
            Choix choix= new Choix(); //connexion OK on peut continuer la navigation

            // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
            String urlDatabase = "jdbc:mysql://localhost:3305/" + usernameECE;

            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();
                
            //CODE ALICE:  ecourtem-rw  6qX55kcg

        } 
        else{
           System.out.println("Connexion offline en cours");
           // url de connexion jdbc:mysql://localhost/test
           // avec comme login root et pas de mdp
           conn = DriverManager.getConnection("jdbc:mysql://localhost/ecourtem","root","projetjava652");
          // on créer la statement stmt
           stmt = conn.createStatement();
           Choix choix= new Choix();
           System.out.println(" Connexion offline reussie"); 
        }
    }

    public static ArrayList requetes(String requete) throws SQLException {
        rset = stmt.executeQuery(requete);
        rsetMeta = rset.getMetaData();
        int nbColonne = rsetMeta.getColumnCount();
        ArrayList<ArrayList<String>> liste;
        liste = new ArrayList<ArrayList<String>>();
        ArrayList<String> ligne;
        ligne = new ArrayList<String>();
        int j = 0;
        String champs;
 
        while (rset.next()) {
            ligne.clear();
            for (int i = 1; i < nbColonne + 1; i++) {
                champs = rset.getString(i);
                ligne.add(champs);
            }
            liste.add(ligne);
            ligne = new ArrayList<String>();
            j++;
        } 
    return liste;
    }
    
    public static void requetesManip (String requete)throws SQLException{
        // récupération de l'ordre de la requete
        stmt.executeUpdate(requete);
    }
          
}
