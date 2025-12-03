package com.github.avano.aoc.day03

import com.github.avano.aoc.support.AocSupport

class Day03 extends AocSupport {
	static int part1(List<String> input = inputFile(Day03.class, 'input')) {
		int first, second, result = 0
		for (final String line in input) {
			first = line[0] as int
			second = line[1] as int
			for (int i = 2; i < line.length(); i++) {
				int current = line[i] as int
				// if the second is greater than first, switching them and picking a new number is always bigger
				if (second > first) {
					first = second
					second = current
					// otherwise just check if the new number is greater than the second number
				} else if (current > second) {
					second = current
				}
			}
			result += first * 10 + second
		}
		result
	}

	static long part2(List<String> input = inputFile(Day03.class, 'input')) {
		long result = 0
		for (final String line in input) {
			int exponent = 11
			int index = 0
			int skipped = 0
			int picked = 0
			// in the example, we know that there are 15 numbers and we pick 12 - it means that we need to skip 3 numbers anywhere in the line
			// so we know that we need to pick the biggest number in the first '4' digits - this window shrinks for each already skipped number
			// and we repeat that process until we skipped 3 numbers or we picked 12 numbers
			while (skipped < (line.length() - 12) && picked < 12) {
				int max = -1
				int maxIndex = 0
				for (int i = index; i < index + line.length() - 12 + 1 - skipped; i++) {
					if (line[i] as int > max) {
						max = line[i] as int
						maxIndex = i
					}
				}
				skipped += maxIndex - index
				picked++
				index = maxIndex + 1
				(result, exponent) = add(result, max, exponent)
			}

			// add the remaining numbers, if any
			for (int i = index; i < line.length(); i++) {
				(result, exponent) = add(result, line[i] as int, exponent)
			}
		}
		result
	}

	static long[] add(long result, int element, int exponent) {
		[result + element * Math.pow(10, exponent), --exponent]
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
