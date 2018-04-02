package com.linhtran.assignment.springboot.booksmanagement;



import com.linhtran.assignment.springboot.booksmanagement.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserDao userDao;

	@Override
	public void run(String... args) throws Exception {
		logger.info(userDao.findUserById(3).getEmail());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
