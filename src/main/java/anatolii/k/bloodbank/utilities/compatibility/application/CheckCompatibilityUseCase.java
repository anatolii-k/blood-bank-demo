package anatolii.k.bloodbank.utilities.compatibility.application;

import anatolii.k.bloodbank.blood.domain.BloodType;
import anatolii.k.bloodbank.blood.domain.BloodTypeCompatibilityRule;
import anatolii.k.bloodbank.common.annotations.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UseCase
public class CheckCompatibilityUseCase {

    public CheckCompatibilityResponse check(String recipientBloodType, String donorBloodType){
        Impl impl = new Impl();
        return impl.check(recipientBloodType, donorBloodType);
    }

    // goal of Impl is to make CheckCompatibilityUseCase stateless -> thread safe
    private static class Impl{

        public CheckCompatibilityResponse check(String recipientBloodType, String donorBloodType) {

            if( convertBloodTypes(recipientBloodType, donorBloodType) ){
                response.setCompatible(BloodTypeCompatibilityRule.isCompatible(recipient,donor));
                response.setOK(true);
            }
            return response;
        }

        private boolean convertBloodTypes(String recipientBloodType, String donorBloodType) {
            recipient = convertBloodType(recipientBloodType, "Recipient");
            donor     = convertBloodType(donorBloodType, "Donor");
            return recipient != null && donor != null;
        }

        private BloodType convertBloodType(String bloodTypeStr, String participantType) {
            if(bloodTypeStr.isEmpty()){
                response.addError( "%s blood type is empty".formatted(participantType) );
                return null;
            }
            try{
                return BloodType.fromString(bloodTypeStr);
            }
            catch (Exception e){
                logger.info("Failed to convert {} string [{}] to Blood Type\nDetails: {}", participantType, bloodTypeStr, e.getMessage());
                response.addError("%s blood type [%s] is not a valid Blood Type".formatted(participantType, bloodTypeStr));
            }
            return null;
        }

        private BloodType recipient;
        private BloodType donor;
        private final CheckCompatibilityResponse response = new CheckCompatibilityResponse();
        private static final Logger logger = LoggerFactory.getLogger(CheckCompatibilityUseCase.class);
    }
}
