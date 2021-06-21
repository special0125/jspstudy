package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class SelectBoardByNoCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDTO boardDTO = BoardDAO.getInstance().selectBoardByNo(no);
		BoardDAO.getInstance().updateHit(no);
		
		request.setAttribute("boardDTO", boardDTO);
		
		
		ModelAndView mav = new ModelAndView("/board/selectBoard.jsp", false); 
		return mav;
		
	}

}
