package by.study.news.controller.impl.article;

import java.io.IOException;
import by.study.news.controller.Command;
import by.study.news.service.NewsService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteMarked implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();
	
	private static final String ID_PARAM = "id";


	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameterValues(ID_PARAM) == null) {

			request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);

		} else {
			String[] selectedId = request.getParameterValues(ID_PARAM);

			for (int i = 0; i < selectedId.length; i++) {

				try {
					newsService.deleteArticle(Integer.parseInt(selectedId[i]));
					
				} catch (NumberFormatException e) {
					response.sendRedirect("controller?command=go_to_error_page");
					e.printStackTrace();
					
				} catch (ServiceException e) {
					response.sendRedirect("controller?command=go_to_error_page");
					e.printStackTrace();
				}

			}
			request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);

		}
	}
}