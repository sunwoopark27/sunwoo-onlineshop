package com.sunwoo.project.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import com.sunwoo.project.ClientApp;
import com.sunwoo.util.Prompt;

public class BoardChooseHandler implements MenuService {
  static BoardServiceProduct boardServiceProduct = new BoardServiceProduct();
  static BoardServiceReview boardServiceReview = new BoardServiceReview();
  static BoardServiceShipping boardServiceShipping = new BoardServiceShipping();
  static BoardServiceExchangeReturn boardServiceExchangeReturn = new BoardServiceExchangeReturn();

  @Override
  public void menu(DataInputStream in, DataOutputStream out) throws Exception {

    HashMap<String,MenuService> boardMap = new HashMap<>();

    boardMap.put("0", new ClientApp("localhost", 8888));
    boardMap.put("1", new BoardServiceProduct());

    while(true) {
      System.out.println("[메인 > 게시판]");
      System.out.println("1. 상품 문의");
      System.out.println("2. 배송 문의");
      System.out.println("3. 교환/반품 문의");
      System.out.println("4. 리뷰");
      System.out.println("0. 이전 메뉴");
      System.out.println();

      String command = Prompt.inputString("명령> ");
      System.out.println();

      MenuService boardMenuHandler = boardMap.get(command);
      if(boardMenuHandler == null) {
        System.out.println("실행할 수 없는 메뉴 번호 입니다.");
      }else {
        boardMenuHandler.menu(in,out);
      }

      //      else if(boardMenuHandler.equals() {
      //
      //      } 

      System.out.println();
    }
  }
}
