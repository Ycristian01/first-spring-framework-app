package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher nor = new Publisher("Norma", "Calle Lagartos #15", "Baq", "Atl", 0000012);

        publisherRepository.save(nor);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234567");
        eric.getBoooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(nor);
        nor.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(nor);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "39345054564");
        rod.getBoooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(nor);
        nor.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(nor);

        System.out.println("Started in bootstrap!");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("the " + nor.getName() + " publisher has " + nor.getBooks().size() + " books");
    }
}
