package jwts.bookstore.service.impl;

import jwts.bookstore.model.Book;
import jwts.bookstore.repository.BookRepository;
import jwts.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
public class JpaBookService implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
		
	}

	@Override
	public Page<Book> findByDeletedFalse(int page, int size) {
		
		return bookRepository.findByDeletedFalse(new PageRequest(page,size));
	}

	@Override
	public Book findOne(String bookID) {
	
		return bookRepository.findOne(bookID);
	}

	

	@Override
	public Page<Book> findByIsbnDeletedFalse(String isbn, int page, int size) {
		return bookRepository.findByIsbnDeletedFalse(isbn, new PageRequest(page,size));
	}

	@Override
	public Page<Book> findByTitleDeletedFalse(String title, int page, int size) {
		return bookRepository.findByTitleDeletedFalse(title, new PageRequest(page,size));
	}

	@Override
	public Page<Book> findByAuthorDeletedFalse(String author, int page, int size) {
		return bookRepository.findByAuthorDeletedFalse(author, new PageRequest(page,size));
	}

	@Override
	public Page<Book> findByGenreDeletedFalse(String genre, int page, int size) {
		return bookRepository.findByGenreDeletedFalse(genre, new PageRequest(page,size));
	}

}
