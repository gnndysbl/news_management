package by.study.news.controller.impl.common;

import java.io.IOException;
import by.study.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocale implements Command {

	private static final String LOCALE_PARAM = "locale";
	private static final String TARGETLINK_ATTRIBUTE = "targetLink";
	private static final String LOCAL_ATTRIBUTE = "local";
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute(LOCAL_ATTRIBUTE, request.getParameter(LOCALE_PARAM));
		response.sendRedirect((String) session.getAttribute(TARGETLINK_ATTRIBUTE));

	}
}
