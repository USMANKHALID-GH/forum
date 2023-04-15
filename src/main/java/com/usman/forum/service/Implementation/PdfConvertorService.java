package com.usman.forum.service.Implementation;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.usman.forum.model.Question;
import com.usman.forum.model.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class PdfConvertorService {

    private final QuestionImpl questionImp;
    private  final UserImp userImp;

    public  void export(HttpServletResponse response, long questionId, long userId) throws IOException {
          User user =userImp.findUser(userId);
          Question q=questionImp.findAQuestion(questionId);
          log.info(q.getContent()+"getting starting====");
//          Document document = new Document(PageSize.A4);
          Document document= new Document(PageSize.A4);

          log.info(document.getDocumentLanguage()+ "first");
          PdfWriter.getInstance(document, response.getOutputStream());

          document.open();
          Font  headerFont= FontFactory.getFont(FontFactory.COURIER_BOLD);
          headerFont.setColor(Color.black);
          headerFont.setSize(20);

          Paragraph p1 = new Paragraph(q.getTitle(), headerFont);
          p1.setAlignment(Paragraph.ALIGN_CENTER);
          log.info(p1.getContent());


          Paragraph p2 = new Paragraph(q.getContent(), headerFont);
          p1.setAlignment(Paragraph.ALIGN_LEFT);
          Paragraph p3 = new Paragraph(q.getAnswer().get(0).getContent(), headerFont);
          p1.setAlignment(Paragraph.ALIGN_RIGHT);


          document.add(p1);
          document.add(p2);
          document.add(p3);
          document.addAuthor(user.getFirstName());
          document.close();

    }



}
