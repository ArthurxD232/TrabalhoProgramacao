package model.DAO;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {
    void inserirentrada(Entrada entrada);
    void inserirestoque(ProdutoEntrada produtoEntrada);
    void inserirsaida(Saida saida);
    void baixarestoque(ProdutoSaida produtoSaida);
    void verestoqueproduto();
    void Deletarregistrosaida(ProdutoSaida ps);
    void Deletarregistroentrada(ProdutoEntrada pe);
}
