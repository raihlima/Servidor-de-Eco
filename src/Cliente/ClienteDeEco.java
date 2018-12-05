package Cliente;

/**
 *
 * @author raihlima
 */
import Servidor.ServidorDeEco;
import java.io.*;
import java.net.*;

public class ClienteDeEco {

    public static void main(String args[]) {
        try {
            Socket conexao = new Socket("localhost", 2000);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            String linha;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("> ");
                linha = teclado.readLine();
                saida.println(linha);
                linha = entrada.readLine();
                if (linha == null) {
                    System.out.println("Conexão encerrada!");
                    ServidorDeEco.diminuirQuantidadeClientes();
                    break;
                } else {
                    System.out.println(linha);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
