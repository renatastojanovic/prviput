package jwts.bookstore.repository;

import jwts.bookstore.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	
	@Query("select b from Book b where b.deleted = false")
	Page<Book> findByDeletedFalse(Pageable pageable);
	@Query("select b from Book b where b.isbn = :isbn and  b.deleted = false")
	Page<Book> findByIsbnDeletedFalse(@Param("isbn")String isbn,Pageable pageable);
	@Query("select b from Book b where b.title = :title and  b.deleted = false")
	Page<Book> findByTitleDeletedFalse(@Param("title")String title,Pageable pageable);
	@Query("select b from Book b where b.author = :author and  b.deleted = false")
	Page<Book> findByAuthorDeletedFalse(@Param("author")String author,Pageable pageable);
	@Query("select b from Book b where b.genre = :genre and  b.deleted = false")
	Page<Book> findByGenreDeletedFalse(@Param("genre")String genre,Pageable pageable);

}
