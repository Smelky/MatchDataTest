package com.test.service.interfaces;

import com.test.entity.MatchData;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchDataService {

    List<MatchData> findMatchData(String liveStatus, String statusType, Pageable pageable);

}
