package com.github.avano.aoc.day11

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import com.github.avano.aoc.support.AocSupport

import java.util.stream.Stream

class Day11Test extends AocSupport {
	@ParameterizedTest
	@MethodSource('part1')
	void part1Test(List<String> input, int result) {
		assert new Day11().part1(input) == result
	}

	@ParameterizedTest
	@MethodSource('part2')
	void part2Test(List<String> input, int result) {
		assert new Day11().part2(input) == result
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				Arguments.of(testFileAs(List.class), 5)
		)
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				Arguments.of(testFileAs(fileName: 'example2', List.class), 2)
		)
	}
}
