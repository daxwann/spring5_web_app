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
public class BootstrapData implements CommandLineRunner {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Started in bootstrap");

    Publisher penguin = new Publisher("Penguin", "900 Lafayette Blvd, New York, NY");
    this.publisherRepository.save(penguin);
    System.out.println("Number of books published by Penguin: " + penguin.getBooks().size());

    Author junot = new Author("Junot", "Diaz");
    Book wondrousLife = new Book("Wondrous Life of Oscar Wao", "12345");
    junot.getBooks().add(wondrousLife);
    wondrousLife.getAuthors().add(junot);
    penguin.getBooks().add(wondrousLife);
    wondrousLife.setPublisher(penguin);
    this.authorRepository.save(junot);
    this.bookRepository.save(wondrousLife);
    this.publisherRepository.save(penguin);

    Author roy = new Author("Aridanthi", "Roy");
    Book god = new Book("God of Small Things", "54321");
    roy.getBooks().add(god);
    god.getAuthors().add(roy);
    penguin.getBooks().add(god);
    god.setPublisher(penguin);
    this.authorRepository.save(roy);
    this.bookRepository.save(god);

    this.publisherRepository.save(penguin);

    System.out.println("Number of books: " + this.bookRepository.count());
    System.out.println("Number of books published by Penguin: " + penguin.getBooks().size());
  }
}
