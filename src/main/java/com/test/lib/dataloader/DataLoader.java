package com.test.lib.dataloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.MatchData;
import com.test.lib.exceptions.DataLoadFromFileException;
import com.test.repository.MatchDataRepository;
import com.test.util.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataLoader {

    private final MatchDataRepository matchDataRepository;
    private final AppProperties appProperties;

    @Autowired
    public DataLoader(MatchDataRepository matchDataRepository, AppProperties appProperties) {
        this.matchDataRepository = matchDataRepository;
        this.appProperties = appProperties;
    }

    @PostConstruct
    private void updateDatabase() throws DataLoadFromFileException {
        long count = matchDataRepository.count();
        if (count > 0)
            return;

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();

        String json;
        try {
            json = getDataFromFileAsString(appProperties.getFileName());
        } catch (IOException e) {
            throw new DataLoadFromFileException("FilePath in application.properties file is wrong", e);
        }

        try {
            List<MatchData> matchDataList = objectMapper.readValue(json, new TypeReference<List<MatchData>>(){});
            matchDataRepository.saveAll(matchDataList);
        } catch (IOException e) {
            throw new DataLoadFromFileException("Parse Json form file error. Pleas check file format and filePath", e);
        }
    }

    private String getDataFromFileAsString(String fileName) throws IOException {
        try (InputStream inputStream = TypeReference.class.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                return null;
            }
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
