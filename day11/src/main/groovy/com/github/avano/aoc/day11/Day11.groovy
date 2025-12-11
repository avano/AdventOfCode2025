package com.github.avano.aoc.day11

import com.github.avano.aoc.support.AocSupport

class Day11 extends AocSupport {
	static int part1(List<String> input = inputFileAs(Day11.class, List.class)) {
		int result = 0
		Map<String, List<String>> paths = paths(input)

		Queue<String> queue = ['you'] as ArrayDeque

		while (queue) {
			String current = queue.poll()
			if (current == 'out') {
				result++
				continue
			}
			queue.addAll(paths[current])
		}

		result
	}

	static long part2(List<String> input = inputFileAs(Day11.class, List.class)) {
		Map<String, List<String>> paths = paths(input)
		count('svr', 'fft', [:], paths) * count('fft', 'dac', [:], paths) * count('dac', 'out', [:], paths)
	}

	static long count(String from, String to, Map<String, Long> memo, Map<String, List<String>> paths) {
		if (from == to) {
			return 1
		}

		if (memo.containsKey(from)) {
			return memo[from]
		}

		memo[from] = paths[from].collect { String next ->
			memo.containsKey(next) ? memo[next] : count(next, to, memo, paths)
		}.sum(0L) as long

		memo[from]
	}

	static Map<String, List<String>> paths(List<String> input) {
		input.collectEntries { line ->
			def (from, to) = line.split(': ')*.toString()
			[(from): to.split(' ')]
		}
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
