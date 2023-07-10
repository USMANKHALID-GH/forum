package com.usman.forum.service;


import com.rometools.rome.feed.rss.Channel;
import jakarta.servlet.http.HttpServletRequest;

public interface RssFeedGenerator {


    Channel generate(HttpServletRequest request);
}
