package com.github.avano.aoc.day06

import com.github.avano.aoc.support.AocSupport

class Day06 extends AocSupport {
	static long part1(List<String> input = inputFileAs(Day06.class, List.class)) {
		long result = 0
		List<List<Integer>> numbers = parseNumbers(input)

		def operands = input.last().replaceAll(" +", " ").trim().split(" ")
		operands.eachWithIndex { operand, int column ->
			List<Integer> columnNumbers = numbers.collect { it[column] }
			result += operand == '*' ? columnNumbers.inject(1, { a, b -> a * b }) : columnNumbers.sum()
		}
		result
	}

	static long part2(List<String> input = inputFileAs(Day06.class, List.class)) {
		long result = 0
		// get a max value for each column, so that we can pad the string representation of the number
		int[] maxValues = parseNumbers(input).transpose().collect { List<Integer> column -> column.max() }

		// reconstruct the input where all numbers are padded - when there's an empty space in the number, it is subsituted with 'x'
		List<List<String>> strings = []

		input.init().each { line ->
			strings.add([])
			String currentNumber = ''
			// in which column are we right now
			int column = 0

			// for each character in the line
			for (int j = 0; j < line.length(); j++) {
				// if it is not space, just add it
				if (line[j] != ' ') {
					currentNumber += line[j]
				} else {
					// otherwise - if we don't have enough digits in the number, this empty place is substituted with 'x'
					if (currentNumber.size() != (maxValues[column] as String).length()) {
						currentNumber += 'x'
					} else {
						strings.last().add(currentNumber)
						currentNumber = ''
						column++
					}
				}
			}

			// at EOL, we might not finish the last number
			if (currentNumber.length() != (maxValues[column] as String).length()) {
				currentNumber += 'x'
			}
			strings.last().add(currentNumber)
		}

		def operands = input.last().replaceAll(" +", " ").trim().split(" ")
		operands.eachWithIndex { operand, int column ->
			long currentResult = operand == '*' ? 1 : 0
			int digitCount = (maxValues[column] as String).length()
			for (int digitColumn = digitCount - 1; digitColumn >= 0; digitColumn--) {
				currentResult = operand == '*' ? currentResult * toNumber(strings, column, digitColumn) : currentResult + toNumber(strings, column, digitColumn)
			}
			result += currentResult
		}
		result
	}

	static List<List<Integer>> parseNumbers(List<String> input) {
		input.init().collect { line ->
			line.replaceAll(' +', ' ').trim().split(' ')*.toInteger()
		}
	}

	private static int toNumber(List<List<String>> strings, int column, int digit) {
		String num = ''
		// for each line, add a string representation of a number for the given column and given digit
		strings.each { line ->
			// skip if it is 'x'
			num += line[column][digit] != 'x' ? line[column][digit] : ''
		}
		num as int
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
