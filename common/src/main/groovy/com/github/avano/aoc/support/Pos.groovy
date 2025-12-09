package com.github.avano.aoc.support

class Pos {
	int x, y, z

	Pos(List<Integer> coordinates) {
		this.x = coordinates[0]
		this.y = coordinates[1]
		this.z = coordinates[2] ?: 0
	}

	double distance(Pos other) {
		Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2))
	}

	boolean equals(o) {
		if (this.is(o)) return true
		if (o == null || getClass() != o.class) return false

		Pos pos = (Pos) o

		if (x != pos.x) return false
		if (y != pos.y) return false
		if (z != pos.z) return false

		return true
	}

	int hashCode() {
		int result
		result = x
		result = 31 * result + y
		result = 31 * result + z
		return result
	}

	String toString() {
		"[${x},${y},${z}]"
	}
}
