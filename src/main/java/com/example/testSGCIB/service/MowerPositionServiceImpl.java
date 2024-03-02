package com.example.testSGCIB.service;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.exception.MowerInstructionException;
import com.example.testSGCIB.model.Mower;
import com.example.testSGCIB.model.MowerData;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MowerPositionServiceImpl implements MowerPositionService {

    @Override
    public List<String> calculatePositions(MowerData mowerData) throws InvalidFileContentException, MowerDirectionException, MowerInstructionException {
        if(mowerData!=null && mowerData.getMapSize()!=null && !mowerData.getMowersInstructions().isEmpty() && !mowerData.getMowersPositionDirection().isEmpty()) {
            List<Mower> mowersFinalPositions = new ArrayList<>();
            List<Mower> mowers = mowerData.getMowersPositionDirection();
            List<String> mowersInstructions = mowerData.getMowersInstructions();
            Point mapSize = mowerData.getMapSize();

            for (int i = 0; i < mowerData.getMowersPositionDirection().size(); i++)
                calculateMowerFinalPosition(mowers.get(i), mowersInstructions.get(i), mapSize, mowersFinalPositions);

            return mowers.stream().map(Mower::getMowerPosition).collect(Collectors.toList());
        }
        else
            throw new InvalidFileContentException();
    }

    //this method calculate the final position for each mower respecting the list of instructions provided
    private void calculateMowerFinalPosition(Mower mower,String instructions,Point mapSize,List<Mower> mowersFinalPositions) throws MowerInstructionException, MowerDirectionException {
        for (Character c:instructions.toCharArray()) {
            switch (c) {
                case 'D' -> mower.switchDirection(false);
                case 'G' -> mower.switchDirection(true);
                case 'A' -> mower.advanceMower(mapSize,mowersFinalPositions.stream().map(Mower::getPosition).collect(Collectors.toList()));
                default -> throw new MowerInstructionException();
            }
        }
        mowersFinalPositions.add(mower);
    }


}
