package com.example.testSGCIB.service;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.model.Mower;
import com.example.testSGCIB.model.MowerData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Service
public class ExtractFileContentServiceImpl implements ExtractFileContentService {
    @Override
    public MowerData extractMowersData(MultipartFile file) throws InvalidFileContentException, IOException, MowerDirectionException {

        if(file!=null && !file.isEmpty())
        {
            MowerData mowerData = new MowerData();
            List<String> fileContent = Arrays.asList(new String(file.getBytes(), StandardCharsets.UTF_8).split("\n"));
            String[] firstLine = fileContent.get(0).trim().split(" ");
            if(isNumeric(firstLine[0])&& isNumeric(firstLine[1])) {
                Point mapSize = new Point(Integer.parseInt(firstLine[0]), Integer.parseInt(firstLine[1]));
                List<Mower> mowersPositionDirection = new ArrayList<>();
                List<String> mowersInstructions = new ArrayList<>();
                String[] data;
                for (int i = 1; i < fileContent.size(); i += 2) {
                    data = fileContent.get(i).trim().split(" ");
                    if (data.length == 3 && isNumeric(data[0]) && isNumeric(data[1]))
                        mowersPositionDirection.add(new Mower(data));
                    else
                        throw new InvalidFileContentException();
                    mowersInstructions.add(fileContent.get(i + 1).trim());
                }
                mowerData.setMapSize(mapSize);
                mowerData.setMowersInstructions(mowersInstructions);
                mowerData.setMowersPositionDirection(mowersPositionDirection);
                return mowerData;
            }
            else
                throw new InvalidFileContentException();
        }
        else
            throw new InvalidFileContentException();
    }
}
