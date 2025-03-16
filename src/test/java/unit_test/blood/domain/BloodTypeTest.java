package unit_test.blood.domain;

import anatolii.k.bloodbank.blood.domain.*;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;


public class BloodTypeTest {

    @Test
    void whenCorrectBloodTypeStringOfFormatABO(){

        testBloodTypeString("AB+", BloodType.AB_POSITIVE, ABO.AB, Rh.POSITIVE);
        testBloodTypeString("AB-", BloodType.AB_NEGATIVE, ABO.AB, Rh.NEGATIVE);

        testBloodTypeString("A+", BloodType.A_POSITIVE, ABO.A, Rh.POSITIVE);
        testBloodTypeString("A-", BloodType.A_NEGATIVE, ABO.A, Rh.NEGATIVE);

        testBloodTypeString("B+", BloodType.B_POSITIVE, ABO.B, Rh.POSITIVE);
        testBloodTypeString("B-", BloodType.B_NEGATIVE, ABO.B, Rh.NEGATIVE);

        testBloodTypeString("O+", BloodType.O_POSITIVE, ABO.O, Rh.POSITIVE);
        testBloodTypeString("O-", BloodType.O_NEGATIVE, ABO.O, Rh.NEGATIVE);
    }

    @Test
    void whenCorrectBloodTypeStringOfNumericFormat(){

        testBloodTypeString("I+", BloodType.O_POSITIVE, ABO.O, Rh.POSITIVE);
        testBloodTypeString("I-", BloodType.O_NEGATIVE, ABO.O, Rh.NEGATIVE);

        testBloodTypeString("II+", BloodType.A_POSITIVE, ABO.A, Rh.POSITIVE);
        testBloodTypeString("II-", BloodType.A_NEGATIVE, ABO.A, Rh.NEGATIVE);

        testBloodTypeString("III+", BloodType.B_POSITIVE, ABO.B, Rh.POSITIVE);
        testBloodTypeString("III-", BloodType.B_NEGATIVE, ABO.B, Rh.NEGATIVE);

        testBloodTypeString("IV+", BloodType.AB_POSITIVE, ABO.AB, Rh.POSITIVE);
        testBloodTypeString("IV-", BloodType.AB_NEGATIVE, ABO.AB, Rh.NEGATIVE);
    }

    @Test
    void whenCorrectBloodTypeStringOfMixedFormat(){

        testBloodTypeString("AB(IV)+", BloodType.AB_POSITIVE, ABO.AB, Rh.POSITIVE);
        testBloodTypeString("AB(IV)-", BloodType.AB_NEGATIVE, ABO.AB, Rh.NEGATIVE);

        testBloodTypeString("A(II)+", BloodType.A_POSITIVE, ABO.A, Rh.POSITIVE);
        testBloodTypeString("A(II)-", BloodType.A_NEGATIVE, ABO.A, Rh.NEGATIVE);

        testBloodTypeString("B(III)+", BloodType.B_POSITIVE, ABO.B, Rh.POSITIVE);
        testBloodTypeString("B(III)-", BloodType.B_NEGATIVE, ABO.B, Rh.NEGATIVE);

        testBloodTypeString("O(I)+", BloodType.O_POSITIVE, ABO.O, Rh.POSITIVE);
        testBloodTypeString("O(I)-", BloodType.O_NEGATIVE, ABO.O, Rh.NEGATIVE);
    }


    @Test
    void whenEmptyBloodTypeString_ThenNullIsReturned(){

        BloodType bt = BloodType.fromString("");
        assertThat(bt).isNull();
    }

    @Test
    void whenInvalidBloodTypeString_ThenExceptionIsThrown(){

        Assertions.assertThatThrownBy( () -> BloodType.fromString("A") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("B") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("AB") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("O") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("D") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("AA-") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("BA+") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("OO") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("+") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("-") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("X") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("VI+") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("A(VI)+") )
                .isInstanceOf(IllegalBloodTypeException.class);

    }

    @Test
    void whenBloodTypeWithABOAndNumericFormatMismatch_thenExceptionIsThrown(){

        Assertions.assertThatThrownBy( () -> BloodType.fromString("A(III)+") )
                .isInstanceOf(IllegalBloodTypeException.class);

        Assertions.assertThatThrownBy( () -> BloodType.fromString("AB(I)-") )
                .isInstanceOf(IllegalBloodTypeException.class);

    }

    private void testBloodTypeString( String bloodTypeString, BloodType expectedType, ABO expectedABO, Rh expectedRh ){
        BloodType bt = BloodType.fromString(bloodTypeString);
        assertThat(bt).isNotNull();
        assertThat(bt).isEqualTo(expectedType);
        assertThat(bt.getAbo()).isEqualTo(expectedABO);
        assertThat(bt.getRh()).isEqualTo(expectedRh);
    }
}
