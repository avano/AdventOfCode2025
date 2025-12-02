package com.github.avano.aoc.support

class AocSupport {
	static List<String> testFile(String fileName, String delimiter = '\n') {
		return new File("src/test/resources/${fileName}.txt").text.trim().split(delimiter)
	}

	static List<String> inputFile(Class parent, String fileName, String delimiter = '\n') {
		parent.classLoader.getResourceAsStream("${fileName}.txt").text.trim().split(delimiter)
	}
}
