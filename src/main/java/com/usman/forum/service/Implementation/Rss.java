package com.usman.forum.service.Implementation;

import com.rometools.rome.feed.rss.*;
import com.usman.forum.dto.UserDto;
import com.usman.forum.mapper.UserMapper;

import com.usman.forum.service.RssFeedGenerator;
import com.usman.forum.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Rss implements RssFeedGenerator {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;


    @Override
    public Channel generate(HttpServletRequest request) {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
         channel.setTitle("FIRST RSS");
         channel.setDescription("My First Rss Feed");
         channel.setLink("http//usman.com");
        channel.setUri("LINK");
        channel.setGenerator("CUSTOM RSS");
        channel.setPubDate(new Date());
        channel.setLanguage("en");


        List<Item> feed= new ArrayList<>();
        List<UserDto> users= mapper.toDto(service.findAllUser(Pageable.unpaged()).toList());

        for(UserDto user: users){

            Item item= new Item();
            item.setAuthor(user.getLastName()+" "+user.getFirstName());
            item.setLink("my link");
            item.setUri("my url");
            item.setTitle(user.getEmail());
            Description description= new Description();
            description.setType("String");
            description.setValue(user.getRelatedField());
            item.setDescription(description);
            item.setPubDate(Date.from(user.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant()));
            System.out.println(item);
            feed.add(item);
            Source source=new Source();
            source.setValue("url");
            item.setSource(source);
            item.setComments("this is my comment");
            Content content= new Content();
            content.setValue("there is much room for us");
            item.setContent(content);
        }

          channel.setItems(feed);
        return  channel;
    }
}
