package com.orgofarmsgroup;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PerfectAppApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(PerfectAppApplicationTests.class);

	@Test
	void contextLoads() {
		LOGGER.info("test succeeded. triggering sonarcloud.");
	}

}
