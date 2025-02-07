package controllers;

import domain.Contact;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContactManager {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        contacts.add(new Contact(0, "janex", "21993915998", "janex@gmail.com"));

        String path = "C:\\Users\\financeiro\\Desktop\\projetos\\test_contacts.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            for (Contact contact : contacts) {
                bw.write(contact.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
