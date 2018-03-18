package vlab.android.architecture.ui.login;

import android.arch.lifecycle.Observer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import vlab.android.architecture.model.UserInfo;
import vlab.android.architecture.repository.LoginRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vinh Tran on 3/17/18.
 */
public class LoginViewModelTest {

    private LoginViewModel mViewModel;

    @Mock
    LoginRepository mLoginRepository;

    @Before
    public void init() {
        mLoginRepository = mock(LoginRepository.class);
        mViewModel = new LoginViewModel(mLoginRepository);
    }

    @Test
    public void testIsUserNameAndPwdNotValid() {

        LoginViewModel.LoginRequestParam data = new LoginViewModel.LoginRequestParam("", "");
        Assert.assertFalse(mViewModel.isDataValid(data));

        data = new LoginViewModel.LoginRequestParam("", null);
        Assert.assertFalse(mViewModel.isDataValid(data));

        data = new LoginViewModel.LoginRequestParam(null, "");
        Assert.assertFalse(mViewModel.isDataValid(data));

        data = new LoginViewModel.LoginRequestParam(null, null);
        Assert.assertFalse(mViewModel.isDataValid(data));
    }

    @Test
    public void testCallApiIfDataValid() {
        String userName = "abc";
        String pwd = "123";
        when(mLoginRepository.login(userName,pwd)).thenReturn(Observable.just(new UserInfo()));

        mViewModel.login(userName, pwd);
        verify(mLoginRepository).login(userName, pwd);
    }

    @Test
    public void sendResultSuccessToUi() throws Exception {
        String userName = "abc";
        String pwd = "123";

        UserInfo userInfo = new UserInfo();
        when(mLoginRepository.login(userName,pwd)).thenReturn(Observable.just(userInfo));

        Observer<UserInfo> userInfoObserver = mock(Observer.class);
        mViewModel.onLoginSuccessObs().observeForever(userInfoObserver);

        mViewModel.login(userName,pwd);

        verify(userInfoObserver).onChanged(userInfo);
    }
}