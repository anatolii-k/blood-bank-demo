package unit_test.blood.domain;

import anatolii.k.bloodbank.blood.domain.BloodType;
import anatolii.k.bloodbank.blood.domain.BloodTypeCompatibilityRule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BloodTypeCompatibilityRuleTest {

    @Test
    void givenRecipientWithBloodType_A_Positive__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.A_POSITIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_A_Negative__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.A_NEGATIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_B_Positive__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.B_POSITIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_B_Negative__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.B_NEGATIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_AB_Positive__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.AB_POSITIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_AB_Negative__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.AB_NEGATIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isTrue();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_O_Positive__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.O_POSITIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isTrue();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void givenRecipientWithBloodType_O_Negative__CheckIsCompatibleWithAllTypes(){

        final BloodType recipient = BloodType.O_NEGATIVE;

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.A_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.B_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.AB_NEGATIVE) ).isFalse();

        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_POSITIVE) ).isFalse();
        assertThat( BloodTypeCompatibilityRule.isCompatible(recipient, BloodType.O_NEGATIVE) ).isTrue();
    }

    @Test
    void whenRecipientTypeOrDonorTypeIsNull_thenIsCompatibleThrowsException(){

        assertThatThrownBy( () -> BloodTypeCompatibilityRule.isCompatible(null, BloodType.A_POSITIVE) )
                .isInstanceOf(NullPointerException.class);

        assertThatThrownBy( () -> BloodTypeCompatibilityRule.isCompatible(BloodType.A_POSITIVE, null ) )
                .isInstanceOf(NullPointerException.class);

        assertThatThrownBy( () -> BloodTypeCompatibilityRule.isCompatible(null, null) )
                .isInstanceOf(NullPointerException.class);

    }


}
