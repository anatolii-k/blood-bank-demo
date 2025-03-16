package unit_test.blood.domain;

import anatolii.k.bloodbank.blood.domain.ABO;
import anatolii.k.bloodbank.blood.domain.ABONumericFormat;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ABOConversionTest {

    @Test
    void convertFromABOToNumericFormat(){

        assertThat(ABO.O.toNumericFormat()  ).isEqualTo(ABONumericFormat.I);
        assertThat(ABO.A.toNumericFormat()  ).isEqualTo(ABONumericFormat.II);
        assertThat(ABO.B.toNumericFormat()  ).isEqualTo(ABONumericFormat.III);
        assertThat(ABO.AB.toNumericFormat() ).isEqualTo(ABONumericFormat.IV);
    }

    @Test
    void convertFromNumericToABOFormat(){

        assertThat(ABONumericFormat.I.toABOFormat()).isEqualTo(ABO.O);
        assertThat(ABONumericFormat.II.toABOFormat()).isEqualTo(ABO.A);
        assertThat(ABONumericFormat.III.toABOFormat()).isEqualTo(ABO.B);
        assertThat(ABONumericFormat.IV.toABOFormat()).isEqualTo(ABO.AB);
    }
}
