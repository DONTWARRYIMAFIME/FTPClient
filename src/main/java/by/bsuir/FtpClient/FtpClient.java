package by.bsuir.FtpClient;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {

    private FTPClient ftp;

    public Collection<String> listFiles(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);

        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    public boolean connect(String server, int port) throws IOException {
        ftp = new FTPClient();

        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            System.out.println("Exception in connecting to FTP Server");
            return false;
        }

        return true;
    }

    public boolean login(String user, String password) throws IOException {
        return ftp.login(user, password);
    }

    public void logout() throws IOException {
        ftp.logout();
    }

    public void disconnect() throws IOException {
        ftp.disconnect();
    }

    public void changeWorkingDirectory(String path) throws IOException {
        ftp.changeWorkingDirectory(path);
    }

    public void makeDirectory(String path) throws IOException {
        ftp.makeDirectory(path);
    }

    public void readFileInfo(String path) throws IOException {
        FTPFile ftpFile = ftp.mlistFile(path);
        if (ftpFile != null) {
            String name = ftpFile.getName();
            long size = ftpFile.getSize();
            String timestamp = ftpFile.getTimestamp().getTime().toString();
            String type = ftpFile.isDirectory() ? "Directory" : "File";

            System.out.println("Name: " + name);
            System.out.println("Size: " + size);
            System.out.println("Type: " + type);
            System.out.println("Timestamp: " + timestamp);
        } else {
            System.out.println("The specified file/directory may not exist!");
        }
    }

    public void readFileSize(String path) throws IOException {
        FTPFile file = ftp.mlistFile(path);
        long size = file.getSize();
        System.out.println("File size = " + size);
    }

    public void rename(String oldName, String newName) throws IOException {
        ftp.rename(oldName, newName);
    }

    public void deleteFile(String path) throws IOException {
        ftp.deleteFile(path);
    }

    public void deleteDirectory(String path) throws IOException {
        FTPUtils.removeDirectory(ftp, "", path);
    }

    public void uploadFile(File file, String path) throws IOException {
        ftp.storeFile(path, new FileInputStream(file));
    }

    public void downloadFile(String source, String destination) throws IOException {
        FTPUtils.downloadSingleFile(ftp, source, destination);
    }

    public void downloadDirectory(String source, String destination) throws IOException {
        FTPUtils.downloadDirectory(ftp, "", source, destination);
    }

    public void uploadFile(String source, String destination) throws IOException {
        FTPUtils.uploadSingleFile(ftp, source, destination);
    }

    public void uploadDirectory(String source, String destination) throws IOException {
        FTPUtils.uploadDirectory(ftp, "", source, destination);
    }

}