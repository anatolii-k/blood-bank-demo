package unit_test.blood.domain;

import anatolii.k.bloodbank.blood.domain.IllegalRhException;
import anatolii.k.bloodbank.blood.domain.Rh;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

public class RhTest {

    @Test
    void whenCorrectRhString_ThenRhIsCreated (){

        assertThat(Rh.fromString("+")).isEqualTo(Rh.POSITIVE);

        assertThat(Rh.fromString("-")).isEqualTo(Rh.NEGATIVE);

    }

    @Test
    void whenEmptyRhString_ThenNullIsReturned(){
        assertThat(Rh.fromString("")).isNull();
    }

    @Test
    void whenInvalidRhString_ThenExceptionIsThrown(){
        Assertions.assertThatThrownBy( () -> Rh.fromString("A") )
                .isInstanceOf(IllegalRhException.class);

        Assertions.assertThatThrownBy( () -> Rh.fromString("++") )
                .isInstanceOf(IllegalRhException.class);

        Assertions.assertThatThrownBy( () -> Rh.fromString("--") )
                .isInstanceOf(IllegalRhException.class);

        Assertions.assertThatThrownBy( () -> Rh.fromString("N") )
                .isInstanceOf(IllegalRhException.class);
    }
}
