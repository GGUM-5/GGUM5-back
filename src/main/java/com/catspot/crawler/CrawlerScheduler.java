package com.catspot.crawler;

import com.catspot.studyplace.StudyPlace;
import com.catspot.studyplace.StudyPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CrawlerScheduler {
    private final LibraryCrawler libraryCrawler;
    private final StudyPlaceRepository studyPlaceRepository;

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void crawl() {
        List<StudyPlace> places = libraryCrawler.getData();
        List<StudyPlace> studyPlacesToSave = new ArrayList<>();

        if (places.isEmpty()) {
            studyPlaceRepository.deleteAll();
            return;
        }

        for (StudyPlace place : places) {
            StudyPlace studyPlace = studyPlaceRepository.findById(place.getPlaceIdx()).orElseGet(() -> place);

            studyPlace.setPlaceName(place.getPlaceName());
            studyPlace.setUrl(place.getUrl());
            studyPlace.setAllSeats(place.getAllSeats());
            studyPlace.setUseSeats(place.getUseSeats());
            studyPlace.setRestSeats(place.getRestSeats());

            studyPlacesToSave.add(studyPlace);
        }

        studyPlaceRepository.saveAll(studyPlacesToSave);
    }
}
