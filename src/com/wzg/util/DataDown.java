package com.wzg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataDown
 */
@WebServlet("/dataDown")
public class DataDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String address = request.getParameter("address");
		List<Map<String,Object>> findMessage1 = ReportsUtil.findMessage(address);
		System.out.println(findMessage1);
		Map<String, Object> map = null;
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		for (int i = 0; i < findMessage1.size(); i++) {
			map = new HashMap<String, Object>();
			map = findMessage1.get(i);
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()){
				String k1=iterator.next();
				key.add(k1);
				value.add((String) map.get(k1));
			}
		}
		request.setAttribute("key", key);
		request.setAttribute("value", value);
		request.getRequestDispatcher("/a.jsp").forward(request, response);
		
	}

}
