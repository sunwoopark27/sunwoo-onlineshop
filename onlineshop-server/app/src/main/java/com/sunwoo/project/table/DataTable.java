package com.sunwoo.project.table;

import com.sunwoo.util.Request;
import com.sunwoo.util.Response;

public interface DataTable {
  void service(Request request, Response response) throws Exception;
}
