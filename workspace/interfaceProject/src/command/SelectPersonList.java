package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;

@WebServlet("/selectPersonList.do")
public class SelectPersonList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectPersonList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Person> list = PersonDAO.getInstance().selectPersonList();
		// list -> Person -> JSONObject -> JSONArray 추가
		JSONArray arr = new JSONArray();
		for (Person person : list) {
			JSONObject obj = new JSONObject();
			obj.put("sno", person.getSno());
			obj.put("name", person.getName());
			obj.put("age", person.getAge());
			obj.put("birthday", person.getBirthday());
			obj.put("regdate", person.getRegdate().toString());  // 문자열로 바꾸는 법 : xxx + "" , xxx.toString()
			arr.add(obj);
		}
		// System.out.println(arr);
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(arr);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
