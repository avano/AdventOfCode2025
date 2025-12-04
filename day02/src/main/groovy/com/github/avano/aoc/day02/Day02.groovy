package com.github.avano.aoc.day02

import com.github.avano.aoc.support.AocSupport

class Day02 extends AocSupport {
	static long part1(List<String> input = inputFileAs(delimiter: ',', Day02.class, List.class)) {
		long result = 0
		for (final def line in input) {
			def (left, right) = line.split('-')*.toString()

			// we can skip numbers with odd length, but we need to check if there are numbers with even length in the interval
			if (left.length() % 2 != 0) {
				// if 'to' is even, or is larger than 'from', then there are numbers with even length in between
				if (left.length() % 2 == 0 || right.length() > left.length()) {
					left = '1' + '0' * left.length()
				} else {
					// can skip the whole interval
					continue
				}
			}

			// take a half of the number and iterate over it creating the 'full' number by repeating the number twice
			long fromLong = left.substring(0, left.length().intdiv(2)) as long
			while (true) {
				long val = (String.valueOf(fromLong) * 2) as long
				fromLong++

				// skip this one as it's not in the interval
				if (val < (left as long)) {
					continue
				}

				if (val <= (right as long)) {
					result += val
				} else {
					// over the interval, so no point of trying larger numbers
					break
				}
			}
		}
		result
	}

	static long part2(List<String> input = inputFileAs(delimiter: ',', Day02.class, List.class)) {
		long result = 0
		Set<Long> dupes = []
		for (final def line in input) {
			def (left, right) = line.split('-')*.toString()

			// if the count of digits is not equal in start and end, split the count into two
			// for example: 95-115 -> 95-99 and 100-115
			if (left.length() < right.length()) {
				result += count(left, '9' * (left.length() + 1), dupes)
				result += count('1' + '0' * left.length(), right, dupes)
			} else {
				result += count(left, right, dupes)
			}
		}
		result
	}

	private static long count(String from, String to, Set<Long> dupes) {
		long result = 0
		// for the length of 8
		// we're looping from substrings of length 1..4 and skipping the length 3 as there is no multiplier to make it 8
		for (int substringLength = 1; substringLength <= from.length().intdiv(2); substringLength++) {
			if (from.length() % substringLength != 0) {
				continue
			}

			// create a loop from the number from first n digits of 'from' to the number from first n digits of 'to'
			// and create all possible 'invalid' IDs
			for (long l = from.substring(0, substringLength) as long; l <= (to.substring(0, substringLength) as long); l++) {
				long val = (String.valueOf(l) * from.length().intdiv(substringLength)) as long

				// skip this one as it's not in the interval
				if (val < (from as long)) {
					continue
				}
				if (val <= (to as long)) {
					if (!dupes.contains(val)) {
						result += val
						dupes.add(val)
					}
				} else {
					// over the interval, so no point of trying larger numbers
					break
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
