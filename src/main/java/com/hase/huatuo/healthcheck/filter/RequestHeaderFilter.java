package com.hase.huatuo.healthcheck.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestHeaderFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestHeaderFilter.class);
    @Value("${app.AppID}")
    private String appID;
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String referer = req.getHeader("HTTP Referer");
        verifyRequestReferer(referer);
        if (referer.equals(appID)) {
            System.out.println(appID);
            log.info("auth success");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.error("auth failed");
            System.out.println(appID);
        }
    }
    public boolean verifyRequestReferer(String referer){

        String regEx = "^https://servicewechat.com/"+this.appID+"/(\\d+(\\.\\d+)?|devtools)/page-frame.html$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(referer);
        return matcher.matches();
    }
}
