package com.catspot.crawler;

import com.catspot.studyplace.StudyPlace;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class LibraryCrawler {
    private final JsoupHelper jsoupHelper;
    private static final String baseUrl = "https://mlibrary.catholic.ac.kr/mobile/PA";
    private static final String tableUrl = "/roomStatusListXML.php";
    private static final String seatUrl = "/seatMap.php?roomNo=";

    public List<StudyPlace> getData() {
        List<StudyPlace> data = new ArrayList<>();
        try {
            Document table = jsoupHelper.getDocument(baseUrl + tableUrl);
            Elements rows = table.select("item");

            for (int i = 0; i < rows.size(); i ++) {
                Element row = rows.get(i);
                String name = row.select("strRoomNm").text();
                String url = baseUrl + seatUrl + row.select("strRoomNo").text();

                Integer allSeats = Integer.parseInt(row.select("strTotalSeat").text());
                Integer useSeats = Integer.parseInt(row.select("strUseSeat").text());
                Integer restSeats = Integer.parseInt(row.select("strRemainSeat").text());

                StudyPlace studyPlace = StudyPlace.builder()
                        .placeIdx((long) i)
                        .placeName(name)
                        .url(url)
                        .allSeats(allSeats)
                        .useSeats(useSeats)
                        .restSeats(restSeats)
                        .build();

                data.add(studyPlace);
            }
        } catch (Exception e) {
            log.error("크롤링에 실패했습니다.");
            data = new ArrayList<>();
        }
        return data;
    }
}
