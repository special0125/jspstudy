package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.SchedulerException;

import dao.BoardDAO;
import dto.BoardDTO;

@WebListener
public class CronSchedulerListener implements ServletContextListener {
    
	// field
	CronScheduler cronScheduler;
	
	// constructor
	public CronSchedulerListener() { }
	
	// method
	@Override
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("=====WEB SERVICE STOP!=====");
    	try {
    		cronScheduler.scheduler.clear();
    		cronScheduler.scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	cronScheduler = new CronScheduler("0 0/1 * 1/1 * ? *", BestHit.class);
    	// BoardDTO boardDTO = BoardDAO.getInstance().bestHit();
		// System.out.println("제목: " + boardDTO.getTitle());
		// System.out.println("내용: " + boardDTO.getContent());
		// System.out.println("조회수: " + boardDTO.getHit());
    	cronScheduler.execute();
    }
	
}
