package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.PersonDAO;
import dto.Person;

class PersonTEST {
	
	// int size = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		// size = PersonDAO.getInstance().selectPersonList().size();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		// assertEquals(2, size, "등록된 사람은 2명이 아니다."); 
		// 123456 주민등록번호 검색
		assertNotNull(PersonDAO.getInstance().selectPerson("654321"), "123456 주민번호는 없다.");
		
	}

}
