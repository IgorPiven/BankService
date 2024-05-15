package com.example.transactionsservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class ErrorDto {

        private int status;
        private String message;
        private ZonedDateTime timeStamp;

        public ErrorDto(int status, String message) {
            this.status = status;
            this.message = message;
            this.timeStamp = ZonedDateTime.now();
        }
}
