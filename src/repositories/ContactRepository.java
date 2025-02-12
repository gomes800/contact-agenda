package repositories;

import domain.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactRepository {
    private final String path;

    public ContactRepository(String path) {
        this.path = path;
    }

    private Contact parseContact( String line) {
        Pattern pattern = Pattern.compile("Contact\\{id=(\\d+), name='(.*?)', phone='(.*?)', email='(.*?)'\\}");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            String name = matcher.group(2);
            String phone = matcher.group(3);
            String email = matcher.group(4);
            return new Contact(id, name, phone, email);
        }
        return null;
    }

    public List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                Contact contact = parseContact(line);
                if (contact != null) {
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void saveContacts(List<Contact> contacts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (Contact contact : contacts) {
                bw.write(contact.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addContactToFile(Contact contact) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(contact.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
