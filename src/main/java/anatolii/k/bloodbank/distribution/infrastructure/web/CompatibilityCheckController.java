package anatolii.k.bloodbank.distribution.infrastructure.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/distribution/compatibility")
public class CompatibilityCheckController {

    private Logger logger = LoggerFactory.getLogger(CompatibilityCheckController.class);

    @GetMapping
    ResponseEntity<CompatibilityCheckResponse> checkCompatibility(@RequestParam String recipientType,
                                                                  @RequestParam String donorType){

        logger.info("checkCompatibility request: recipientType={}, donorType={}", recipientType, donorType);

        return ResponseEntity.ok( new CompatibilityCheckResponse(true));
    }

}
