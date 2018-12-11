package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Part2 {

	static Path fileName = FileSystems.getDefault().getPath("day-3--input");
	static String inputRegex = "[# @,:x]";			// input format example: "#1 @ 755,138: 26x19"
	static int[][] board = new int[1000][1000];
	static List<Integer> overlappingClaims = IntStream.rangeClosed(1,1307).boxed().collect(Collectors.toList());	// hard-coded range end

	public static void main(String[] args) {
		try {
			readAndProcessFile();
		} catch(IOException e) {
			System.err.println(e.toString());
		}

		System.out.println(overlappingClaims.get(0));	// hard-coded retrieve the only remaining entry
	}

	private static void readAndProcessFile() throws IOException {
		BufferedReader reader = Files.newBufferedReader(fileName, Charset.forName("US-ASCII"));
		String inputLine = null;
		while((inputLine = reader.readLine()) != null) {
			fillRectangleFromInputLine(inputLine);
		}
	}

	private static void fillRectangleFromInputLine(String line) {
		String[] parsedLine = line.split(inputRegex);
		int claim = Integer.parseInt(parsedLine[1]);
		int x = Integer.parseInt(parsedLine[4]);
		int y = Integer.parseInt(parsedLine[5]);
		int width = Integer.parseInt(parsedLine[7]);
		int length = Integer.parseInt(parsedLine[8]);

		for(int i = x; i < x+width; i++) {
			for(int j = y; j < y+length; j++) {
				if(board[i][j] == 0) {
					board[i][j] = claim;
				}
				else {
					Integer existingClaim = Integer.valueOf(board[i][j]);
					Integer currentClaim = Integer.valueOf(claim);
					overlappingClaims.remove(existingClaim);
					overlappingClaims.remove(currentClaim);
				}
			}
		}
	}
}
