package services;

import domain.Contact;
import repositories.ContactRepository;

import java.util.List;
import java.util.Optional;

public class ContactService {

    private final ContactRepository contactRepository;
    private List<Contact> contacts;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        this.contacts = contactRepository.loadContacts();
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void addContact(String name, String phone, String email) {
        int id = contacts.isEmpty() ? 1 : contacts.getLast().getId() + 1;
        Contact contact = new Contact(id, name, phone, email);
        contacts.add(contact);
        contactRepository.addContactToFile(contact);
    }

    public Optional<Contact> findByName(String name) {
        return contacts.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public boolean removeContact(int id) {
        boolean removed = contacts.removeIf(c -> c.getId() == id);
        if (removed) {
            contactRepository.saveContacts(contacts);
        }
        return removed;
    }
}
