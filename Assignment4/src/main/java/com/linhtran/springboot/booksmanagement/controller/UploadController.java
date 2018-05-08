package com.linhtran.springboot.booksmanagement.controller;



import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;

@Controller
public class UploadController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    private HttpServletRequest request;

    BookService bookService;

    @Autowired
    public UploadController(HttpServletRequest request, BookService bookService) {
        this.request = request;
        this.bookService = bookService;
    }

    @PostMapping("/uploadBookCover")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("id") int bookId,
                             Principal principal,
                             Model model) {

        String userEmail = principal.getName();
        Book book = bookService.searchBookById(bookId);
        if (!userEmail.equals(book.getUser().getEmail())) {
            return "redirect:/book/detail/" + bookId;
        }

        try {
            String uploadDir = "/resources/upload/book-covers/";
            String realPath = request.getServletContext().getRealPath(uploadDir);
            logger.info("Upload to ===> " + realPath);
            String filePath = realPath + "book_" + bookId + "_"+ file.getOriginalFilename();
            String filePathInHost = uploadDir + "book_" + bookId + "_"+ file.getOriginalFilename();
            File transferFile = new File(filePath);
            if (!file.isEmpty()) {
                book.setImageUrl(filePathInHost);
                bookService.updateBook(book);
                file.transferTo(transferFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/book/detail/" + bookId;
        }

        return "redirect:/book/detail/" + bookId;
    }
}
