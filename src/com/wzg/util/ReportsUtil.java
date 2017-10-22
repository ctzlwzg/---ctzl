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
 * @author lenovo ����ץȡ������
 */

public class ReportsUtil{

	/**
	 * ��ȡ����Դ����
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
		// ������������
		URL urlObj;
		try {
			urlObj = new URL(url);
			URLConnection uc = urlObj.openConnection();
			is = uc.getInputStream();
			//ת��������������뷽ʽ
			isr = new InputStreamReader(is, encoding);
			// ������
			bf = new BufferedReader(isr);
			// ������ʱ�ļ�
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
		
		// ��ȡԴ����
		String html = getHtmlUrl(url, "utf-8");
		// ����Դ����
		Document document = Jsoup.parse(html);
		// ��ȡdiv��classΪcategory-area�ĺ���get(0)�õ�����category-area�µ�һ��div
		Element element = document.getElementsByClass("category-area").get(0);
		// ��ȡ���б�ǩΪa
		Elements elements = element.getElementsByTag("a");
		// ѭ������
		for (Element element2 : elements) {
			map = new HashMap<String, Object>();
			String[] text = element2.text().split("\\(");
			String ct = text[0];
			String num = text[1].replaceAll("\\)", "");
			map.put(ct, num);
			//System.out.println(ct + "==>" + num+"��");
			list.add(map);
		}
		return list;
	}
}
