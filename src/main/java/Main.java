import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Run as a server (s), client (c)?");
            String input = sc.nextLine();
            switch (input){
                case "s":
                    System.out.println("Server");
                    new Server();
                    break;
                case "c":
                    System.out.println("Client");
                    new Client();
                    break;
            }
        }
    }
}
