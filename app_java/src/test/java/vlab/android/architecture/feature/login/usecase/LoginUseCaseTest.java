package vlab.android.architecture.feature.login.usecase;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import vlab.android.architecture.feature.login.LoginUseCase;
import vlab.android.architecture.feature.user.UserModel;
import vlab.android.architecture.repository.LoginRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vinh.Tran on 10/25/18.
 **/
public class LoginUseCaseTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private LoginUseCase mLoginUseCase;

    @Mock
    LoginRepository mLoginRepository;

    private LoginUseCase.LoginRequestParam mLoginRequestParam;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        mLoginRequestParam = new LoginUseCase.LoginRequestParam("abc", "123");

        mLoginUseCase = new LoginUseCase(mLoginRepository);
    }

    @Test
    public void testIsUserNameAndPwdNotValid() {

        LoginUseCase.LoginRequestParam data = new LoginUseCase.LoginRequestParam("", "");
        Assert.assertFalse(mLoginUseCase.isDataValid(data));

        data = new LoginUseCase.LoginRequestParam("", null);
        Assert.assertFalse(mLoginUseCase.isDataValid(data));

        data = new LoginUseCase.LoginRequestParam(null, "");
        Assert.assertFalse(mLoginUseCase.isDataValid(data));

        data = new LoginUseCase.LoginRequestParam(null, null);
        Assert.assertFalse(mLoginUseCase.isDataValid(data));
    }

    @Test
    public void testCallApiIfDataValid() {
        when(mLoginRepository.login(mLoginRequestParam.mUserName, mLoginRequestParam.mPwd)).thenReturn(Observable.just(new UserModel()));

        mLoginUseCase.login(mLoginRequestParam);
        verify(mLoginRepository).login(mLoginRequestParam.mUserName, mLoginRequestParam.mPwd);
    }

    @Test
    public void sendResultSuccessToUi() throws Exception {
        UserModel userInfo = new UserModel();
        when(mLoginRepository.login(mLoginRequestParam.mUserName, mLoginRequestParam.mPwd)).thenReturn(Observable.just(userInfo));

        Observer<UserModel> loginSuccessObserver = mock(Observer.class);
        mLoginUseCase.onLoginSuccessObs().observeForever(loginSuccessObserver);

        // perform login, call api...
        mLoginUseCase.login(mLoginRequestParam);

        verify(loginSuccessObserver).onChanged(any());
    }

    @Test
    public void showErrorIfLoginFailed() throws Exception {
        String userName = "abc";
        String pwd = "123";
        when(mLoginRepository.login(userName, pwd)).thenReturn(Observable.error(new NullPointerException()));

        Observer<Throwable> throwableObserver = mock(Observer.class);
        mLoginUseCase.onLoginErrorObs().observeForever(throwableObserver);

        // perform login, call api...
        mLoginUseCase.login(mLoginRequestParam);
        // verify data return
        verify(throwableObserver).onChanged(any());
    }
}