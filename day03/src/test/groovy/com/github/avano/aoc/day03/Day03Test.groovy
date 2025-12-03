package com.github.avano.aoc.day03

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import com.github.avano.aoc.support.AocSupport

import java.util.stream.Stream

class Day03Test extends AocSupport {
	@ParameterizedTest
	@MethodSource('part1')
	void part1Test(List<String> input, int result) {
		assert new Day03().part1(input) == result
	}

	@ParameterizedTest
	@MethodSource('part2')
	void part2Test(List<String> input, long result) {
		assert new Day03().part2(input) == result
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				Arguments.of(testFile('example'), 357)
		)
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				Arguments.of(testFile('example'), 3121910778619)
		)
	}
}
