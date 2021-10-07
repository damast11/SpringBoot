package com.example.dkylish.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentException extends RuntimeException{

    private final String field;
    private final String fieldValue;
    private final String reason;
    private final Status status;

    public enum Status {

        NOT_FOUND("Not found", 404),
        INTERNAL_ERROR("Internal Error", 500);

        private final String value;
        private final int code;

        Status(String value, int code) {
            this.value = value;
            this.code = code;
        }

        public int code() {
            return this.code;
        }

        public String value() {
            return this.value;
        }

        @Override
        public String toString() {
            return code + " " + name();
        }

    }
}
