package com.catspot.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;

public class LibraryCrawler {
    private static final String baseUrl = "http://203.229.203.240/8080";
    private static final String tableUrl = "/Domian5_jythh.asp";
    private static HashMap<String, StudyPlace> data = new HashMap<>();

    public static HashMap<String, StudyPlace> getData() {
        data = new HashMap<>();
        try {
            Document document = Jsoup.connect(baseUrl + tableUrl).get();
            Elements rows = document.select("tbody tr");

            for (int i = 0; i < rows.size(); i ++) {
                Element row = rows.get(i);
                String section = row.select("a").text();
                String url = baseUrl + "/" + row.select("a").attr("href");
                Integer freeSeat = Integer.parseInt(row.select("font").get(2).text());
                StudyPlace library = new StudyPlace(url, freeSeat);
                data.put(section, library);
            }

            if (data.isEmpty()) throw new IOException();
            System.out.println("크롤링 성공 : 데이터 개수 " + data.size());
        } catch (IOException e) {
            System.out.println("에러 발생");
        }
        return data;
    }
}
