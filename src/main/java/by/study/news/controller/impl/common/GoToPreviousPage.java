package by.study.news.controller.impl.common;

import java.io.IOException;

import by.study.news.controller.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToPreviousPage implements Command {

	private static final String EDIT_ARTICLE_ATTRIBUTE = "editArticle";
	private static final String VIEW_ARTICLE_ATTRIBUTE = "viewArticle";
	private static final String ADD_ARTICLE_ATTRIBUTE = "addArticle";
	private static final String PAGE_ATTRIBUTE = "page";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.getAttribute(PAGE_ATTRIBUTE);
		int targetPage = (Integer) session.getAttribute(PAGE_ATTRIBUTE);
		targetPage--;
		session.setAttribute(PAGE_ATTRIBUTE, targetPage);
		session.setAttribute(VIEW_ARTICLE_ATTRIBUTE, null);
		session.setAttribute(ADD_ARTICLE_ATTRIBUTE, null);
		session.setAttribute(EDIT_ARTICLE_ATTRIBUTE, null);

		response.sendRedirect("controller?command=show_page");

	}
}
