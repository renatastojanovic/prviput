package jwts.bookstore.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jwts.bookstore.model.Book;
import jwts.bookstore.service.BookService;
import jwts.bookstore.web.dto.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/books")
public class ApiBookController {
	
    @Autowired 
	BookService bookService;
    
    @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<BookDTO>> getBooks(@RequestParam(value="search",required=false) String search,@RequestParam(value="searchBy",required=false) String searchBy,
				@RequestParam(value="page") int page){
		List<Book> books =null;
		Page<Book> booksPage=null;
		int size =6;
		if(search!=null){
			if(searchBy.equals("isbn")){	
				booksPage=bookService.findByIsbnDeletedFalse(search, page, size);
				books = booksPage.getContent();
				
			}else if(searchBy.equals("title")){
				booksPage=bookService.findByTitleDeletedFalse(search, page, size);
				books = booksPage.getContent();
			}else if(searchBy.equals("author")){
				booksPage=bookService.findByAuthorDeletedFalse(search, page, size);
				books = booksPage.getContent();
			}else if(searchBy.equals("genre")){
				booksPage=bookService.findByGenreDeletedFalse(search, page, size);
				books = booksPage.getContent();
			}
			
			
		}else{
			booksPage=bookService.findByDeletedFalse(page, size);
			books = booksPage.getContent();
		}
		
		List<BookDTO> booksDTO = new ArrayList<>();
		for(Book book : books){
			booksDTO.add(new BookDTO(book));
		}
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("total-pages", ""+ booksPage.getTotalPages());
		httpHeaders.add("total-elements",""+ booksPage.getTotalElements());
		
		return new ResponseEntity<>(booksDTO,httpHeaders,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<BookDTO> getUser(@PathVariable String id){
		Book book=bookService.findOne(id);
		if(book==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		 BookDTO bookDTO=new BookDTO(book);
	     return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<BookDTO> saveUser(@RequestBody BookDTO bookDTO){
		Book book = new Book();
		book.setBookID(UUID.randomUUID().toString());
		book.setIsbn(bookDTO.getIsbn());
		book.setTitle(bookDTO.getTitle());;
		book.setAuthor(bookDTO.getAuthor());
		book.setGenre(bookDTO.getGenre());
	    book.setDeleted(false);
		book = bookService.save(book);
		return new ResponseEntity<>(new BookDTO(book), HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes="application/json" )
	public ResponseEntity<BookDTO> editUser(@PathVariable String id,
			@RequestBody BookDTO bookDTO){
		Book book=bookService.findOne(id);
		System.out.println(book.toString());
		if(book!=null){
			if(!id.equals(bookDTO.getId())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else{
			book.setIsbn(bookDTO.getIsbn());
			book.setTitle(bookDTO.getTitle());;
			book.setAuthor(bookDTO.getAuthor());
			book.setGenre(bookDTO.getGenre());
			book.setDeleted(bookDTO.isDeleted());
			book = bookService.save(book);
			return new ResponseEntity<>(new BookDTO(book), HttpStatus.OK);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
