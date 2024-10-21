package com.catspot.crawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class LibraryCrawlerTest {
    @Test
    void 크롤링_페이지_정상동작() {
        // when
        HashMap<String, StudyPlace> map = LibraryCrawler.getData();

        // then
        System.out.println(map);
        Assertions.assertFalse(map.isEmpty());
    }
}
