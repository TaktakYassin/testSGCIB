package com.example.testSGCIB.controller;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.exception.MowerInstructionException;
import com.example.testSGCIB.model.MowerData;
import com.example.testSGCIB.service.ExtractFileContentService;
import com.example.testSGCIB.service.MowerPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("MowItNow")
public class MowController {

    @Autowired
    private MowerPositionService mowerPositionService;

    @Autowired
    private ExtractFileContentService extractFileContentService;

    @PostMapping(value="/calculatePositions",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<String> getFinalPositionsOfMachines(@RequestPart MultipartFile file) throws InvalidFileContentException, IOException, MowerDirectionException, MowerInstructionException {
        MowerData mowerData = extractFileContentService.extractMowersData(file);
        return mowerPositionService.calculatePositions(mowerData);
    }

}
