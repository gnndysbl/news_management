package by.study.news.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import by.study.news.bean.UserRole;

public class CommonFilter implements Filter {

	private static final String USER_ROLE_ATTRIBUTE = "userRole";

	private static final String COMMAND_PARAM = "command";

	private static final String ADD_ARTICLE_COMMAND = "add_article";
	private static final String EDIT_ARTICLE_COMMAND = "edit_article";
	private static final String DELETE_ARTICLE_COMMAND = "delete_article";
	private static final String DELETE_MARKED_COMMAND = "delete_marked";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String command = request.getParameter(COMMAND_PARAM).toLowerCase();

		if (command.equals(ADD_ARTICLE_COMMAND) | command.equals(DELETE_ARTICLE_COMMAND)
				| command.equals(DELETE_MARKED_COMMAND) | command.equals(EDIT_ARTICLE_COMMAND)) {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;

			HttpSession session = req.getSession(true);

			if (session.getAttribute(USER_ROLE_ATTRIBUTE) == null
					| session.getAttribute(USER_ROLE_ATTRIBUTE) != UserRole.ADMIN) {

				res.sendRedirect("controller?command=go_to_error_page&error=action is not allowed");

				return;

			}
		}

		chain.doFilter(request, response);

	}

}
