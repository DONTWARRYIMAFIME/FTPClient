package by.bsuir.FtpClient.Menus;

import by.bsuir.FtpClient.FtpClient;

import java.io.IOException;
import java.util.Deque;
import java.util.Scanner;

public class MenuAction extends Menu {

    public MenuAction(Deque<Menu> stack, FtpClient ftp, Scanner scanner) {
        super(stack, ftp, scanner);
    }

    @Override
    public void start() throws IOException {
        printCommands();
        String command = scanner.next();

        switch (command) {
            case "1" -> {
                changeWorkingDirectory();
                listFiles();
            }
            case "2" -> {
                makeDirectory();
                listFiles();
            }
            case "3" -> listFiles();
            case "4" -> readFileInfo();
            case "5" -> readFileSize();
            case "6" -> rename();
            case "7" -> deleteFile();
            case "8" -> deleteDirectory();
            case "9" -> downloadFile();
            case "10" -> downloadDirectory();
            case "11" -> uploadFile();
            case "12" -> uploadDirectory();
            case "0" -> {
                ftp.logout();
                setExit();
            }
            default -> System.out.println(unknownCommandMessage);
        }
    }

    @Override
    public void printCommands() {
        System.out.println("1 - change working directory");
        System.out.println("2 - make directory");
        System.out.println("3 - list files and directories");
        System.out.println("4 - read file info");
        System.out.println("5 - read size of a file");
        System.out.println("6 - rename file/directory");
        System.out.println("7 - delete file");
        System.out.println("8 - delete directory");
        System.out.println("9 - download file");
        System.out.println("10 - download directory");
        System.out.println("11 - upload file");
        System.out.println("12 - upload directory");
        System.out.println("0 - logout");
    }

    private void changeWorkingDirectory(){
        System.out.println("Enter directory:");
        try {
            ftp.changeWorkingDirectory(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeDirectory() {
        System.out.println("Enter directory:");
        try {
            ftp.makeDirectory(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listFiles() {
        try {
            ftp.listFiles("").forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileInfo() {
        System.out.println("Enter file name:");
        try {
            ftp.readFileInfo(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileSize() {
        System.out.println("Enter file name:");
        try {
            ftp.readFileSize(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rename() {
        System.out.println("Enter name:");
        String oldName = scanner.next();
        System.out.println("Enter new name:");
        String newName = scanner.next();
        try {
            ftp.rename(oldName, newName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        System.out.println("Enter file name:");
        try {
            ftp.deleteFile(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteDirectory() {
        System.out.println("Enter directory name:");
        try {
            ftp.deleteDirectory(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile() {
        System.out.println("Enter source path:");
        String source = scanner.next();
        System.out.println("Enter destination path:");
        String destination = scanner.next();

        try {
            ftp.downloadFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadDirectory() {
        System.out.println("Enter source path:");
        String source = scanner.next();
        System.out.println("Enter destination path:");
        String destination = scanner.next();

        try {
            ftp.downloadDirectory(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadFile() {
        System.out.println("Enter source path:");
        String source = scanner.next();
        System.out.println("Enter destination path:");
        String destination = scanner.next();

        try {
            ftp.uploadFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadDirectory() {
        System.out.println("Enter source path:");
        String source = scanner.next();
        System.out.println("Enter destination path:");
        String destination = scanner.next();

        try {
            ftp.uploadDirectory(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
