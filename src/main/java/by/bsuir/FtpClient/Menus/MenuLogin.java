package by.bsuir.FtpClient.Menus;

import by.bsuir.FtpClient.FtpClient;

import java.io.IOException;
import java.util.Deque;
import java.util.Scanner;

public class MenuLogin extends Menu {

    private boolean logIn() throws IOException {
        System.out.println("Enter user name:");
        String user = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        return ftp.login(user, password);
    }

    public MenuLogin(Deque<Menu> stack, FtpClient ftp, Scanner scanner) {
        super(stack, ftp, scanner);
    }

    @Override
    public void start() throws IOException {
        printCommands();
        String command = scanner.next();

        switch (command) {
            case "1" -> {
                if (logIn()) {
                    stack.offerLast(new MenuAction(stack, ftp, scanner));
                }
            }
            case "0" -> {
                ftp.disconnect();
                setExit();
            }
            default -> System.out.println(unknownCommandMessage);
        }
    }

    @Override
    public void printCommands() {
        System.out.println("1 - login");
        System.out.println("0 - disconnect");
    }
}
