package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.exceptions.BookNotFoundException;
import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.Author;
import com.risingCode.bibliosIO.models.Book;
import com.risingCode.bibliosIO.models.Reader;
import com.risingCode.bibliosIO.models.Review;
import com.risingCode.bibliosIO.repository.AuthorRepository;
import com.risingCode.bibliosIO.repository.BookRepository;
import com.risingCode.bibliosIO.repository.ReaderRepository;
import com.risingCode.bibliosIO.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReviewRepository reviewRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder encoder;


    public ReaderService(ReaderRepository readerRepository, ReviewRepository reviewRepository, AuthorRepository authorRepository, BookRepository bookRepository, PasswordEncoder encoder) {
        this.readerRepository = readerRepository;
        this.reviewRepository = reviewRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.encoder = encoder;
    }

    public Reader  registerReader(Reader reader){

        Optional<Reader> myUser = readerRepository
                .findByUsername(reader.getUserName());

        if (myUser.isPresent())
            throw new UserAlreadyCreatedException();

        reader.setPassword(encoder.encode(reader.getPassword()));
        reader.setEnabled(true);

        return readerRepository.save(reader);
    }

    public Boolean authenticateReader(UserLoginFormDto userLoginForm){

        return readerRepository
                .findByUsername(userLoginForm.getUserName())
                .map(user -> encoder.matches(userLoginForm.getPassword(),
                            user.getPassword()))
                .orElse(false);

    }

    public Book getBook(UUID id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(("Book with id" +
                        " %s not found").formatted(id)));
    }

    public Page<Book> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Page<Author> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public Review sendReview(Review review){
        return reviewRepository.save(review);
    }


}
