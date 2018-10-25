package vlab.android.architecture.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vinh.Tran on 10/25/18.
 **/
public class TextValidatorTest {

    @Test
    public void isUserNameValid() {
        Assert.assertEquals(false, TextValidator.isUserNameValid(""));
    }

}