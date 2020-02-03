package com.test;

import com.test.entity.MatchData;
import com.test.entity.Status;
import com.test.lib.datafilter.MatchDataFilter;
import com.test.repository.MatchDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DataFilterTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MatchDataRepository matchDataRepository;

    @Before
    public void updateTestData() {

        for (int i = 0; i < 4; i++) {
            MatchData matchData = new MatchData();
            matchData.setId("id" + i);
            matchData.setLiveStatus("cancelled");
            Status status = new Status();
            status.setType("FT");
            matchData.setStatus(status);
            testEntityManager.persist(matchData);
        }

        for (int i = 0; i < 6; i++) {
            MatchData matchData = new MatchData();
            matchData.setId("id" + i + 7);
            matchData.setLiveStatus("inprogress");
            Status status = new Status();
            status.setType("HT");
            matchData.setStatus(status);
            testEntityManager.persist(matchData);
        }

        for (int i = 0; i < 3; i++) {
            MatchData matchData = new MatchData();
            matchData.setId("id" + i + 15);
            matchData.setLiveStatus("inprogress");
            Status status = new Status();
            status.setType("FT");
            matchData.setStatus(status);
            testEntityManager.persist(matchData);
        }
        testEntityManager.flush();
    }

    @Test
    public void ShouldGetOnlyFTAndInprogressMatchData() {
        MatchDataFilter matchDataFilterFTAndInprogressOnly = new MatchDataFilter();
        matchDataFilterFTAndInprogressOnly.setStatusType("FT");
        matchDataFilterFTAndInprogressOnly.setLiveStatus("inprogress");
        Page<MatchData> FTAndInprogressMatchData = matchDataRepository.findAll(matchDataFilterFTAndInprogressOnly, Pageable.unpaged());
        assertEquals(3, FTAndInprogressMatchData.getTotalElements());
    }

    @Test
    public void ShouldGetOnlyFTMatchData() {
        MatchDataFilter matchDataFilterStatusTypeOnly = new MatchDataFilter();
        matchDataFilterStatusTypeOnly.setStatusType("FT");
        Page<MatchData> StatusTypeMatchData = matchDataRepository.findAll(matchDataFilterStatusTypeOnly, Pageable.unpaged());
        assertEquals(7, StatusTypeMatchData.getTotalElements());
    }

    @Test
    public void ShouldGetOnlyInprogressMatchData() {
        MatchDataFilter matchDataFilterLiveStatusOnly = new MatchDataFilter();
        matchDataFilterLiveStatusOnly.setLiveStatus("inprogress");
        Page<MatchData> liveStatusMatchData = matchDataRepository.findAll(matchDataFilterLiveStatusOnly, Pageable.unpaged());
        assertEquals(9, liveStatusMatchData.getTotalElements());
    }
}