package by.study.news.controller.impl.user;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import by.study.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignOut implements Command {

	private static final String LOCAL_ATTRIBUTE = "local";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession oldSession = request.getSession(true);
		String currentLocale = (String) oldSession.getAttribute(LOCAL_ATTRIBUTE);
		oldSession.invalidate();
		HttpSession session = request.getSession(true);
		session.setAttribute(LOCAL_ATTRIBUTE, currentLocale);

		response.sendRedirect("controller?command=go_to_base_page");

	}
}
