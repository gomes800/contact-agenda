import controllers.ContactManager;

public class Agenda {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();

        contactManager.initializer();
        System.out.println("adicionando contato teste: ");
        contactManager.addContact();
        System.out.println("Contato adicionado.");
    }
}
