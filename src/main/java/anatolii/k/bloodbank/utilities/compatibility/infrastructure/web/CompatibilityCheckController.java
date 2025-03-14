package anatolii.k.bloodbank.utilities.compatibility.infrastructure.web;

import anatolii.k.bloodbank.utilities.compatibility.application.CheckCompatibilityResponse;
import anatolii.k.bloodbank.utilities.compatibility.application.CheckCompatibilityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/utilities/compatibility")
public class CompatibilityCheckController {

    @GetMapping
    ResponseEntity<CheckCompatibilityResponse> checkCompatibility(@RequestParam String recipientType,
                                                                  @RequestParam String donorType){

        CheckCompatibilityResponse response = checkCompatibilityUseCase.check(recipientType,donorType);
        return ResponseEntity.ok( response );
    }

    @Autowired
    public CompatibilityCheckController(CheckCompatibilityUseCase checkCompatibilityUseCase) {
        this.checkCompatibilityUseCase = checkCompatibilityUseCase;
    }

    private final CheckCompatibilityUseCase checkCompatibilityUseCase;
}
