package controller;

import command.BoardCommand;
import command.DeleteBoardCommand;
import command.GoinsertBoardCommand;
import command.InsertBoardCommand;
import command.InsertReplyCommand;
import command.ListBoardCommand;
import command.SelectBoardByNoCommand;

public class BoardCommandMapper {
	
	private static BoardCommandMapper instance = new BoardCommandMapper();
	private BoardCommandMapper() {}
	public static BoardCommandMapper getInstance() {
		if (instance == null) {
			instance = new BoardCommandMapper();
		}
		return instance;
	}
	
	public BoardCommand getCommand(String cmd) {
		BoardCommand command = null;
		switch (cmd) {
		case "listBoard.do":
			command = new ListBoardCommand();
			break;
		case "goinsertBoard.do":
			command = new GoinsertBoardCommand();
			break;
		case "insertBoard.do":
			command = new InsertBoardCommand();
			break;
		case "SelectBoardByNo.do":
			command = new SelectBoardByNoCommand();
			break;
		case "insertReply.do":
			command = new InsertReplyCommand();
			break;
		case "deleteBoard.do":
			command = new DeleteBoardCommand();
			break;
		}
		return command;
		
	}
	
}