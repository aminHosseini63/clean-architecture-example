package com.link_intersystems.person.customer;

import com.link_intersystems.person.Age;

import java.time.*;

public class Customer {

    private CustomerId id;
    private LocalDate birthday;

    public Customer(CustomerId id, LocalDate birthday) {
        this.id = id;
        this.birthday = birthday;
    }

    public Age getAge(Clock atTime) {
        Instant now = atTime.instant();
        ZoneId zone = atTime.getZone();
        Period age = Period.between(birthday, LocalDate.ofInstant(now, zone));
        return new Age(age.getYears());
    }

    public CustomerId getId() {
        return id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}