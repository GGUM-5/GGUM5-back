package com.catspot.crawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudyPlaceTest {
    @Test
    void 열람실_엔티티_생성() {
        StudyPlace studyPlace = new StudyPlace("link", 1);

        Assertions.assertEquals(studyPlace.getLink(), "link");
        Assertions.assertEquals(studyPlace.getFreeSeats(), 1);
    }
}
