package model.DAO;

import model.Login;
import model.Produto;
import model.Usuario;

import java.sql.SQLException;

public interface AdministradorDAO {
    void criarproduto(Produto produto);
    void removerproduto(Produto produto);
    void alterarproduto();
    void registrarusuario(Usuario usuario);
    void excluirusuario(Usuario usuario);
    void alterarusuario(Usuario usuario, Login l);
    void verususarios(Usuario usuario);
    void verproduto();
}
