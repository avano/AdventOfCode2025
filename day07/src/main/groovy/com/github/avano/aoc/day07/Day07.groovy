package com.github.avano.aoc.day07

import com.github.avano.aoc.support.AocMap
import com.github.avano.aoc.support.AocSupport

class Day07 extends AocSupport {
	static final char START = 'S'
	static final char EMPTY = '.'
	static final char BEAM = '|'
	static final char SPLITTER = '^'

	static int part1(AocMap map = inputFileAs(Day07.class, AocMap.class)) {
		int x = (0..<map.columns()).find { map.get(it, 0) == START }
		traverse(map, x, 1)
	}

	static int traverse(AocMap map, int x, int y) {
		if (x < 0 || x == map.columns() || map.get(x, y) == BEAM) {
			return 0
		}

		// continue down through empty places
		while (y < map.rows() && map.get(x, y) == EMPTY) {
			map.set(x, y, BEAM)
			y++
		}

		if (y == map.rows() || map.get(x, y) == BEAM) {
			return 0
		} else {
			int splits = 0
			// if another splitter was found
			boolean leftEmpty = x - 1 >= 0 && map.get(x - 1, y) == EMPTY
			boolean rightEmpty = x + 1 < map.columns() && map.get(x + 1, y) == EMPTY
			if (leftEmpty) {
				splits += traverse(map, x - 1, y)
			}
			if (rightEmpty) {
				splits += traverse(map, x + 1, y)
			}
			if (leftEmpty || rightEmpty) {
				splits += 1
			}
			return splits
		}
	}

	static long count(AocMap input, int x, int y, Map<List<Integer>, Long> processed) {
		if (x < 0 || x >= input.columns()) {
			return 0
		}

		// continue down through empty places
		while (y < input.rows() && input.get(x, y) == EMPTY) {
			y++
		}

		// if we reached end of the map, there is only 1 path
		if (y == input.rows()) {
			return 1
		} else {
			// otherwise we reached another splitter, so the result will be the paths from that splitter
			return processed[[x, y]]
		}
	}

	static long part2(AocMap input = inputFileAs(Day07.class, AocMap.class)) {
		long result = 0

		Map<List<Integer>, Long> processed = [:]

		// go from bottom to top, counting all possible paths through each splitter
		for (int y = input.rows() - 1; y >= 0; y--) {
			for (int x = 0; x < input.columns(); x++) {
				if (input.get(x, y) == SPLITTER) {
					// the result will be the topmost splitter, so save the last processed splitter
					result = count(input, x - 1, y, processed) + count(input, x + 1, y, processed)
					processed[[x, y]] = result
				}
			}
		}

		return result
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
