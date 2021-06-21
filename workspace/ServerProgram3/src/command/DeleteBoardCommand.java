package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		long no = Long.parseLong(request.getParameter("no"));
		
		int result = BoardDAO.getInstance().deleteBoard(no);
		
		ModelAndView mav = null;
		try {
			if (result > 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("</script>");
				mav = new ModelAndView("/ServerProgram3/listBoard.do", true); 
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
		
				
	}

}
