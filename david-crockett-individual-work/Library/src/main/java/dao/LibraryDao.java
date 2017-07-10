/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Author;
import model.Book;
import model.Publisher;

/**
 *
 * @author apprentice
 */
public interface LibraryDao {

    public void addAuthor(Author author);

    public void deleteAuthor(int authorId);

    public void updateAuthor(Author author);

    public Author getAuthorById(int id);

    public List<Author> getAllAuthors();

    public void addBook(Book book);

    public void deleteBook(int bookId);

    public void updateBook(Book book);

    public Book getBookById(int id);

    public List<Book> getBooksByAuthorId(int authorId);

    public List<Book> getBooksByPublisherId(int publisherId);

    public List<Book> getAllBooks();

    public void addPublisher(Publisher publisher);

    public void deletePublisher(int publisherId);

    public void updatePublisher(Publisher publisher);

    public Publisher getPublisherById(int id);

    public List<Publisher> getAllPublishers();
}
