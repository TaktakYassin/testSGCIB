package com.example.testSGCIB.service;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.model.MowerData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExtractFileContentService {

    MowerData extractMowersData (MultipartFile file) throws InvalidFileContentException, IOException, MowerDirectionException;
}
