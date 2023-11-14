package model.DAO;

import model.Login;
import model.Produto;
import model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class AdministradorDAOImpl implements AdministradorDAO{
    @Override
    public void criarproduto(Produto produto){
        try {
            String SQL = "INSERT INTO produto (idproduto,descricao) VALUES " + "(" + produto.getIdproduto() + ",'" + produto.getDescricao() + "')";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O produto ja existe");
        }
    }


    @Override
    public void removerproduto(Produto produto){
        try {
            String SQL = "DELETE FROM produto WHERE idproduto='" + produto.getIdproduto() + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println("O produto não existe");
        }
    }

    public void alterarproduto(){
        try {
            String SQL;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o ID do produto a ser alterado");
            int idproduto = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite a nova descricao");
            String descricao = scanner.nextLine();
            SQL = "UPDATE produto SET descricao= '" + descricao + "' WHERE  idproduto='" + idproduto + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O produto não existe");
        }
    }

    @Override
    public void registrarusuario(Usuario usuario){
        try {
            String SQL = "INSERT INTO usuario(idusuario, nome,sobrenome,senha, login, funcao) VALUES " +
                    "(" + usuario.getIdusuario() + ",'" + usuario.getNome() + "','" + usuario.getSobrenome() + "','" + usuario.getSenha() + "'," +
                    "'" + usuario.getLogin() + "', '" + usuario.getFuncao() + "')";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O usuário ja existe");
        }
    }


    @Override
    public void excluirusuario(Usuario usuario) {
        try {
            String SQL = "DELETE FROM usuario WHERE idusuario='" + usuario.getIdusuario() + "' ";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O usuário não existe");
        }
    }

    @Override
    public void alterarusuario(Usuario usuario, Login l){
        try {
            String SQL;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o ID do usuario para mudar os dados: ");
            int id = Integer.parseInt(scanner.nextLine());
            l.setIdusuario(id);
            System.out.print("Digite o novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o novo sobrenomenome: ");
            String sobrenome = scanner.nextLine();
            System.out.print("Digite o novo login: ");
            String login = scanner.nextLine();
            l.setidlogin(login);
            System.out.print("Digite a nova senha: ");
            String senha = scanner.nextLine();
            System.out.print("Confirme a senha: ");
            String confirmasenha = scanner.nextLine();
            while (!confirmasenha.equalsIgnoreCase(senha)) {
                System.out.println("Senha errada, digite novamente.");
                System.out.print("Digite a nova senha: ");
                senha = scanner.nextLine();
                System.out.print("Confirme a senha: ");
                confirmasenha = scanner.nextLine();
            }
            l.setSenha(senha);
            System.out.print("Digite a nova funcao: ");
            String funcao = scanner.nextLine();
            SQL = "UPDATE usuario SET nome='" + nome + "', sobrenome='" + sobrenome + "'" +
                    ",login='" + login + "',senha='" + senha + "'" +
                    ",funcao='" + funcao + "' WHERE  idusuario='" + id + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O usuário não existe");
        }
    }

    @Override
    public void verususarios(Usuario usuario){
        try {
            String dados;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o ID do usuario a ser buscado: ");
            int id = scanner.nextInt();
            String SQL = "SELECT * FROM usuario WHERE idusuario='" + id + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            ResultSet rs = s.executeQuery(SQL);
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Sobrenome: " + rs.getString("sobrenome"));
                System.out.println("Login: " + rs.getString("login"));
                System.out.println("Funcao: " + rs.getString("funcao"));
            }
        } catch (SQLException e){
            System.out.println("O usuário não existe");
        }
    }
    public void registrarlogin(Login login){
        try {
            String SQL = "INSERT INTO login(idlogin, senha, idusuario) VALUES " +
                    "('" + login.getidlogin() + "','" + login.getSenha() + "'," + login.getIdusuario() + ")";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O login já existe");
        }
    }

    public void excluirlogin(Usuario usuario, Login login){
        try {
            String u;
            String sql = "SELECT * FROM usuario WHERE idusuario='" + usuario.getIdusuario() + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                login.setidlogin(rs.getString("login"));
            }
            String SQL = "DELETE FROM login WHERE idlogin='" + login.getidlogin() + "' ";
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O login não existe");
        }
    }
    public void alterarlogin(Login l){
        try {
            String SQL;
            SQL = "UPDATE login SET idlogin='" + l.getidlogin() + "',senha='" + l.getSenha() + "'WHERE  idusuario='" + l.getIdusuario() + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            s.executeUpdate(SQL);
            conexaoDAO.closeConn();
        } catch (SQLException e){
            System.out.println("O login não existe");
        }
    }
    public void verproduto(){
        try {
            String dados;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o ID do produto a ser buscado: ");
            int id = scanner.nextInt();
            String SQL = "SELECT * FROM produto WHERE idproduto='" + id + "'";
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conexao = conexaoDAO.ConDB("projetofinal");
            Statement s = conexaoDAO.getS();
            ResultSet rs = s.executeQuery(SQL);
            while (rs.next()) {
                System.out.println("ID do produto: " + rs.getString("idproduto"));
                System.out.println("Descricao: " + rs.getString("descricao"));
            }
        } catch (SQLException e){
            System.out.println("O produto não existe");
        }

    }


}
