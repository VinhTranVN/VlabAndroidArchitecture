package vlab.android.architecture.ui.login;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import vlab.android.architecture.model.UserInfo;
import vlab.android.architecture.repository.LoginRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vinh Tran on 3/17/18.
 */
public class LoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private LoginViewModel mViewModel;

    @Mock
    LoginRepository mLoginRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
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
        when(mLoginRepository.login(userName,pwd)).thenReturn(Observable.just(new UserInfo()));

        Observer<UserInfo> userInfoObserver = mock(Observer.class);
        mViewModel.onLoginSuccessObs().observeForever(userInfoObserver);

        // perform login, call api...
        mViewModel.login(userName,pwd);
        // verify data return
        verify(userInfoObserver).onChanged(userInfo);
    }

    @Test
    public void showErrorIfLoginFailed() throws Exception {
        String userName = "abc";
        String pwd = "123";
        when(mLoginRepository.login(userName,pwd)).thenReturn(Observable.error(new NullPointerException()));

        Observer<Throwable> throwableObserver = mock(Observer.class);
        mViewModel.onLoginErrorObs().observeForever(throwableObserver);

        // perform login, call api...
        mViewModel.login(userName,pwd);
        // verify data return
        verify(throwableObserver).onChanged(any());
    }
}