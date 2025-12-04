package com.github.avano.aoc.day04

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import com.github.avano.aoc.support.AocMap
import com.github.avano.aoc.support.AocSupport

import java.util.stream.Stream

class Day04Test extends AocSupport {
	@ParameterizedTest
	@MethodSource('part1')
	void part1Test(AocMap input, int result) {
		assert new Day04().part1(input) == result
	}

	@ParameterizedTest
	@MethodSource('part2')
	void part2Test(AocMap input, int result) {
		assert new Day04().part2(input) == result
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				Arguments.of(testFileAs(AocMap.class), 13)
		)
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				Arguments.of(testFileAs(AocMap.class), 43)
		)
	}
}
