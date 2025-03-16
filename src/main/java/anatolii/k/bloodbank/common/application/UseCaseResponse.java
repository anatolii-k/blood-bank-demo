package anatolii.k.bloodbank.common.application;

import java.util.LinkedList;
import java.util.List;

public class UseCaseResponse {

    private boolean isOK;
    private final List<String> errors = new LinkedList<>();


    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
