package anatolii.k.bloodbank.utilities.compatibility.application;

import anatolii.k.bloodbank.common.application.UseCaseResponse;

public class CheckCompatibilityResponse extends UseCaseResponse {

    private boolean isCompatible;

    public boolean isCompatible() {
        return isCompatible;
    }

    public void setCompatible(boolean compatible) {
        isCompatible = compatible;
    }
}
