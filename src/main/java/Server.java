import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<Connection> connections = new ArrayList<>();

    public Server() {
        try {
            ServerSocket server = new ServerSocket(8000);

            while (true) {
                Socket socket = server.accept();
                Connection con = new Connection(socket);
                connections.add(con);
                con.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public Connection(Socket socket) {

            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                //when client joined
                name = in.readLine();
                connections.forEach(connection -> connection.out.println(name + " joined now"));

                //when client send message
                while (true) {
                    String str = in.readLine();
                    if(str.equals("exit")) break;
                    connections.forEach(connection -> connection.out.println(name + ": " + str));
                }

                //when client leave chat
                connections.forEach(connection -> connection.out.println(name +" has left"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
