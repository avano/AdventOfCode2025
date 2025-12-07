package com.github.avano.aoc.support

class AocMap {
	private char[][] array

	AocMap(String input) {
		String[] lines = input.split('\n')
		array = new char[lines.length][lines[0].length()]

		for (int i = 0; i < lines.length; i++) {
			array[i] = lines[i].collect()
		}
	}

	char get(int x, int y) {
		array[y][x]
	}

	void set(int x, int y, char value) {
		array[y][x] = value as char
	}

	String toString() {
		StringBuilder sb = new StringBuilder()
		for (int y = 0; y < array.length; y++) {
			sb.append((array[y] as List).join('')).append("\n")
		}
		return sb.toString().trim()
	}

	void forEachCell(Closure body) {
		for (int y = 0; y < array.length; y++) {
			for (int x = 0; x < array[0].length; x++) {
				body(x, y)
			}
		}
	}

	List<Character> adjacent8(int x, int y) {
		List<Character> result = []

		for (final List<Integer> coordinates in [
				[-1, -1], [0, -1], [1, -1],
				[-1, 0], [1, 0],
				[-1, 1], [0, 1], [1, 1]
		]) {
			int x1 = x + coordinates[0]
			int y1 = y + coordinates[1]
			if (x1 >= 0 && x1 < array[0].length && y1 >= 0 && y1 < array.length) {
				result.add(get(x1, y1))
			}
		}

		result
	}

	int rows() {
		array.length
	}

	int columns() {
		array[0].length
	}
}
