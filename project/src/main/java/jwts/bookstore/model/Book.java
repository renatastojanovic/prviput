package jwts.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblBook")
public class Book {
	@Id
	@Column(name="bookID", nullable=false) 
	private String bookID;
	@Column(name="ISBN", nullable=false) 
	private String isbn;
	@Column(name="title", nullable=false) 
	private String title;
	@Column(name="author", nullable=false) 
	private String author;
	@Column(name="genre", nullable=false) 
	private String genre;
	@Column(name="deleted", nullable=false) 
	private boolean deleted;

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
}
