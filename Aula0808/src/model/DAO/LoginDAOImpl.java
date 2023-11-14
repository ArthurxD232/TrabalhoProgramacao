package model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean logar(String login, String senha, int tipo) {
        try {
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            if (tipo == 2) {
                String SQL = "SELECT idlogin,senha FROM login WHERE idlogin='" + login + "' AND senha=" + "'" + senha + "'";
                ResultSet rs = conexaoDAO.getS().executeQuery(SQL);
                if (rs.next()) {//achou
                    conexaoDAO.closeConn();
                    return true;
                } else {
                    conexaoDAO.closeConn();
                    return false;
                }
            } else {
                String SQL = "SELECT i.idlogin,i.senha, a.idusuario FROM login AS i JOIN administrador AS a WHERE idlogin=" + login + " AND senha=" + "'" + senha + "'" + " AND i.idusuario=a.idusuario";
                ResultSet rs = conexaoDAO.getS().executeQuery(SQL);
                if (rs.next()) {//achou
                    conexaoDAO.closeConn();
                    return true;
                } else {
                    conexaoDAO.closeConn();
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("O login inserido não existe");
            return false;
        }
    }

    @Override
    public void deslogar() {

    }
}
