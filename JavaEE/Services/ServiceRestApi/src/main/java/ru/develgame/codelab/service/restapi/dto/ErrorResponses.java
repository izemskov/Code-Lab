package ru.develgame.codelab.service.restapi.dto;

import java.util.List;

public class ErrorResponses {
    private List<String> errorMessages;

    public ErrorResponses(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
