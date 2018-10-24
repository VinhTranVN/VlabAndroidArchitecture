package vlab.android.architecture.feature.validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vinh Tran on 10/25/18.
 */
public class TextValidatorTest {

    @Test
    public void isUserNameValid() {
        TextValidator textValidator = new TextValidator();
        boolean isValid = textValidator.isUserNameValid("");

        Assert.assertEquals(false, isValid);

    }
}