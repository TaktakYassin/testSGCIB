package com.example.testSGCIB.service;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.model.MowerData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtractMowerInstructionsServiceTest {

    @Autowired
    private ExtractFileContentServiceImpl extractFileContentService;

    @Test(expected = FileNotFoundException.class)
    public void whenFileDoesntExist_throwInvalidFileContentException() throws IOException, InvalidFileContentException, MowerDirectionException {
        MultipartFile multipartFile = new MockMultipartFile("no_existing_file.txt", new FileInputStream("src/test/resources/no_existing_file.txt"));
        extractFileContentService.extractMowersData(multipartFile);
    }
    @Test(expected = InvalidFileContentException.class)
    public void whenFileIsEmpty_throwInvalidFileContentException() throws IOException, InvalidFileContentException, MowerDirectionException {
        MultipartFile multipartFile = new MockMultipartFile("empty_file.txt", new FileInputStream("src/test/resources/empty_file.txt"));
        extractFileContentService.extractMowersData(multipartFile);
    }


    @Test(expected = InvalidFileContentException.class)
    public void whenDataIsMissing_throwInvalidFileContentException() throws IOException, InvalidFileContentException, MowerDirectionException {
        MultipartFile multipartFile = new MockMultipartFile("map_size_missing.txt", new FileInputStream("src/test/resources/map_size_missing.txt"));
        extractFileContentService.extractMowersData(multipartFile);
    }

    @Test(expected = MowerDirectionException.class)
    public void whenDirectionaIsWrong_throwMowerDirectionException() throws IOException, InvalidFileContentException, MowerDirectionException {
        MultipartFile multipartFile = new MockMultipartFile("wrong_direction.txt", new FileInputStream("src/test/resources/wrong_direction.txt"));
        extractFileContentService.extractMowersData(multipartFile);
    }

    @Test
    public void whenFileContentIsCorrect_expectExtractionIsSuccess() throws IOException, InvalidFileContentException, MowerDirectionException {
        MultipartFile multipartFile = new MockMultipartFile("data_correct.txt", new FileInputStream("src/test/resources/data_correct.txt"));
        MowerData mowerData = extractFileContentService.extractMowersData(multipartFile);
        assertEquals(mowerData.getMapSize(), new Point(5,5));
        assertEquals(mowerData.getMowersInstructions().get(0), "GAGAGAGAA");
        assertEquals(mowerData.getMowersInstructions().size(), 2);
        assertEquals(mowerData.getMowersPositionDirection().size(), 2);
    }



}