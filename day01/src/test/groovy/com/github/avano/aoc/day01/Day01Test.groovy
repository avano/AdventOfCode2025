package com.github.avano.aoc.day01

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import com.github.avano.aoc.support.AocTestSupport

import java.util.stream.Stream

class Day01Test extends AocTestSupport {
	@ParameterizedTest
	@MethodSource('part1')
	void part1Test(List<String> input, int result) {
		assert new Day01().part1(input) == result
	}

	@ParameterizedTest
	@MethodSource('part2')
	void part2Test(List<String> input, int result) {
		assert new Day01().part2(input) == result
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				Arguments.of(testFile('example'), 3)
		)
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				Arguments.of(['L90'], 1),
				Arguments.of(['L190'], 2),
				Arguments.of(['L50'], 1),
				Arguments.of(['L150'], 2),
				Arguments.of(['R50'], 1),
				Arguments.of(['R150'], 2),
				Arguments.of(['L50', 'R100'], 2),
				Arguments.of(['L50'], 1),
				Arguments.of(['L50', 'L1'], 1),
				Arguments.of(['L51', 'R1'], 2),
				Arguments.of(testFile('example'), 6)
		)
	}
}
