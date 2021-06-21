package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class InsertReplyCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setAuthor(request.getParameter("author"));
		replyDTO.setContent(request.getParameter("content"));
		replyDTO.setBoardNo(Long.parseLong(request.getParameter("boardNo")));
		
		ReplyDTO replyDTO2 = BoardDAO.getInstance().insertReply(replyDTO);
		request.setAttribute("replyDTO", replyDTO2);
		
		ModelAndView mav = null;
		try {
			if (replyDTO2 != null) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('댓글이 등록되었습니다.')");
				out.println("</script>");
				mav = new ModelAndView("/ServerProgram3/SelectBoardByNo.do?no=" + replyDTO.getBoardNo(), true);  
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
		
	}

}
