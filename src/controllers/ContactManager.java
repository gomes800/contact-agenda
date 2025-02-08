package controllers;

import domain.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManager {

    ArrayList<Contact> contacts = new ArrayList<Contact>();
    String path = "C:\\Users\\CAIXA1\\Documents\\Projects\\contact-agenda\\contacts.txt";
    int cont = 0;

    public void initializer() {

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Linha lida: " + line);
                Pattern pattern = Pattern.compile("Contact\\{id=(\\d+), name='(.*?)', phone='(.*?)', email='(.*?)'\\}");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int id = Integer.parseInt(matcher.group(1));
                    String name = matcher.group(2);
                    String phone = matcher.group(3);
                    String email = matcher.group(4);

                    Contact contact = new Contact(id, name, phone, email);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addContact() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            contacts.add(new Contact(0, "janex", "21993915998", "janex@gmail.com"));
            cont += 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Contact findByName(String name, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            Pattern pattern = Pattern.compile("Contact\\{id=(\\d+), name='(.*?)', phone='(.*?)', email='(.*?)'\\}");

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String nameFound = matcher.group(2);
                    if (nameFound.equalsIgnoreCase(name)) {
                        int id = Integer.parseInt(matcher.group(1));
                        String phone = matcher.group(3);
                        String email = matcher.group(4);

                        return new Contact(id, nameFound, phone, email);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
