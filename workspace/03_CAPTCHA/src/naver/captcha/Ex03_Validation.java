package naver.captcha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Ex03_Validation {
	
	public static void main(String[] args) {
	        
	    	// 캡차 키 발급 코드
	    	String clientId = "COj9il9DSFNgSFAqPhEp"; //애플리케이션 클라이언트 아이디값";
	        String clientSecret = "vZRSVXHzgj"; //애플리케이션 클라이언트 시크릿값";
	
	        String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
	        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
	
	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        
	        // 네이버가 응답한 JSON데이터 : {"key": "xFPY4QXSvmQlQcmqx"}
	        String responseBody = get1(apiURL, requestHeaders);
	
	        // System.out.println(responseBody);
	        // key값을 빼자.
	        JSONParser parser = new JSONParser();
	        JSONObject obj = null;
	        try {
	        	obj = (JSONObject)parser.parse(responseBody);
	        }catch (Exception e) {
				e.printStackTrace();
			}
	        String key = (String)obj.get("key");  // get() 메소드는 Object를 반환하므로 캐스팅 필요
	    	
	    	// 캡차 이미지 발급 코드
	        // String key = "CAPTCHA_KEY"; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
	        apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
	        
	        // 이미지 생성이 성공하면 responseBody == "이미지 캡차가 생성되었습니다."
	        responseBody = get2(apiURL,requestHeaders);
	
	        System.out.println(responseBody);
	        
	        //사용자 입력 검증 코드
	        code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
	        String value = JOptionPane.showInputDialog("자동가입 방지문자를 입력하세요."); // 사용자가 입력한 캡차 이미지 글자값
	        apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
	
	        responseBody = get1(apiURL, requestHeaders);
	
	        System.out.println(responseBody);
	        
	        JSONObject obj2 = null;
	        try {
	        	obj2 = (JSONObject)parser.parse(responseBody);
	        }catch (Exception e) {
				e.printStackTrace();
			}
	        
	        boolean result = (boolean)obj2.get("result");
	        if (result) {
	        	System.out.println("휴먼이군요~");
	        } else {
	        	System.out.println("누구냐 넌?");
	        }
	        
	    }
	    
	    private static String get1(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }
	    
	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);
	
	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();
	
	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }
	
	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
	
	    private static String get2(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return getImage(con.getInputStream());
	            } else { // 에러 발생
	                return error(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }
	
	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }
	
	    private static String getImage(InputStream is){
	        int read;
	        byte[] bytes = new byte[1024];
	        // 랜덤한 이름으로  파일 생성
	        String filename = Long.valueOf(new Date().getTime()).toString();
	        File f = new File(filename + ".jpg");
	        try(OutputStream outputStream = new FileOutputStream(f)){
	            f.createNewFile();
	            while ((read = is.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	            return "이미지 캡차가 생성되었습니다.";
	        } catch (IOException e) {
	            throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.",e);
	        }
	    }
	
	    private static String error(InputStream body) {
	        InputStreamReader streamReader = new InputStreamReader(body);
	
	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();
	
	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }
	
	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
	
		

}
