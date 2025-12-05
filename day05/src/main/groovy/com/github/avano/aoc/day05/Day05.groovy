package com.github.avano.aoc.day05

import com.github.avano.aoc.support.AocSupport

class Day05 extends AocSupport {
	static int part1(List<String> input = inputFileAs(Day05.class, List.class)) {
		// take elements until an empty line and convert them to List<Long>
		List<List<Long>> intervals = input.takeWhile { it }.collect { it.split("-")*.toLong() }
		// take the remaining elements and convert them to long
		List<Long> numbers = input.drop(intervals.size() + 1)*.toLong()

		// count the number that are in any interval
		numbers.count { n -> intervals.any { i -> n >= i[0] && n <= i[1] } }
	}

	static long part2(List<String> input = inputFileAs(Day05.class, List.class)) {
		long result = 0

		List<List<Long>> intervals = []
		def strings = new ArrayDeque<>(input.takeWhile { it })

		while (!strings.isEmpty()) {
			def (left, right) = strings.poll().split('-')*.toLong()

			// if we should count this interval into the result
			boolean skip = false
			for (final def i in intervals) {
				long iL = i[0], iR = i[1]
				if (left > iR || right < iL) {
					// no overlap
					continue
				}

				skip = true
				// if the left value is in this interval
				if (left >= iL && left <= iR) {
					// add the remainder of this interval into the queue, if possible
					if (iR + 1 <= right) {
						strings.add("${iR + 1}-${right}")
					}
				}

				// if the right value is in this interval
				if (right >= iL && left <= iR) {
					// add the remainder of this interval into the queue, if possible
					if (left <= iL - 1) {
						strings.add("${left}-${iL - 1}")
					}
				}

				// if there is already an interval that is completely within this interval
				if (left <= iL && iR <= right) {
					// add the two remainders of this interval into the queue, if possible
					if (left <= iL - 1) {
						strings.add("${left}-${iL - 1}")
					}
					if (iR +1 <= right) {
						strings.add("${iR + 1}-${right}")
					}
				}
			}

			if (!skip) {
				result += right - left + 1
				intervals.add([left, right])
			}
		}

		result
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
