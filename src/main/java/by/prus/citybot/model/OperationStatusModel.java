package by.prus.citybot.model;

import java.io.Serializable;

public class OperationStatusModel implements Serializable {
    private String operationResult;
    private String operationName;

    public String getOperationResult() { return operationResult; }
    public void setOperationResult(String operationResult) { this.operationResult = operationResult; }
    public String getOperationName() { return operationName; }
    public void setOperationName(String operationName) { this.operationName = operationName; }
}
