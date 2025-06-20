package de.jensknipper.re_director.web.filter;

import de.jensknipper.re_director.db.RedirectRepository;
import de.jensknipper.re_director.db.entity.RedirectInformation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class DomainRedirectFilter extends OncePerRequestFilter {

  private final RedirectRepository redirectRepository;

  public DomainRedirectFilter(RedirectRepository redirectRepository) {
    this.redirectRepository = redirectRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String host = request.getHeader("Host");
    if (host != null) {
      String normalizedHost = normalizeHost(host);
      RedirectInformation redirectInformation =
          redirectRepository.findRedirectInformationBySource(normalizedHost);
      if (redirectInformation != null) {
        response.setStatus(redirectInformation.httpStatusCode().getCode());
        response.setHeader("Location", redirectInformation.target());
      }
    }
    filterChain.doFilter(request, response);
  }

  private static String normalizeHost(String host) {
    int portStartIndex = host.indexOf(":");
    if (portStartIndex >= 0) {
      return host.substring(0, portStartIndex);
    }
    return host;
  }
}
