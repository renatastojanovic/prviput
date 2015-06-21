package jwts.bookstore.service;

import java.util.List;

import jwts.bookstore.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
	Book findOne(String bookID);
	Book save(Book book);
	Page<Book> findByDeletedFalse(int page,int size);
	Page<Book> findByIsbnDeletedFalse(String isbn,int page,int size);
	Page<Book> findByTitleDeletedFalse(String title,int page,int size);
	Page<Book> findByAuthorDeletedFalse(String author,int page,int size);
	Page<Book> findByGenreDeletedFalse(String genre,int page,int size);
}
