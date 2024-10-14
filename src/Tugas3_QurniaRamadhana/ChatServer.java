package Tugas3_QurniaRamadhana;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatServer {
    private static final int PORT = 9876;
    private static final int PORT1 = 9877;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("Server mendengarkan pada port " + PORT);
            
            DatagramSocket serverSocket1 = new DatagramSocket(PORT1);
            System.out.println("Server mendengarkan pada port " + PORT1);
            

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Menerima pesan dari klien
                serverSocket.receive(receivePacket);
                serverSocket1.receive(receivePacket);
 
                // Menampilkan pesan yang diterima
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Menerima pesam dari " + receivePacket.getAddress() + ": " + message);

                // Menyebarkan pesan ke semua klien yang terhubung (simulasi)
                broadcastMessage(serverSocket, receivePacket);
                broadcastMessage1(serverSocket1, receivePacket);
                
            }
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }

    private static void broadcastMessage(DatagramSocket serverSocket, DatagramPacket receivePacket) throws IOException {
        // Simulasi: Menyebarkan pesan ke semua klien yang terhubung
        // Untuk implementasi yang lebih canggih, simpan daftar klien yang terhubung
        // dan kirim pesan ke setiap klien.
        // Simulasi di sini hanya menunjukkan konsep dasar.
        byte[] sendData = receivePacket.getData();
        for (int port = 10000; port <= 10002; port++) {
            serverSocket.send(new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), port));
        }
    }
    
    private static void broadcastMessage1(DatagramSocket serverSocket1, DatagramPacket receivePacket) throws IOException {
        // Simulasi: Menyebarkan pesan ke semua klien yang terhubung
        // Untuk implementasi yang lebih canggih, simpan daftar klien yang terhubung
        // dan kirim pesan ke setiap klien.
        // Simulasi di sini hanya menunjukkan konsep dasar.
        byte[] sendData = receivePacket.getData();
        for (int port = 10000; port <= 10002; port++) {
            serverSocket1.send(new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), port));
        }
    }
}
