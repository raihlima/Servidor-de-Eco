package Servidor;

/**
 *
 * @author raihlima
 */
import java.io.*;
import java.net.*;

public class ServidorDeEco {
    private static int quantidadeClientes = 0;
    public static void main(String args[]) {
        
        try {
            ServerSocket s = new ServerSocket(2000);
            s.setReuseAddress(true);
           // int repeticao =0;

            while (true) {
                //if(quantidadeClientes <5){
                System.out.print("Esperando alguém se conectar...");
                Socket conexao = s.accept();
                
                System.out.println(" Conectou!");
                
                GeradorClientes geradorClientes = new GeradorClientes(conexao);
                
                   quantidadeClientes++;
                    System.out.println("Clientes conectados: "+ quantidadeClientes);
 //                   repeticao=0;
                    new Thread(geradorClientes).start();
                    /*
                } else {
                    if(repeticao==0){
                        System.out.println("Todos os slots ocupados");
                        repeticao=1;                      
                    }  
                    
                }*/
                System.out.print("");
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
    }

    public static void diminuirQuantidadeClientes() {
        
        quantidadeClientes = quantidadeClientes-1;
        System.out.println("Clientes conectados: "+ quantidadeClientes);
    }
}


