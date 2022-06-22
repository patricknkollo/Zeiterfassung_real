package com.einsatzstunden.demo.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class SpringFilterDemo implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(SpringFilterDemo.class);
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    String path = ((HttpServletRequest)servletRequest).getRequestURI();
    logger.info("hier ist der Path:  {}     <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<", path);
    logger.info("token: ", ((HttpServletRequest) servletRequest).getAuthType());
    if(path.contains("/api/controller/mitarbeiter/id/")){
      String id = path.replace("/api/controller/mitarbeiter/id/", "");
      logger.info("hier ist die id:  {}", id);
      if(("112021202120212").equals(id)){
        return;
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  /*@Override
  public void destroy() {
    Filter.super.destroy();
  }*/
}
