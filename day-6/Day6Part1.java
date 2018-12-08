package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day6Part1 {
	static int MAX_BOARD_SIZE = 500;
	static int NUMBER_OF_COORDINATES = 50;
	static Path fileName = FileSystems.getDefault().getPath("day-6--input");
	static int[][] board = new int[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	static int[] xCoords = new int[NUMBER_OF_COORDINATES];
	static int[] yCoords = new int[NUMBER_OF_COORDINATES];

	public static void main(String[] args) {
		readFileAndInitBoard();

		fillInManhattanDistance();
		removeOuterAreas();
		int count = calculateBiggestArea();
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

	static void fillInManhattanDistance() {
		for(int i = 0; i < MAX_BOARD_SIZE; i++) {
			for(int j = 0; j < MAX_BOARD_SIZE; j++) {

				// skip the input value spots
				int boardVal = board[i][j];
				if(boardVal >= 0 && xCoords[boardVal] == i && yCoords[boardVal] == j) {
					continue;
				}

				int minManhattanDistance = Integer.MAX_VALUE;

				for(int coordinate = 0; coordinate < NUMBER_OF_COORDINATES; coordinate++) {
					int currManhattanDistance = getManhattanDistance(xCoords[coordinate], yCoords[coordinate], i, j);
					if(minManhattanDistance > currManhattanDistance) {
						minManhattanDistance = currManhattanDistance;
						board[i][j] = coordinate;
					}
				}

				int count = 0;
				for(int coordinate = 0; coordinate < NUMBER_OF_COORDINATES; coordinate++) {
					int currManhattanDistance = getManhattanDistance(xCoords[coordinate], yCoords[coordinate], i, j);
					if(minManhattanDistance == currManhattanDistance) {
						count++;
					}
				}
				if(count > 1) {
					board[i][j] = -2;
				}
			}
		}
	}

	static void removeOuterAreas() {
		Set<Integer> outerAreas = new HashSet<>();
		for(int i = 0; i < MAX_BOARD_SIZE; i++) {
			outerAreas.add(board[0][i]);
			outerAreas.add(board[MAX_BOARD_SIZE-1][i]);
			outerAreas.add(board[i][0]);
			outerAreas.add(board[i][MAX_BOARD_SIZE-1]);
		}

		for(int i = 0; i < MAX_BOARD_SIZE; i++) {
			for(int j = 0; j < MAX_BOARD_SIZE; j++) {
				if(outerAreas.contains(board[i][j])) {
					board[i][j] = -1;
				}
			}
		}
	}

	static int calculateBiggestArea() {
		int[] counts = new int[NUMBER_OF_COORDINATES];

		for(int i = 0; i < MAX_BOARD_SIZE; i++) {
			for(int j = 0; j < MAX_BOARD_SIZE; j++) {
				int boardVal = board[i][j];

				if(boardVal > 0) {
					counts[boardVal]++;
				}
			}
		}

		return Arrays.stream(counts).max().getAsInt();
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
