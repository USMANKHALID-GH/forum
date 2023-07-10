package com.usman.forum.controller;


import com.rometools.rome.feed.rss.Channel;
import com.usman.forum.service.RssFeedGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/rss")
public class RssFeedGeneratorController {

    @Autowired
   private RssFeedGenerator feedGenerator;

    @GetMapping("/")
   public Channel generate(HttpServletRequest request){
       return  feedGenerator.generate(request);
   }

}
