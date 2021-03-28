package by.bsuir.FtpClient;

import by.bsuir.FtpClient.Menus.Menu;
import by.bsuir.FtpClient.Menus.MenuConnection;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class App {

    private final Scanner scanner = new Scanner(System.in);
    private final Deque<Menu> stack = new ArrayDeque<>();
    private final FtpClient ftp = new FtpClient();

    public void start() throws IOException {


        stack.offerLast(new MenuConnection(stack, ftp, scanner));

        while (!stack.isEmpty()) {
            stack.peekLast().start();
            assert stack.peekLast() != null;
            if (stack.peekLast().isExit()) {
                stack.pollLast();
            }
        }

    }
}