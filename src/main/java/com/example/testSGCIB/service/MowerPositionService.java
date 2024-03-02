package com.example.testSGCIB.service;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.exception.MowerInstructionException;
import com.example.testSGCIB.model.MowerData;

import java.util.List;

public interface MowerPositionService {
    List<String> calculatePositions(MowerData mowerData) throws InvalidFileContentException, MowerDirectionException, MowerInstructionException;
}
