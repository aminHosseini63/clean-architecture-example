package com.link_intersystems.carrental.time;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class ClockProviderTest {

    @Test
    void getDefaultClock() {
        Clock clock = ClockProvider.getClock();
        assertNotNull(clock);
    }

    @Test
    void setClock() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 15, 23, 54);
        Instant intstant = dateTime.toInstant(ZoneOffset.UTC);

        Clock fixedClock = Clock.fixed(intstant, zoneId);
        Clock clock = ClockProvider.getClock();
        try {
            ClockProvider.setClock(fixedClock);
            assertEquals(fixedClock, ClockProvider.getClock());
        } finally {
            ClockProvider.setClock(clock);
        }

    }
}