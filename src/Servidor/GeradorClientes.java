package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author raihlima
 */
class GeradorClientes implements Runnable {

    Socket socket;

    public GeradorClientes(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String linha;
            String subLista;
            while ((linha = in.readLine()) != null && !((linha.trim().equals("quit")))) {
                if (linha.length() > 3) {
                    subLista = linha.substring(0, 3);
                    if (subLista.equalsIgnoreCase("eco")) {
                        out.println("Eco: " + linha.substring(3, linha.length()));
                    } else {
                        out.println("");
                    }
                } else {
                    out.println("");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    socket.close();
                    ServidorDeEco.diminuirQuantidadeClientes();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
