package com.github.avano.aoc.day04

import com.github.avano.aoc.support.AocMap
import com.github.avano.aoc.support.AocSupport

class Day04 extends AocSupport {
	static int part1(AocMap input = inputFileAs(fileName: 'input', Day04.class, AocMap.class)) {
		int result = 0

		input.forEachCell { int x, int y ->
			if (input.get(x, y) == '@' as char && input.adjacent8(x, y).findAll { it == '@' }.size() < 4) {
				result++
			}
		}

		result
	}

	static int part2(AocMap input = inputFileAs(fileName: 'input', Day04.class, AocMap.class)) {
		int result = 0

		boolean changed = true
		while (changed) {
			changed = false
			input.forEachCell { int x, int y ->
				if (input.get(x, y) == '@' as char && input.adjacent8(x, y).findAll { it == '@' }.size() < 4) {
					result++
					input.set(x, y, '.')
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
