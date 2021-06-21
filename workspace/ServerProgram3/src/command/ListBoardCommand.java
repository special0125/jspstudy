package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class ListBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		List<BoardDTO> list = BoardDAO.getInstance().getBoardList();
		int totalRecord = BoardDAO.getInstance().getTotalBoardCount();
		
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalRecord);
		
		ModelAndView mav = new ModelAndView("/board/listBoard.jsp", false); 
		return mav;
	}

}
