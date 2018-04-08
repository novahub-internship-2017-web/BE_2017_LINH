package com.linhtran.springboot.booksmanagement;
import com.linhtran.springboot.booksmanagement.model.MainComment;
import com.linhtran.springboot.booksmanagement.repository.BookRepository;
import com.linhtran.springboot.booksmanagement.repository.CommentRepository;
import com.linhtran.springboot.booksmanagement.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BooksManagementApplicationtest implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookRepository bookRepository;

	@Autowired
	CommentService commentService;

	@Autowired
	CommentRepository commentRepository;


	public static void main(String[] args) {
		SpringApplication.run(BooksManagementApplicationtest.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
