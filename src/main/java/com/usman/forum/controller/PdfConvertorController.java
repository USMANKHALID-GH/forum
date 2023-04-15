package com.usman.forum.controller;


import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.usman.forum.service.Implementation.PdfConvertorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/convert")
@Slf4j
public class PdfConvertorController {

    private PdfConvertorService service;

    @GetMapping("/{userId}/{questionId}/pdf")
    public void setPdfConvertorService(HttpServletResponse response,
                                       @PathVariable("userId") long userId,
                                       @PathVariable("questionId")long questionId) throws IOException {

        response.setContentType("application/pdf");

        String headerKey="Content-Disposition";
        String headerValue="Question";

        response.setHeader(headerKey,headerValue);
        service.export(response,questionId,userId);

    }

    @GetMapping("/")
    public  void get(){
        log.info("this is me===============");
        Document document= new Document(PageSize.A4);
    }

}
