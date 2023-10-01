package com.protonmail.landrevillejf.cognos.categories.api.util.annotation;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Creation;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.License;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Duration;

@Author(
		name = "Jean-Francois Landreville",
		enterprise = "Lanaforge Inc.",
		email = "landrevillejf@protonmail.com",
		website = "https://www.lanaforge.ca"
)
@Maintainer(
		name = "Jean-Francois Landreville",
		enterprise = "Lanaforge Inc.",
		email = "landrevillejf@protonmail.com",
		website = "https://www.lanaforge.ca"
)
@Creation(
		date = "2023-09-30",
		comments = "ExecutionTimeCalculator Annotation"
)
@Revision(
		date = "2023-10-01",
		revision = 2,
		comments = "optimization"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
@SuppressWarnings("CheckStyle")
@Slf4j
@Aspect
@Component
public class ExecutionTimeCalculator {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\033[38;2;0;189;25m";
	public static final String ANSI_YELLOW = "\033[38;2;255;255;0m";
	public static final String ANSI_ORANGE = "\u001B[38;5;208m";

	@Around("@annotation(com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime)")
	public Object getExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Instant startTime = Instant.now();
		Object result = joinPoint.proceed();
		Instant endTime = Instant.now();
		Duration duration = Duration.between(startTime, endTime);

		long minutes = duration.toMinutes();
		long seconds = duration.getSeconds() % 60;

		logExecutionTime(joinPoint.getSignature().getName(), startTime, endTime, minutes, seconds);
		return result;
	}

	private void logExecutionTime(String methodName, Instant startTime, Instant endTime, long minutes, long seconds) {
		log.info("Method {}{}{} called at: {}{}{} and finished execution at: {}{}{}. " +
						"The execution time was: {}{} minutes{} and {}{} seconds{}.",
				ANSI_YELLOW, methodName, ANSI_RESET,
				ANSI_ORANGE, startTime, ANSI_RESET,
				ANSI_ORANGE, endTime, ANSI_RESET,
				ANSI_GREEN, minutes, ANSI_RESET,
				ANSI_GREEN, seconds, ANSI_RESET);
	}
}
