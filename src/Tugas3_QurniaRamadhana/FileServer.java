package Tugas3_QurniaRamadhana;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) {
        final int PORT = 12345;
        final int PORT1 = 13567;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server mendengarkan pada port " + PORT);
            
            ServerSocket serverSocket1 = new ServerSocket(PORT1);
            System.out.println("Server mendengarkan pada port " + PORT1);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Socket clientSocket1 = serverSocket1.accept();
                
                System.out.println("Klien terkoneksi: " + clientSocket.getInetAddress());
                System.out.println("Klien terkoneksi: " + clientSocket1.getInetAddress());

                // Menerima file dari klien
                receiveFile(clientSocket);
                receiveFile(clientSocket1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(Socket clientSocket) {
        try {
            // Inisialisasi input stream untuk membaca data dari klien
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // Menerima informasi file (nama dan ukuran)
            String fileName = objectInputStream.readUTF();
            long fileSize = objectInputStream.readLong();

            System.out.println("Menerima file: " + fileName + "dengan ukuran (" + fileSize + " bytes)");

            // Inisialisasi output stream untuk menulis data ke file
            FileOutputStream fileOutputStream = new FileOutputStream("server_" + fileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            // Menerima dan menulis data file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            // Menutup output stream dan socket
            bufferedOutputStream.close();
            clientSocket.close();

            System.out.println("File sukses diterima.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
