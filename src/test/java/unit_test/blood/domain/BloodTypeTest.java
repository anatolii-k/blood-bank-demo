package unit_test.blood.domain;

import anatolii.k.bloodbank.blood.domain.*;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;


public class BloodTypeTest {

    @Test
    void whenCorrectBloodTypeString_ThenBloodTypeIsCreated(){
        {
            BloodType bt = BloodType.fromString("AB+");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.AB_POSITIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.AB);
            assertThat(bt.getRh()).isEqualTo(Rh.POSITIVE);
        }
        {
            BloodType bt = BloodType.fromString("AB-");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.AB_NEGATIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.AB);
            assertThat(bt.getRh()).isEqualTo(Rh.NEGATIVE);
        }
        {
            BloodType bt = BloodType.fromString("A+");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.A_POSITIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.A);
            assertThat(bt.getRh()).isEqualTo(Rh.POSITIVE);
        }
        {
            BloodType bt = BloodType.fromString("A-");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.A_NEGATIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.A);
            assertThat(bt.getRh()).isEqualTo(Rh.NEGATIVE);
        }
        {
            BloodType bt = BloodType.fromString("B+");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.B_POSITIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.B);
            assertThat(bt.getRh()).isEqualTo(Rh.POSITIVE);
        }
        {
            BloodType bt = BloodType.fromString("B-");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.B_NEGATIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.B);
            assertThat(bt.getRh()).isEqualTo(Rh.NEGATIVE);
        }
        {
            BloodType bt = BloodType.fromString("O+");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.O_POSITIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.O);
            assertThat(bt.getRh()).isEqualTo(Rh.POSITIVE);
        }
        {
            BloodType bt = BloodType.fromString("O-");
            assertThat(bt).isNotNull();
            assertThat(bt).isEqualTo(BloodType.O_NEGATIVE);
            assertThat(bt.getAbo()).isEqualTo(ABO.O);
            assertThat(bt.getRh()).isEqualTo(Rh.NEGATIVE);
        }
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
    }
}
