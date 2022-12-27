package com.turing.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperationResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private ResponseStatusEnum operationStatus;
    private String operationMessage;
    public enum ResponseStatusEnum {SUCCESS, ERROR, WARNING, NO_ACCESS}
}
