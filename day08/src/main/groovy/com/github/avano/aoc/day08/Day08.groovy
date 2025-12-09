package com.github.avano.aoc.day08

import com.github.avano.aoc.support.AocSupport
import com.github.avano.aoc.support.Pos

class Day08 extends AocSupport {
	static long part1(List<String> input = inputFileAs(Day08.class, List.class), int pairs = 1000) {
		List<Pos> points = parse(input)
		Queue<Distance> distances = toDistances(points)
		List<Circuit> circuits = points.collect { new Circuit(it) }

		pairs.times {
			Distance d = distances.poll()
			Circuit c1 = circuits.find { it.points.contains(d.p1) }
			Circuit c2 = circuits.find { it.points.contains(d.p2) }

			if (c1 != c2) {
				c1.points.addAll(c2.points)
				circuits.remove(c2)
			}
		}

		circuits.sort { it.points.size() }.takeRight(3).inject(1L, { sum, it -> sum * it.points.size() })
	}

	static int part2(List<String> input = inputFileAs(Day08.class, List.class)) {
		List<Pos> points = parse(input)
		Queue<Distance> distances = toDistances(points)
		List<Circuit> circuits = points.collect { new Circuit(it) }

		Distance last = null
		while (circuits.size() > 1) {
			last = distances.poll()
			Circuit c1 = circuits.find { it.points.contains(last.p1) }
			Circuit c2 = circuits.find { it.points.contains(last.p2) }

			if (c1 != c2) {
				c1.points.addAll(c2.points)
				circuits.remove(c2)
			}
		}

		last.p1.x * last.p2.x
	}

	static List<Pos> parse(List<String> input) {
		input.collect { it ->
			new Pos(it.split(',')*.toInteger())
		}
	}

	static Queue<Distance> toDistances(List<Pos> points) {
		Queue<Distance> distances = new PriorityQueue<Distance>(Comparator.comparingDouble { Distance d -> d.distance })

		for (int i = 0; i < points.size() - 1; i++) {
			for (int j = i + 1; j < points.size(); j++) {
				distances.add(new Distance(points[i], points[j]))
			}
		}

		distances
	}

	static class Circuit {
		Set<Pos> points = []

		Circuit(Pos point) {
			this.points.add(point)
		}
	}

	static class Distance {
		Pos p1
		Pos p2
		double distance

		Distance(Pos p1, Pos p2) {
			this.p1 = p1
			this.p2 = p2
			this.distance = p1.distance(p2)
		}
	}

	static void main(String[] args) {
		println part1()
		println part2()
	}
}
