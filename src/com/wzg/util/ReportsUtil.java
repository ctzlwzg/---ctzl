package com.wzg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author lenovo 数据抓取核心类
 */

public class ReportsUtil{

	/**
	 * 获取网络源代码
	 * 
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getHtmlUrl(String url, String encoding) {
		StringBuffer sb = new StringBuffer();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader bf = null;
		// 建立网络连接
		URL urlObj;
		try {
			urlObj = new URL(url);
			URLConnection uc = urlObj.openConnection();
			is = uc.getInputStream();
			//转换流，并定义编码方式
			isr = new InputStreamReader(is, encoding);
			// 缓冲流
			bf = new BufferedReader(isr);
			// 创建临时文件
			String line = "";
			while ((line = bf.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	public static List<Map<String, Object>> findMessage(String url) {

		Map<String, Object> map = null;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		// 获取源代码
		String html = getHtmlUrl(url, "utf-8");
		// 解析源代码
		Document document = Jsoup.parse(html);
		// 获取div下class为category-area的盒子get(0)得到的是category-area下第一个div
		Element element = document.getElementsByClass("category-area").get(0);
		// 获取所有标签为a
		Elements elements = element.getElementsByTag("a");
		// 循环遍历
		for (Element element2 : elements) {
			map = new HashMap<String, Object>();
			String[] text = element2.text().split("\\(");
			String ct = text[0];
			String num = text[1].replaceAll("\\)", "");
			map.put(ct, num);
			//System.out.println(ct + "==>" + num+"家");
			list.add(map);
		}
		return list;
	}
}
