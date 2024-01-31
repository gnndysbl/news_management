package by.study.news.service.exception;

public class ServiceLoginIsUsedException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceLoginIsUsedException() {

		super();
	}

	public ServiceLoginIsUsedException(String message) {
		super(message);
	}

	public ServiceLoginIsUsedException(Exception e) {
		super(e);
	}

	public ServiceLoginIsUsedException(String message, Exception e) {
		super(message, e);
	}
}
