package com.sunwoo.project.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.sunwoo.project.domain.Board;
import com.sunwoo.util.Prompt;

public class BoardAddHandler implements Command {

  @Override
  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    System.out.println("[새 게시글]");

    Board b = new Board();

    b.setTitle(Prompt.inputString("제목: "));
    b.setContent(Prompt.inputString("내용: "));
    b.setWriter(Prompt.inputString("작성자: "));

    out.writeUTF("board/insert");
    out.writeInt(1);
    out.writeUTF(String.format("%s, %s, %s", b.getTitle(), b.getContent(), b.getWriter()));
    out.flush();

    String status = in.readUTF();
    in.readInt();

    if (status.equals("error")) {
      System.out.println(in.readUTF());
      return;
    }
    System.out.println("게시글을 등록하겠습니다.");
    System.out.println();
  }
}
