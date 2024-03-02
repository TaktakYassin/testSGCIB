package com.example.testSGCIB;

import com.example.testSGCIB.exception.InvalidFileContentException;
import com.example.testSGCIB.exception.MowerDirectionException;
import com.example.testSGCIB.exception.MowerInstructionException;
import com.example.testSGCIB.model.MowerData;
import com.example.testSGCIB.service.ExtractFileContentServiceImpl;
import com.example.testSGCIB.service.MowerPositionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSGCIBApplicationTests {

	@Autowired
	private ExtractFileContentServiceImpl extractFileContentService;
	@Autowired
	private MowerPositionServiceImpl mowerPositionService;

	@Test(expected = InvalidFileContentException.class)
	public void whenFileIsEmpty_throwInvalidFileContentException() throws IOException, InvalidFileContentException, MowerDirectionException, MowerInstructionException {
		MultipartFile multipartFile = new MockMultipartFile("empty_file.txt", new FileInputStream("src/test/resources/empty_file.txt"));
		MowerData mowerData = extractFileContentService.extractMowersData(multipartFile);
		mowerPositionService.calculatePositions(mowerData);
	}
	@Test
	public void whenDataIsCorrect_expectResult() throws IOException, InvalidFileContentException, MowerDirectionException, MowerInstructionException {
		MultipartFile multipartFile = new MockMultipartFile("data_correct.txt", new FileInputStream("src/test/resources/data_correct.txt"));
		MowerData mowerData = extractFileContentService.extractMowersData(multipartFile);
		List<String> result = mowerPositionService.calculatePositions(mowerData);
		List<String> expected = Arrays.asList("1 3 N","5 1 E");
		assertEquals(expected, result);
	}

	@Test
	public void whenMowIsBlocking_expectResult() throws IOException, InvalidFileContentException, MowerDirectionException, MowerInstructionException {
		MultipartFile multipartFile = new MockMultipartFile("mow_blocking_position.txt", new FileInputStream("src/test/resources/mow_blocking_position.txt"));
		MowerData mowerData = extractFileContentService.extractMowersData(multipartFile);
		List<String> result = mowerPositionService.calculatePositions(mowerData);
		List<String> expected = Arrays.asList("1 3 N","5 1 E","4 1 E","1 2 N");
		assertEquals(expected, result);
	}



}
