package com.github.avano.aoc.day01

import com.github.avano.aoc.support.AocSupport

class Day01 extends AocSupport {
	static int part1(List<String> input = inputFile(Day01.class, 'input')) {
		int zeroes = 0
		int current = 50

		for (final def line in input) {
			int num = (line.substring(1)) as int
			if (line[0] == 'R') {
				current = (current + num) % 100
			} else {
				current = 100 + (current - num) % 100
				if (current == 100) {
					current = 0
				}
			}
			if (current == 0) {
				zeroes++
			}
		}
		return zeroes
	}

	static int part2(List<String> input = inputFile(Day01.class, 'input')) {
		int zeroes = 0
		int current = 50

		for (final def line in input) {
			int num = (line.substring(1)) as int
			if (line[0] == 'L') {
				if (current >= num) {
					current -= num
				} else {
					if (current == 0) {
						zeroes--
					}
					int overflow = ((num - current) + 99).intdiv(100)
					zeroes += overflow
					current += 100 * overflow - num
				}
				if (current == 0) {
					zeroes++
				}
			} else {
				zeroes += (current + num) / 100
				current = (current + num) % 100
			}
		}
		return zeroes
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
