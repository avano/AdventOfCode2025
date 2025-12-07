package com.github.avano.aoc.day04

import com.github.avano.aoc.support.AocMap
import com.github.avano.aoc.support.AocSupport

class Day04 extends AocSupport {
	static final char ROLL = '@'
	static final char EMPTY = '.'

	static int part1(AocMap map = inputFileAs(fileName: 'input', Day04.class, AocMap.class)) {
		int result = 0

		map.forEachCell { int x, int y ->
			if (map.get(x, y) == ROLL && map.adjacent8(x, y).findAll { it == ROLL }.size() < 4) {
				result++
			}
		}

		result
	}

	static int part2(AocMap map = inputFileAs(fileName: 'input', Day04.class, AocMap.class)) {
		int result = 0

		boolean changed = true
		while (changed) {
			changed = false
			map.forEachCell { int x, int y ->
				if (map.get(x, y) == ROLL && map.adjacent8(x, y).findAll { it == ROLL }.size() < 4) {
					result++
					map.set(x, y, EMPTY)
					changed = true
				}
			}
		}

		result
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
