package ex06;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forward1
 */
@WebServlet("/Forward1")
public class Forward1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forward1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 포워드 이동
		
		// 1. 서버가 직접 이동한다. (서버 내부 이동이기 때문에 주소는 '/매핑'만 전달)
		// 2. 클라이언트는 이동을 알 수 없다. (주소창이 안 변한다.)
		// 3. 기존의 요청을 그대로 가지고 이동한다. (현재 request가 전달된다.)
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Forward2");
		dispatcher.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
