package jwts.bookstore.web.dto;

import jwts.bookstore.model.Book;

public class BookDTO {
	
private String id;
	
	private String isbn;
	
	private String title;
	
	private String author;
	
	private String genre;
	
	private boolean deleted;
	
	public BookDTO(){
		super();
	}
	public BookDTO(Book book){
		this.id = book.getBookID();
		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.author = book.getAuthor();
	    this.genre=book.getGenre();
	    this.deleted=book.isDeleted();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
