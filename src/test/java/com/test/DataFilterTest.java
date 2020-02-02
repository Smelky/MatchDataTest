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
    private TestEntityManager entityManager;

    @Autowired
    private MatchDataRepository recordRepository;

    @Before
    public void loadData(){
        for (int i = 0; i < 2; i++) {
            MatchData matchData = new MatchData();
            matchData.setId("id" + i);
            matchData.setLiveStatus("inprogress");
            Status status = new Status();
            status.setType("HT");
            matchData.setStatus(status);
            entityManager.persist(matchData);
        }

        for (int i = 0; i < 4; i++) {
            MatchData matchData = new MatchData();
            matchData.setId("id" + i + 10);
            matchData.setLiveStatus("cancelled");
            Status status = new Status();
            status.setType("FT");
            matchData.setStatus(status);
            entityManager.persist(matchData);
        }

        for (int i = 0; i < 5; i++) {
            MatchData matchData = new MatchData();
            matchData.setId("id" + i + 20);
            matchData.setLiveStatus("inprogress");
            Status status = new Status();
            status.setType("FT");
            matchData.setStatus(status);
            entityManager.persist(matchData);
        }

        entityManager.flush();
    }

    @Test
    public void onlyInprogressRecordsShouldBeReturned(){
        MatchDataFilter liveStatusOnlySpecification = new MatchDataFilter();
        liveStatusOnlySpecification.setLiveStatus("inprogress");

        Page<MatchData> liveStatusRecords = recordRepository.findAll(liveStatusOnlySpecification, Pageable.unpaged());

        assertEquals(7, liveStatusRecords.getTotalElements());

    }

    @Test
    public void onlyFTStatusTypeRecordsShouldBeReturned(){
        MatchDataFilter statusTypeSpecification = new MatchDataFilter();
        statusTypeSpecification.setStatusType("FT");

        Page<MatchData> statusTypeRecords = recordRepository.findAll(statusTypeSpecification, Pageable.unpaged());

        assertEquals(9, statusTypeRecords.getTotalElements());
    }

    @Test
    public void onlyFTAndInprogressRecordsShouldBeReturned(){
        MatchDataFilter matchDataFilter = new MatchDataFilter();
        matchDataFilter.setStatusType("FT");
        matchDataFilter.setLiveStatus("inprogress");

        Page<MatchData> records = recordRepository.findAll(matchDataFilter, Pageable.unpaged());

        assertEquals(5, records.getTotalElements());
    }
}