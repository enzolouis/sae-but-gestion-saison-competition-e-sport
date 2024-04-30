package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import classes.Arbitre;
import classes.DBConnection;
import modeles.TournoiModele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArbitreDAO {

    private static ArbitreDAO instance;

    private ArbitreDAO() {
    }

    public static synchronized ArbitreDAO getInstance() {
        if (instance == null) {
            instance = new ArbitreDAO();
        }
        return instance;
    }

    /**
     * renvoie l'ensemble des arbitres
     * 
     * @return la liste de tous les arbitres dans la bdd
     */
    public List<Arbitre> getAll() {

        ArrayList<Arbitre> arbitres = new ArrayList<Arbitre>();

        try {

            PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM arbitre");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                arbitres.add(new Arbitre(rs.getInt(1), rs.getString(2), rs.getString(3),
                        classes.Nationalite.valueOf(rs.getString(4))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arbitres;

    }

    /**
     * renvoie l'arbitre du premier id reconnu
     * 
     * @param identifiant(s) de l'arbitre
     * @return un optional englobant l'arbitre séléctionné
     */
    public Optional<Arbitre> getById(Integer... id) {

        try {

            for (Integer i : id) {
                PreparedStatement st = DBConnection.getInstance()
                        .prepareStatement("SELECT * FROM arbitre WHERE idArbitre=" + i);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    return Optional.of(new Arbitre(rs.getInt(1), rs.getString(2),
                            rs.getString(3), classes.Nationalite.valueOf(rs.getString(4))));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    /**
     * ajoute un arbitre à la bdd
     * 
     * @param arbitre à ajouter
     * @return si l'addition s'est bien effectuée
     */
    public boolean add(Arbitre value) {

        int rowcount = 0;

        try {

            // séléction de l'id pour l'arbitre dans la bdd
            PreparedStatement st = DBConnection.getInstance()
                    .prepareStatement("SELECT NEXT VALUE FOR seqIdArbitre FROM arbitre");
            ResultSet rs = st.executeQuery();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            value.setIdArbitre(id);

            // insertion de l'arbitre
            st = DBConnection.getInstance().prepareStatement("INSERT INTO arbitre VALUES (?,?,?,?)");
            st.setInt(1, id);
            st.setString(2, value.getNom());
            st.setString(3, value.getPrenom());
            st.setString(4, value.getNationalite().toString());
            rowcount = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowcount > 0;

    }

    /**
     * met à jour l'arbitre dans la bdd
     * 
     * @param arbitre à mettre à jour
     * @return booléen indiquant si l'arbitre a bien été mis à jour
     */
    public boolean update(Arbitre value) {

        int rowcount = 0;

        try {

            PreparedStatement st = DBConnection.getInstance()
                    .prepareStatement("UPDATE arbitre SET nom=?, prenom=?, nationalite=? WHERE idArbitre=?");
            st.setString(1, value.getNom());
            st.setString(2, value.getPrenom());
            st.setString(3, value.getNationalite().toString());
            st.setInt(4, value.getIdArbitre());
            rowcount = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowcount > 0;

    }

    /**
     * supprime l'arbitre de la bdd
     * 
     * @param arbitre à supprimer
     * @return booléen indiquant si l'arbitre a été supprimé
     */
    public boolean delete(Arbitre value) {

        int rowcount = 0;

        try {

            // suppression des dépendances de l'arbitre
            PreparedStatement st = DBConnection.getInstance().prepareStatement("DELETE FROM gerer WHERE idArbitre=?");
            st.setInt(1, value.getIdArbitre());
            st.executeUpdate();

            // suppression de l'arbitre
            st = DBConnection.getInstance().prepareStatement("DELETE FROM arbitre WHERE idArbitre=?");
            st.setInt(1, value.getIdArbitre());
            rowcount = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowcount > 0;
    }

    /**
     * renvoie la liste des tournois arbitrés par l'arbitre
     * 
     * @param arbitre séléctionné
     * @return la liste des arbitres
     */
    public List<TournoiModele> getTournoisOfArbitre(Arbitre value) {

        ArrayList<TournoiModele> tournois = new ArrayList<>();

        try {

            PreparedStatement st = DBConnection.getInstance()
                    .prepareStatement("SELECT idTournoi FROM gerer WHERE idArbitre = ?");
            st.setInt(1, value.getIdArbitre());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tournois.add(TournoiDAO.getInstance().getById(rs.getInt(1)).get());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournois;
    }

}
