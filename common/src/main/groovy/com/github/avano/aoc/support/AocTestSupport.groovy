package com.github.avano.aoc.support

class AocTestSupport {
	static List<String> testFile(String fileName) {
		return new File("src/test/resources/${fileName}.txt").text.trim().split('\n')
	}

	static List<String> inputFile(Class parent, String fileName) {
		parent.classLoader.getResourceAsStream("${fileName}.txt").text.trim().split('\n')
	}
}
