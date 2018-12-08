package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6Part2 {
	static int MAX_BOARD_SIZE = 500;
	static int NUMBER_OF_COORDINATES = 50;
	static int MAX_SAFE_DISTANCE = 10000;
	static Path fileName = FileSystems.getDefault().getPath("day-6--input");
	static int[][] board = new int[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	static int[] xCoords = new int[NUMBER_OF_COORDINATES];
	static int[] yCoords = new int[NUMBER_OF_COORDINATES];

	public static void main(String[] args) {
		readFileAndInitBoard();
		int count = calculateSafeArea();
		System.out.println(count);
	}

	static void readFileAndInitBoard() {
		for(int i = 0; i < MAX_BOARD_SIZE; i++) {
			for (int j = 0; j < MAX_BOARD_SIZE; j++) {
				board[i][j] = -1;
			}
		}

		try {
			BufferedReader reader = Files.newBufferedReader(fileName, Charset.forName("US-ASCII"));
			String inputLine = null;
			int lineNumber = 0;
			while ((inputLine = reader.readLine()) != null) {
				String[] coordinate = inputLine.split(", ");
				int x = Integer.parseInt(coordinate[0]), y = Integer.parseInt(coordinate[1]);

				xCoords[lineNumber] = x;
				yCoords[lineNumber] = y;

				board[x][y] = lineNumber;

				lineNumber++;
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	static int calculateSafeArea() {
		int safeAreaSize = 0;

		for(int i = 0; i < MAX_BOARD_SIZE; i++) {
			for(int j = 0; j < MAX_BOARD_SIZE; j++) {
				int currentManhattanDistance = 0;
				for(int c = 0; c < NUMBER_OF_COORDINATES; c++) {
					currentManhattanDistance += getManhattanDistance(xCoords[c], yCoords[c], i, j);
				}
				if(currentManhattanDistance < MAX_SAFE_DISTANCE) {
					safeAreaSize++;
				}
			}
		}

		return safeAreaSize;
	}

	static int getManhattanDistance(int x0, int y0, int x1, int y1) {
		return Math.abs(x0 - x1) + Math.abs(y0 - y1);
	}

	static void printBoard() {
		for (int i = 0; i < MAX_BOARD_SIZE; i++) {
			for (int j = 0; j < MAX_BOARD_SIZE; j++)
				System.out.print(board[j][i] + "\t");
			System.out.println();
		}
	}
}
