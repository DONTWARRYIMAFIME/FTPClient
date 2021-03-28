package by.bsuir.FtpClient.Menus;

import by.bsuir.FtpClient.FtpClient;

import java.io.IOException;
import java.util.Deque;
import java.util.Scanner;

public class MenuConnection extends Menu {

    private final String server = "127.0.0.1";
    private final int port = 21;

    public MenuConnection(Deque<Menu> stack, FtpClient ftp, Scanner scanner) {
        super(stack, ftp, scanner);
    }

    @Override
    public void start() throws IOException {

        printCommands();
        String command = scanner.next();

        switch (command) {
            case "1" -> {
                if (ftp.connect(server, port)) {
                    stack.offerLast(new MenuLogin(stack, ftp, scanner));
                }
            }
            case "0" -> setExit();
            default -> System.out.println(unknownCommandMessage);
        }

    }

    @Override
    public void printCommands() {
        System.out.println("1 - connect to FTP server");
        System.out.println("0 - exit");
    }
}
