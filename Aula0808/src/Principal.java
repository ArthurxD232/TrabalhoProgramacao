import model.DAO.AdministradorDAOImpl;
import model.DAO.LoginDAOImpl;
import view.AdministradorView;
import view.UsuariosView;

import java.sql.*;
import java.util.Scanner;


public class Principal {
	public static void main(String[] args) throws SQLException {

		Scanner scanner = new Scanner(System.in);
		int opcao =0;
		int vala;
		String login, senha;
		System.out.println("Você deseja se logar como administrador(1) ou usuario(2)?");
		opcao = Integer.parseInt(scanner.nextLine());
		while(opcao != 1 && opcao !=2) {
			System.out.println("Opçao nao existente! Escolha 1 para administrador ou 2 para usuario");
			opcao = Integer.parseInt(scanner.nextLine());
		}

			System.out.println("Digite o login");
			login = scanner.nextLine();
			System.out.println("Digite a senha");
			senha= scanner.nextLine();
			LoginDAOImpl lg = new LoginDAOImpl();
			if(lg.logar(login,senha,opcao)){
				if(opcao == 1){
					new AdministradorView().menu();
				}else{
					new UsuariosView().menu();
				}
			}
			else {
				System.out.println("Login invalido!");
			}
	}
}


