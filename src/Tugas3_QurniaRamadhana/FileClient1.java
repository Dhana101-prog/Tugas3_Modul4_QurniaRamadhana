package Tugas3_QurniaRamadhana;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhana
 */
public class FileClient1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 13567;

        try {
            // Membaca file yang akan dikirim ke server
            String filePath = "D://Projek/Tugas3_TDP-UDP/src//coba1.txt";
            File file = new File(filePath);

            Socket clientSocket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Terkoneksi ke server.");

            // Mengirim file ke server
            sendFile(file, clientSocket);

            // Menutup socket setelah selesai
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(File file, Socket clientSocket) {
        try {
            // Inisialisasi output stream untuk mengirim data ke server
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // Mengirim informasi file (nama dan ukuran)
            objectOutputStream.writeUTF(file.getName());
            objectOutputStream.writeLong(file.length());
            objectOutputStream.flush();

            // Inisialisasi input stream untuk membaca data dari file
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // Membaca dan mengirim data file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Menutup output stream dan file
            bufferedInputStream.close();
            clientSocket.shutdownOutput();

            System.out.println("File sukses terkirim.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
