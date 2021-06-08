package batch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.StudentDAO;
import dto.StudentDTO;

public class Top3StudentJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		List<StudentDTO> top3 = StudentDAO.getInstance().top3StudentList();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("포상자.txt"));
			for (int i = 0; i < top3.size(); i++) {
				bw.write((i + 1) + ", ");
				bw.write(top3.get(i).getSno() + ", ");
				bw.write(top3.get(i).getName() + ", ");
				bw.write(top3.get(i).getKor() + ", ");
				bw.write(top3.get(i).getEng() + ", ");
				bw.write(top3.get(i).getMat() + ", ");
				bw.write(top3.get(i).getAve() + ", ");
				bw.write(top3.get(i).getGrade() + "\n");
			}
			System.out.println("포상자.txt 파일이 생성되었습니다.");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}