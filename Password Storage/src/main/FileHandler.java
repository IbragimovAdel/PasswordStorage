package main;

import controllers.LoginController;
import controllers.MainController;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    Scanner in;
    PrintWriter out;
    private Encrypted encrypted;

    public FileHandler() {
    }

    public ArrayList<Password> loadPasswords() throws Exception {
        in = new Scanner(new File("src/files/DB.list"));
        ArrayList<Password> a = new ArrayList<>();
        int i=0;
        deSerialize();
        while (in.hasNextLine()) {
            a.add(new Password(in.next(), in.next(), deCrypt(encrypted.encrypts.get(i), in.next()), in.next(), in.nextLine()));
            i++;
        }
        return a;
    }

    public String[] loadAccountData() throws IOException {
        in = new Scanner(new File("src/files/AccountData.list"));
        String[] a = new String[3];
        a[0] = in.nextLine();
        a[1] = in.nextLine();
        a[2] = in.nextLine();
        return a;
    }

    public void savePasswords() throws Exception {
        encrypted = new Encrypted();
        out = new PrintWriter(new FileWriter("src/files/DB.list"));
        ArrayList<Password> passwords = MainController.getPasswords();
        for (int i = 0; i < passwords.size(); i++) {
            Password currentPass = passwords.get(i);
            out.println(currentPass.getLabel() + " " + currentPass.getLogin() + " " + enCrypt(currentPass.getPassword()) + " " + currentPass.getRoot() + " " + currentPass.getDescription());
        }
        serialize();
        out.close();
    }

    private String enCrypt(String s) throws Exception {
        String result = "";

        String k = LoginController.getKEY();
        Cipher cipher = Cipher.getInstance("AES");

        SecretKey key = new SecretKeySpec(k.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(s.getBytes());
        for (byte b : bytes) {
            result += (b);
        }
        encrypted.encrypts.add(bytes);
        return result;
    }

    private String deCrypt(byte[] s, String musor) throws Exception {
        String result = "";
        String k = LoginController.getKEY();
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey key = new SecretKeySpec(k.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(s);
        for (byte b : bytes) {
            result += (char)b;
        }
        return result;
    }

    private void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(encrypted);
        oos.flush();
        oos.close();
    }

    private void deSerialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        encrypted = (Encrypted) oin.readObject();
    }

}
