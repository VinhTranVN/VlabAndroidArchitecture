package vlab.android.common.util;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by vlab.smartapps@gmail.com on 11/14/18.
 */
public class RxTaskTest {

    @Rule
    public InstantTaskExecutorRule taskExecutorRule = new InstantTaskExecutorRule();

    RxTask<String, String> mRxTask;
    private CountDownLatch mCountDownLatch;

    @Before
    public void setup(){

        mCountDownLatch = new CountDownLatch(1);

        mRxTask = new RxTask<>(inputParam ->
                callApi1(inputParam)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
        );
        mRxTask.subscribe();
    }

    @Test
    public void testExecuteTask(){
        Observer<String> observer = mock(Observer.class);
        mRxTask.onSingleLiveDataChanged().observeForever(observer);
        mRxTask.execute("123");

        /*try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        verify(observer).onChanged("123");
        System.out.println("DONE");
    }

    private Observable<String> callApi1(String textInput){
        return Observable.create(emitter -> {
            //String result = "Api 1 responded textInput " + textInput;
            String result = textInput;
            System.out.println("Thread " + Thread.currentThread());
            System.out.println(">>> sent " + result);
            emitter.onNext(result);
            emitter.onComplete();
        });
    }
}