package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day3Part1 {

	static Path fileName = FileSystems.getDefault().getPath("day-3--input");
	static String inputRegex = "[# @,:x]";			// input format example: "#1 @ 755,138: 26x19"
	static int[][] board = new int[1000][1000];

	public static void main(String[] args) {
		try {
			readAndProcessFile();
		} catch(IOException e) {
			System.err.println(e.toString());
		}

		int count = countOverlaps();
		System.out.println(count);
	}

	private static void readAndProcessFile() throws IOException {
		BufferedReader reader = Files.newBufferedReader(fileName, Charset.forName("US-ASCII"));
		String inputLine = null;
		while((inputLine = reader.readLine()) != null) {
			fillRectangleFromInputLine(inputLine);
		}
	}

	private static int countOverlaps() {
		int overlapCount = 0;

		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 1000; j++) {
				if(board[i][j] == 1)
					overlapCount++;
			}
		}

		return overlapCount;
	}

	private static void fillRectangleFromInputLine(String line) {
		String[] parsedLine = line.split(inputRegex);
		int x = Integer.parseInt(parsedLine[4]);
		int y = Integer.parseInt(parsedLine[5]);
		int width = Integer.parseInt(parsedLine[7]);
		int length = Integer.parseInt(parsedLine[8]);

		for(int i = x; i < x+width; i++) {
			for(int j = y; j < y+length; j++) {
				if(board[i][j] == 0) {
					board[i][j] = -1;
				}
				else if (board[i][j] == -1) {
					board[i][j] = 1;
				}
			}
		}
	}
}
