package com.test.controller;

import com.test.entity.MatchData;
import com.test.service.interfaces.MatchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matchdata")
public class MatchDataController {

    private final MatchDataService matchDataService;

    @Autowired
    public MatchDataController(MatchDataService matchDataService) {
        this.matchDataService = matchDataService;
    }

    @GetMapping
    public ResponseEntity<List<MatchData>> getMatchData(@RequestParam(required = false) String liveStatus,
                                                        @RequestParam(required = false) String statusType,
                                                        Pageable pageable) {

        List<MatchData> matchData = matchDataService.findRecords(liveStatus, statusType, pageable);
        return ResponseEntity.ok(matchData);
    }

}
