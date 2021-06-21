package batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.BoardDAO;
import dto.BoardDTO;

public class BestHit implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			System.out.println("=====최대 조회수 게시글=====");
			BoardDTO boardDTO = BoardDAO.getInstance().bestHit();
			System.out.println("제목: " + boardDTO.getTitle());
			System.out.println("내용: " + boardDTO.getContent());
			System.out.println("조회수: " + boardDTO.getHit());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
