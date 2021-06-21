package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class GoinsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		return new ModelAndView("/ServerProgram3/board/insertBoard.jsp", true);
	}

}
