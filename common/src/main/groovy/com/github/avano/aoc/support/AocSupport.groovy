package com.github.avano.aoc.support

class AocSupport {
	static <T> T testFileAs(Map config = [:], Class<T> type) {
		convert(config, new File("src/test/resources/${config['fileName'] ?: 'example'}.txt" as String).text.trim(), type)
	}

	static <T> T inputFileAs(Map config = [:], Class parent, Class<T> type) {
		convert(config, parent.classLoader.getResourceAsStream("${config['fileName'] ?: 'input'}.txt").text.trim(), type)
	}

	private static <T> T convert(Map config, String content, Class<T> type) {
		switch (type) {
			case String.class:
				(T) content
				break
			case List.class:
				(T) Arrays.asList(content.split(config['delimiter'] as String ?: "\n"))
				break
			case AocMap.class:
				(T) new AocMap(content)
				break
			default:
				throw new IllegalArgumentException("Unexpected type: ${type}")
		}
	}
}
