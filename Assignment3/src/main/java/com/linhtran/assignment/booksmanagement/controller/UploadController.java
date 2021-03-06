package com.linhtran.assignment.booksmanagement.controller;


import com.linhtran.assignment.booksmanagement.model.Book;
import com.linhtran.assignment.booksmanagement.service.BookService;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    BookService bookService;


    @PostMapping("/uploadImage")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("id") int bookId,
                             Principal principal,
                             Model model) {
        String userEmail = principal.getName();
        Book book = bookService.searchBookById(bookId);
        if (!userEmail.equals(book.getUser().getEmail())) {
            return "redirect:/book/detail?id=" + bookId;
        }

        try {
            String uploadDir = "/resources/img/";
            String realPath = request.getServletContext().getRealPath(uploadDir);
            String filePath = realPath + "book_" + bookId + "_"+ file.getOriginalFilename();
            String filePathInHost = uploadDir + "book_" + bookId + "_"+ file.getOriginalFilename();
            File transferFile = new File(filePath);
            if (!file.isEmpty()) {
                book.setImagePath(filePathInHost);
                bookService.updateBookInformation(book);
                file.transferTo(transferFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/book/detail?id=" + bookId;
        }

        return "redirect:/book/detail?id=" + bookId;
    }
}
