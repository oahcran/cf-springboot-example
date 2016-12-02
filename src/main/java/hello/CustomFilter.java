package hello;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;

		// response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setHeader("Access-Control-Allow-Methods", "POST, GET,
		// OPTIONS, DELETE");
		// response.setHeader("Access-Control-Max-Age", "3600");
		// response.setHeader("Access-Control-Allow-Headers", "Content-Type,
		// x-requested-with, X-Custom-Header");

		HttpServletRequest httpReq = null;
		if (req instanceof HttpServletRequest) {
			httpReq = (HttpServletRequest) req;
		}

		if (httpReq != null) {
			Cookie[] cookieList = httpReq.getCookies();
			boolean flagCookie = false;
			if (cookieList != null)
				for (int i = 0; i < cookieList.length; i++) {
					String name = cookieList[i].getName();
					String value = cookieList[i].getValue();
					System.out.println("Cookie[" + i + "], name-" + name + ", value-" + value);

					if (name.equalsIgnoreCase("JSESSIONID"))
						flagCookie = true;
				}

			if (!flagCookie) {
				Cookie cookie = new Cookie("JSESSIONID", "GREEN" + UUID.randomUUID());
				// setting cookie to expiry in 30 mins
				cookie.setMaxAge(30 * 60);
				response.addCookie(cookie);
			}

		}

		response.setHeader("TestCaseHeaderName", "TestCaseHeaderValue");

		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}