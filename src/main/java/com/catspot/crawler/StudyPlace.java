package com.catspot.crawler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class StudyPlace {
    private final String link;
    private final Integer freeSeats;

    @Override
    public String toString() {
        return "link: " + link + ", freeSeats: " + freeSeats;
    }
}
