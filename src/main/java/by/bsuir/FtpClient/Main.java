package by.bsuir.FtpClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.setProperty("jdk.tls.useExtendedMasterSecret", "false");
        try {
            new App().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
