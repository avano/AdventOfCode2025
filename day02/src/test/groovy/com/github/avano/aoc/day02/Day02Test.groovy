package com.github.avano.aoc.day02

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import com.github.avano.aoc.support.AocSupport

import java.util.stream.Stream

class Day02Test extends AocSupport {
	@ParameterizedTest
	@MethodSource('part1')
	void part1Test(List<String> input, long result) {
		assert new Day02().part1(input) == result
	}

	@ParameterizedTest
	@MethodSource('part2')
	void part2Test(List<String> input, long result) {
		assert new Day02().part2(input) == result
	}

	static Stream<Arguments> part1() {
		return Stream.of(
				Arguments.of(testFileAs(delimiter: ',', List.class), 1227775554)
		)
	}

	static Stream<Arguments> part2() {
		return Stream.of(
				Arguments.of(testFileAs(delimiter: ',', List.class), 4174379265)
		)
	}
}
