package com.example.reactive.spring5reactiveclient.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Event {

    private final Long id;
    private final Date date;

}
