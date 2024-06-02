package org.veeva.atf.constants;

import lombok.SneakyThrows;

import java.time.Duration;

public class SessionVariable {

    public static final Duration MIN_WAIT_TIME = Duration.ofSeconds(30);
    public static final Duration MAX_WAIT_TIME = Duration.ofSeconds(60);

    public static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(15);

    public static final long WAIT_TIME = 20000;

    public static final long WAIT_MIN_TIME = 5000;


    @SneakyThrows
    public static void delayTime() {
        Thread.sleep(WAIT_TIME);
    }

    @SneakyThrows
    public static void delayMinTime() {
        Thread.sleep(WAIT_MIN_TIME);
    }







}
