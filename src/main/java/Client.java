import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {
        Scanner scan = new Scanner(System.in);
        String ip = "127.0.0.1";

        try {
            Socket socket = new Socket(ip, 8000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Enter your name:");
            out.println(scan.nextLine());

            String strOut = "";
            while (!strOut.equals("exit")) {

                strOut = scan.nextLine();
                out.println(strOut);

                String strIn = in.readLine();
                System.out.println(strIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
