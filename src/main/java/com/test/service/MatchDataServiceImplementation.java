package com.test.service;

import com.test.entity.MatchData;
import com.test.lib.datafilter.MatchDataFilter;
import com.test.repository.MatchDataRepository;
import com.test.service.interfaces.MatchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchDataServiceImplementation implements MatchDataService {

    private final MatchDataRepository matchDataRepository;

    @Autowired
    public MatchDataServiceImplementation(MatchDataRepository matchDataRepository) {
        this.matchDataRepository = matchDataRepository;
    }

    @Override
    public List<MatchData> findRecords(String liveStatus, String statusType, Pageable pageable) {
        MatchDataFilter matchDataFilter = new MatchDataFilter();
        matchDataFilter.setLiveStatus(liveStatus);
        matchDataFilter.setStatusType(statusType);
        Page<MatchData> matchDataPage = matchDataRepository.findAll(matchDataFilter, pageable);
        return matchDataPage.getContent();
    }
}
