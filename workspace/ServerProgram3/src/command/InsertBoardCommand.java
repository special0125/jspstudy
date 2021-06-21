package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		//작성자 제목 내용
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setAuthor(request.getParameter("author"));
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setContent(request.getParameter("content"));
		
		int result = BoardDAO.getInstance().insertBoard(boardDTO);
		
		ModelAndView mav = null;
		try {
			if (result > 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 등록되었습니다.')");
				out.println("</script>");
				mav = new ModelAndView("/ServerProgram3/listBoard.do", true); 
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	
	}

}
