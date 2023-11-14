package model.DAO;

import model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioDAOImpl implements UsuarioDAO{


    @Override
    public void inserirentrada(Entrada entrada){
        try {
            String SQL = "INSERT INTO entrada (identrada,dataped,dataentr) VALUES " + "(" + entrada.getIdentrada() + ",'" + entrada.getDataped() + "','" + entrada.getDataentr() + "')";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("Entrada já existe");
        }
    }

    @Override
    public void inserirestoque(ProdutoEntrada produtoEntrada) {
        try {
            String SQL = "INSERT INTO produtoentrada (idproduto, identrada, qtd, valor) VALUES " + "(" + produtoEntrada.getIdproduto() + "," +
                    produtoEntrada.getIdentrada() + "," + produtoEntrada.getQtd() + "," + produtoEntrada.getValor() + ")";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("Erro ao inserir produto");
        }
    }


    @Override
    public void inserirsaida(Saida saida){
        try {
            String SQL = "INSERT INTO saida (idsaida,datasaid) VALUES " + "(" + saida.getIdsaida() + ",'" + saida.getDatasaida() + "')";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        }catch (SQLException e){
            System.out.println("Saída já existe");
        }
    }

    public void baixarestoque(ProdutoSaida produtoSaida){
        try {
            String SQL = "INSERT INTO produtosaida (idproduto, idsaida, qtd, valor) VALUES " + "(" + produtoSaida.getIdproduto() + "," +
                    produtoSaida.getIdsaida() + "," + produtoSaida.getQtd() + "," + produtoSaida.getValor() + ")";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("Erro ao dar baixa no estoque");
        }
    }

    public void verestoqueproduto(){
        try {
            int somaentrada = 0, somasaida = 0, quantidadefinal = 0, valorsaid = 0, valorentrad = 0, valortotal = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o ID do produto a ser buscado: ");
            int id = scanner.nextInt();
            String SQL = "SELECT qtd, valor FROM produtoentrada WHERE idproduto='" + id + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            ResultSet rs = s.executeQuery(SQL);
            while (rs.next()) {
                somaentrada = somaentrada + Integer.parseInt(rs.getString("qtd"));
                valorentrad = valorentrad + Integer.parseInt(rs.getString("valor"));
            }
            SQL = "SELECT qtd, valor FROM produtosaida WHERE idproduto='" + id + "'";
            rs = s.executeQuery(SQL);
            while (rs.next()) {
                somasaida = somasaida + Integer.parseInt(rs.getString("qtd"));
                valorsaid = valorsaid + Integer.parseInt(rs.getString("valor"));
            }
            quantidadefinal = somaentrada - somasaida;
            valortotal = valorentrad - valorsaid;
            System.out.println("A quantidade do produto com ID " + id + " e de " + quantidadefinal);
            System.out.println("O valor total do produto com ID " + id + " e de " + valortotal);
        }catch (SQLException e){
            System.out.println("Produto não existe");
        }
    }

    public void Deletarregistrosaida(ProdutoSaida ps){
        try {
            String SQL = "DELETE FROM produtosaida WHERE idsaida=" + ps.getIdsaida() + " AND idproduto=" + ps.getIdproduto();
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            SQL = "DELETE FROM saida WHERE idsaida=" + ps.getIdsaida();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        }catch (SQLException e){
            System.out.println("Saída não existe");
        }
    }

    public void Deletarregistroentrada(ProdutoEntrada pe){
        try {
            String SQL = "DELETE FROM produtoentrada WHERE identrada=" + pe.getIdentrada() + " AND idproduto=" + pe.getIdproduto();
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            SQL = "DELETE FROM entrada WHERE identrada=" + pe.getIdentrada();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        }catch (SQLException e){
            System.out.println("Entrada não existe");
        }
    }

}
