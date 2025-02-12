import domain.Contact;
import repositories.ContactRepository;
import services.ContactService;

import java.util.Optional;
import java.util.Scanner;

public class Agenda {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\financeiro\\Desktop\\projetos\\contact_agenda\\contacts.txt";
        ContactRepository contactRepository = new ContactRepository(filePath);
        ContactService contactService = new ContactService(contactRepository);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n Agenda de contatos");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Buscar contato pelo nome");
            System.out.println("3. Exibir todos so contatos");
            System.out.println("4. Remover contatos por ID");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Nome: ");
                    String name = sc.nextLine();
                    System.out.println("Telefone: ");
                    String phone = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    contactService.addContact(name, phone, email);
                    System.out.println("Contato adicionado com sucesso!");
                    break;

                case 2:
                    System.out.println("Digite o nome para buscar: ");
                    String searchName = sc.nextLine();
                    Optional<Contact> contact = contactService.findByName(searchName);
                    contact.ifPresentOrElse(
                            c -> System.out.println("Contato encontrado: " + c),
                            () -> System.out.println("Contato não encontrado!")
                    );
                    break;

                case 3:
                    System.out.println("\n Lista de contatos:");
                    contactService.getAllContacts().forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Digite o ID para remover: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (contactService.removeContact(id)) {
                        System.out.println("Contato removido!");
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        }
    }
}
