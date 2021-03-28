package by.bsuir.FtpClient.Menus;

import by.bsuir.FtpClient.FtpClient;

import java.io.IOException;
import java.util.Deque;
import java.util.Scanner;

public abstract class Menu {

    protected final Scanner scanner;
    protected final FtpClient ftp;
    protected final Deque<Menu> stack;

    protected final String unknownCommandMessage = "Unknown command, try again";
    protected boolean exit = false;

    public Menu(Deque<Menu> stack, FtpClient ftp, Scanner scanner) {
        this.stack = stack;
        this.ftp = ftp;
        this.scanner = scanner;
    }

    public abstract void start() throws IOException;
    public abstract void printCommands();

    public boolean isExit() {
        return exit;
    }

    protected void setExit() {
        exit = true;
    }


}
