package com.github.avano.aoc.day05

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import com.github.avano.aoc.support.AocSupport

import java.util.stream.Stream

class Day05Test extends AocSupport {
	@ParameterizedTest
	@MethodSource('part1')
	void part1Test(List<String> input, int result) {
		assert new Day05().part1(input) == result
	}

	@ParameterizedTest
	@MethodSource('part2')
	void part2Test(List<String> input, int result) {
		assert new Day05().part2(input) == result
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				Arguments.of(testFileAs(List.class), 3)
		)
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				Arguments.of(testFileAs(List.class), 14),
				Arguments.of(['2-4', '0-6'], 7)
		)
	}
}
