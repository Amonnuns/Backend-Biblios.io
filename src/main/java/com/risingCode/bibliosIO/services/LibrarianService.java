package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.AuthorDTO;
import com.risingCode.bibliosIO.dto.BookDTO;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.Author;
import com.risingCode.bibliosIO.models.Book;
import com.risingCode.bibliosIO.models.Librarian;
import com.risingCode.bibliosIO.repository.AuthorRepository;
import com.risingCode.bibliosIO.repository.BookRepository;
import com.risingCode.bibliosIO.repository.LibrarianRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder encoder;


    public LibrarianService(LibrarianRepository librarianRepository,
                            AuthorRepository authorRepository, BookRepository bookRepository, PasswordEncoder encoder) {
        this.librarianRepository = librarianRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.encoder = encoder;
    }

    public Librarian registerLibrarian(Librarian librarian){

        Optional<Librarian> myUser = librarianRepository
                .findByUsername(librarian.getUsername());

        if (myUser.isPresent())
            throw new UserAlreadyCreatedException();

        librarian.setPassword(encoder.encode(librarian.getPassword()));
        librarian.setEnabled(true);

        return librarianRepository.save(librarian);
    }

    public Boolean authenticateLibrarian(UserLoginFormDto librarianLoginForm){

        return librarianRepository
                .findByUsername(librarianLoginForm.getUserName())
                .map(librarian -> encoder.matches(librarianLoginForm.getPassword(),
                        librarian.getPassword()))
                .orElse(false);
        
    }
    public Author registerAuthor(AuthorDTO authorDTO){
        Author newAuthor = new Author();
        BeanUtils.copyProperties(authorDTO, newAuthor);

        Optional<Author> authorFromRep= authorRepository.findByFirstName(newAuthor.getFirstName());

        if (authorFromRep.isPresent())
            return authorFromRep.get();
        return authorRepository.save(newAuthor);
    }

    public Book registerBook(BookDTO book){
        Book newBook = new Book();
        BeanUtils.copyProperties(book, newBook, "authors");

        //adding authors to book
        book.getAuthors().stream()
                .map(this::registerAuthor)
                .forEach(newBook::addAuthors);

        return bookRepository.save(newBook);
    }


}
