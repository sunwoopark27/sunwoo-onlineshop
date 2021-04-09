package com.sunwoo.project.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface MenuService {
  void menu(DataInputStream in, DataOutputStream out) throws Exception;
}
