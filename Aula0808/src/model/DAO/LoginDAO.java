package model.DAO;

import java.sql.SQLException;

public interface LoginDAO {
    public boolean logar(String login, String senha, int tipo);
    public void deslogar();
}
