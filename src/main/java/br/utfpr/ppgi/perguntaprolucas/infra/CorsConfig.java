package br.utfpr.ppgi.perguntaprolucas.infra;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CorsConfig implements Filter {

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader(
        "Access-Control-Allow-Headers",
        "Authorization, Origin, X-Requested-With, Content-Type, Accept, X-Usuario-Emulador, "
            + "X-Usuario-Canal, g-recaptcha-response, x-flag-captcha");
    filterChain.doFilter(servletRequest, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void destroy() {}
}
