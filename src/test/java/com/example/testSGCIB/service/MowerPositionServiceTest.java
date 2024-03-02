package com.example.testSGCIB.service;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.exception.MowerInstructionException;
import com.example.testSGCIB.model.Mower;
import com.example.testSGCIB.model.MowerData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MowerPositionServiceTest {

    @Autowired
    private MowerPositionServiceImpl mowerPositionService;

    @Test(expected = InvalidFileContentException.class)
    public void whenDataIsEmpty_throwInvalidFileContentException() throws InvalidFileContentException, MowerDirectionException, MowerInstructionException {
        MowerData mowerData = new MowerData();
        mowerPositionService.calculatePositions(mowerData);
    }

    @Test
    public void whenDataIsCorrect_expectResult() throws InvalidFileContentException, MowerDirectionException, MowerInstructionException {
        MowerData mowerData = new MowerData();
        mowerData.setMapSize(new Point(5,5));
        mowerData.setMowersInstructions(Arrays.asList("GAGAGAGAA","AADAADADDA"));
        mowerData.setMowersPositionDirection(Arrays.asList(new Mower("1 2 N".split(" ")),new Mower("3 3 E".split(" "))));
        List<String> result = mowerPositionService.calculatePositions(mowerData);
        List<String> expected = Arrays.asList("1 3 N","5 1 E");
        assertEquals(expected, result);
    }

    @Test(expected = MowerInstructionException.class)
    public void whenInstructionIsWrong_throwMowerInstructionException() throws InvalidFileContentException, MowerDirectionException, MowerInstructionException {
        MowerData mowerData = new MowerData();
        mowerData.setMapSize(new Point(5,5));
        mowerData.setMowersInstructions(Arrays.asList("GAFAGAGAA","AADAADADDA"));
        mowerData.setMowersPositionDirection(Arrays.asList(new Mower("1 2 N".split(" ")),new Mower("3 3 E".split(" "))));
        mowerPositionService.calculatePositions(mowerData);
    }

    @Test(expected = MowerDirectionException.class)
    public void whenDirectionIsWrong_throwMowerInstructionException() throws InvalidFileContentException, MowerDirectionException, MowerInstructionException {
        MowerData mowerData = new MowerData();
        mowerData.setMapSize(new Point(5,5));
        mowerData.setMowersInstructions(Arrays.asList("GAFAGAGAA","AADAADADDA"));
        mowerData.setMowersPositionDirection(Arrays.asList(new Mower("1 2 K".split(" ")),new Mower("3 3 E".split(" "))));
        mowerPositionService.calculatePositions(mowerData);
    }

}