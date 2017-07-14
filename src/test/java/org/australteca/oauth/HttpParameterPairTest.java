package org.australteca.oauth;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
/**
 * Created by tomi on 13/07/17.
 */
public class HttpParameterPairTest {

    @Test
    public void testGeneratedString(){

        HttpParameterPair httpParameterPair = new HttpParameterPair("a", "b");
        String parameterString = httpParameterPair.getParameterString();

        assertThat(parameterString).isEqualTo("a=b");
    }
}
