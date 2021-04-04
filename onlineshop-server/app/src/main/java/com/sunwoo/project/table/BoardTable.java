package com.sunwoo.project.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.sunwoo.project.domain.Board;
import com.sunwoo.util.JsonFileHandler;
import com.sunwoo.util.Request;
import com.sunwoo.util.Response;

// 1) 간단한 동작 테스트를 위해 임의의 값을 리턴한다.
public class BoardTable implements DataTable {

  File jsonFile = new File("board.json");
  List<Board> list;

  public BoardTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Board.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Board board = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "board/insert" :
        fields = request.getData().get(0).split(",");
        board = new Board();

        if(list.size() > 0) {
          board.setNumber(list.get(list.size() - 1).getNumber() + 1);
        } else {
          board.setNumber(1);
        }

        board.setTitle(fields[0]);
        board.setContent(fields[1]);
        board.setWriter(fields[2]);
        board.setRegisteredDate(new Date(System.currentTimeMillis()));

        list.add(board);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;

      case "board/selectall":
        for (Board b : list) {
          response.appendData(String.format("%d,%s,%s,%s,%d",
              b.getNumber(), b.getTitle(), b.getWriter(), b.getRegisteredDate(),
              b.getViewCount()));
        }
        break;

      case "board/select":
        int no = Integer.parseInt(request.getData().get(0));

        board = getBoard(no);
        if(board != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%d", 
              board.getNumber(), 
              board.getTitle(), 
              board.getContent(),
              board.getWriter(), 
              board.getRegisteredDate(), 
              board.getViewCount()));
        } else {
          throw new Exception("해당 번호의 게시글이 없습니다.");
        }
        break;
      case "board/selectByKeyword":
        String keyword = request.getData().get(0);

        for (Board b : list) {
          if(b.getTitle().contains(keyword) ||
              b.getContent().contains(keyword) ||
              b.getWriter().contains(keyword)) {

            response.appendData(String.format("%d,%s,%s,%s,%d", 
                b.getNumber(), 
                b.getTitle(), 
                b.getWriter(), 
                b.getRegisteredDate(), 
                b.getViewCount()));

          }
        }
        break;

      case "board/update":
        fields = request.getData().get(0).split(",");

        board = getBoard(Integer.parseInt(fields[0]));
        if(board == null) {
          throw new Exception("해당 번호의 게시글이 없습니다.");
        }

        board.setTitle(fields[1]);
        board.setContent(fields[2]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;

      case "board/delete":
        no = Integer.parseInt(request.getData().get(0));
        board = getBoard(no);
        if (board == null) {
          throw new Exception("해당 번호의 게시글이 없습니다.");
        }
        list.remove(board);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Board getBoard(int boardNo) {
    for (Board b : list) {
      if(b.getNumber() == boardNo) {
        return b;
      }
    }
    return null;
  }
}
