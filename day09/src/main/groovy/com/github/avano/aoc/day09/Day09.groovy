package com.github.avano.aoc.day09

import com.github.avano.aoc.support.AocSupport
import com.github.avano.aoc.support.Pos

class Day09 extends AocSupport {
	static long part1(List<String> input = inputFileAs(Day09.class, List.class)) {
		long max = 0
		List<Pos> points = []

		input.each { line ->
			Pos p = new Pos(line.split(',')*.toInteger())
			points.each { other -> max = Math.max(max, rectangleSize(p, other)) }
			points.add(p)
		}

		max
	}

	static long rectangleSize(Pos p1, Pos p2) {
		(Math.abs(p1.x - p2.x) + 1) as long * (Math.abs(p1.y - p2.y) + 1)
	}

	static int part2(List<String> input = inputFileAs(Day09.class, List.class)) {
		-1
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
