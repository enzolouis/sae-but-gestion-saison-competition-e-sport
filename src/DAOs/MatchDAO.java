package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.DBConnection;
import classes.Match;

public class MatchDAO {

    private static MatchDAO instance;

    public MatchDAO() {
        super();
    }

    public static synchronized MatchDAO getInstance() {
        if (instance == null) {
            instance = new MatchDAO();
        }
        return instance;
    }

    /**
     * renvoie l'ensemble des matchs
     * 
     * @return la liste des matchs dans la bdd
     */
    public List<Match> getAll() {

        ArrayList<Match> matchs = new ArrayList<Match>();

        try {

            PreparedStatement st = DBConnection.getInstance().prepareStatement("SELECT * FROM matchT");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Match m = new Match(rs.getInt(1), rs.getInt(4), rs.getBoolean(2));
                Integer idVainqueur = rs.getInt(3);
                if (idVainqueur != 0) {
                    m.setVainqueur(idVainqueur);
                }

                PreparedStatement stEquipes = DBConnection.getInstance()
                        .prepareStatement("SELECT idEquipe1, idEquipe2 FROM disputer WHERE idMatch = ?");
                stEquipes.setInt(1, m.getIDMatch());
                ResultSet rsEquipes = stEquipes.executeQuery();

                if (rsEquipes.next()) {
                    m.addEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(1)).get());
                    m.addEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(2)).get());
                    matchs.add(m);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchs;

    }

    /**
     * renvoie le match du premier id reconnu
     * 
     * @param identifiant(s) de l'arbitre
     * @return optional englobant le match séléctionné
     */
    public Optional<Match> getById(Integer... id) {

        try {

            PreparedStatement st = DBConnection.getInstance()
                    .prepareStatement("SELECT * FROM matchT WHERE idMatch=?");

            for (Integer i : id) {

                st.setInt(1, i);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {

                    Match m = new Match(rs.getInt(1), rs.getInt(4), rs.getBoolean(2));
                    Integer idVainqueur = rs.getInt(3);

                    if (idVainqueur != 0) {
                        m.setVainqueur(idVainqueur);
                    }

                    PreparedStatement stEquipes = DBConnection.getInstance()
                            .prepareStatement("SELECT idEquipe1, idEquipe2 FROM disputer WHERE idMatch = ?");

                    stEquipes.setInt(1, m.getIDMatch());
                    ResultSet rsEquipes = stEquipes.executeQuery();

                    if (rsEquipes.next()) {
                        m.addEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(1)).get());
                        m.addEquipe(EquipeDAO.getInstance().getById(rsEquipes.getInt(2)).get());
                    }

                    return Optional.of(m);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    /**
     * rajoute un match à la bdd
     * 
     * @param match à ajouter
     * @return booléen indiquant si le match a été ajouté
     */
    public boolean add(Match value) {

        int rowcountMatch = 0;
        int rowcountDisputer = 1;

        try {

            // récupération de l'identifiant dans la bdd
            PreparedStatement st = DBConnection.getInstance()
                    .prepareStatement("SELECT NEXT VALUE FOR SeqIdMatch FROM dual");
            ResultSet rs = st.executeQuery();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            value.setIdMatch(id);

            // insertion du match
            st = DBConnection.getInstance().prepareStatement("INSERT INTO matchT VALUES (?,?,?,?)");
            st.setInt(1, id);
            st.setBoolean(2, value.IsItFinale());
            if (value.getVainqueur() == 0) {
                st.setNull(3, Types.INTEGER);
            } else {
                st.setInt(3, value.getVainqueur());
            }
            st.setInt(4, value.getIdTournoi());
            rowcountMatch = st.executeUpdate();

            // récupération des participants
            if (!value.getEquipes().isEmpty()) {
                st = DBConnection.getInstance().prepareStatement("INSERT INTO disputer VALUEs(?,?,?)");
                st.setInt(1, id);
                st.setInt(2, value.getEquipes().get(0).getIdEquipe());
                st.setInt(3, value.getEquipes().get(1).getIdEquipe());
                rowcountDisputer = st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowcountMatch > 0 && rowcountDisputer > 0;

    }

    /**
     * met à jour un match dans la bdd
     * 
     * @param match à maj
     * @return booléen indiquant si la modification a été effectuée
     */
    public boolean update(Match value) {

        int rowcount = 0;
        PreparedStatement st;

        try {

            if (value.getVainqueur() == 0) {

                st = DBConnection.getInstance()
                        .prepareStatement("UPDATE matchT SET finale=? WHERE idMatch=?");

                st.setBoolean(1, value.IsItFinale());
                st.setInt(2, value.getIDMatch());
                rowcount = st.executeUpdate();

            } else {

                st = DBConnection.getInstance()
                        .prepareStatement("UPDATE matchT SET finale=?, idVainqueur=? WHERE idMatch=?");
                st.setBoolean(1, value.IsItFinale());
                st.setInt(2, value.getVainqueur());
                st.setInt(3, value.getIDMatch());
                rowcount = st.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowcount > 0;

    }

    /**
     * supprime un match de la bdd
     * 
     * @param match à supp
     * @return booléen indiquant si la suppression a été effectuée
     */
    public boolean delete(Match value) {

        int rowcount = 0;

        try {

            PreparedStatement st = DBConnection.getInstance()
                    .prepareStatement("DELETE FROM disputer WHERE idMatch=?");
            st.setInt(1, value.getIDMatch());
            st.executeUpdate();
            st = DBConnection.getInstance().prepareStatement("DELETE FROM matchT WHERE idMatch=?");
            st.setInt(1, value.getIDMatch());
            rowcount = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowcount > 0;
    }

}
