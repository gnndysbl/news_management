package by.study.news.controller.impl.common;

import java.io.IOException;


import java.util.List;
import by.study.news.bean.Article;
import by.study.news.controller.Command;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.NewsService;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToBasePage implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();
	
	private static final String NEWS_PER_PAGE_ATTRIBUTE = "newsPerPage";
	private static final String EDIT_ARTICLE_ATTRIBUTE = "editArticle";
	private static final String VIEW_ARTICLE_ATTRIBUTE = "viewArticle";
	private static final String ADD_ARTICLE_ATTRIBUTE = "addArticle";
	private static final String TARGETLINK_ATTRIBUTE = "targetLink";
	private static final String NEWS_ATTRIBUTE = "news";
	private static final String PAGE_ATTRIBUTE = "page";


	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Article> lastNews;

		try {
			
			HttpSession session = request.getSession(true);
			session.setAttribute(NEWS_PER_PAGE_ATTRIBUTE, 3);

			lastNews = newsService.getLast((Integer) session.getAttribute(NEWS_PER_PAGE_ATTRIBUTE));
			
			request.setAttribute(NEWS_ATTRIBUTE, lastNews);
			session.setAttribute(TARGETLINK_ATTRIBUTE, "controller?command=go_to_base_page");
			session.setAttribute(VIEW_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(ADD_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(EDIT_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(PAGE_ATTRIBUTE, 1);

			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
	
		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
			e.printStackTrace();
		}

	}
}
