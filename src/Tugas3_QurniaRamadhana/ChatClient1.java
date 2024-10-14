package Tugas3_QurniaRamadhana;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhana
 */
public class ChatClient1 {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT1 = 9877;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            // Memasukkan nama klien
            System.out.print("Masukan Nama: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();

            // Mengirim pesan ke server
            sendMessage(clientSocket, name);

            // Menutup socket setelah mengirim pesan
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(DatagramSocket clientSocket, String name) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Memasukkan pesan
            System.out.print("Masukan Pesan (atau ketik 'exit' untuk berhenti): ");
            String message = reader.readLine();

            if ("exit".equalsIgnoreCase(message)) {
                break; // Keluar dari loop jika pengguna mengetik 'exit'
            }

            // Mengirim pesan ke server
            byte[] sendData = (name + ": " + message).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(SERVER_IP), SERVER_PORT1);
            clientSocket.send(sendPacket);
        }
    }
}
