package updateBoard;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import command.board.BoardCommand;
import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

		//첨부 파일이 있는 폼은 MultipartRequest로 처리한다.
		final String DIRECTORY = "archive";
		String realPath = request.getServletContext().getRealPath(DIRECTORY);
		
		MultipartRequest multipartRequest = null;
		
		ModelAndView mav = null;
		
		try {
			multipartRequest = new MultipartRequest(request,
													realPath,
													1024 * 1024 * 10,
													"utf-8",
													new DefaultFileRenamePolicy());
			// 새로 첨부하려는 파일
			File newFile = multipartRequest.getFile("filename");
			
			// 기존에 첨부되어 있던 파일
			String filename2 = multipartRequest.getParameter("filename2");
			File oldFile = new File(realPath, filename2);
			// 서버에 있는 기존 첨부를 삭제해야 하는 경우
			// 기존 첨부와 새로운 첨ㅂ무가 모두 있는 경우
			if (oldFile != null && newFile != null) {
				if (oldFile.exists() ) {
					oldFile.delete();
				}

				// DB로 보낼 DTO
				BoardDTO dto = new BoardDTO();
				dto.setIdx(Long.parseLong(multipartRequest.getParameter("idx")));
				dto.setTitle(multipartRequest.getParameter("title"));
				dto.setContent(multipartRequest.getParameter("content"));
				// 기존 첨부와 상관 없이 새로운 첨부가 있으면 DB의 파일명을 수정해야 한다.
				if (newFile != null) {
					dto.setFilename(multipartRequest.getFilesystemName("filename"));
				}else {
					dto.setFilename("");
				}
				
				// DAO의 updateBoard() 메소드 호출
				int result = BoardDAO.getInstance().updateBoard(dto);
				
				if (result == 0) {
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정되지 않았습니다.')");
					out.println("history.back()");
					out.println("</script>");
				}else {
					mav = new ModelAndView("/10_MODEL2/selectOneBoard.b?idx=" + multipartRequest.getParameter("idx"), true);  // redirect여야만함
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return mav;
	}

}