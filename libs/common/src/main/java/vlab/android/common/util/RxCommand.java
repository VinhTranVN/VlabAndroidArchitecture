package vlab.android.common.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;


/**
 * Execute Rx command
 */
public class RxCommand<ResultType> {
    PublishSubject<String> mCommand = PublishSubject.create();
    BehaviorSubject<Boolean> mOnExecuting = BehaviorSubject.createDefault(false);
    PublishSubject<Throwable> mOnError = PublishSubject.create();
    Observable<ResultType> mOnExecuted;

    public RxCommand(Observable<ResultType> func) {
        mOnExecuted = mCommand.withLatestFrom(mOnExecuting, (v, executing) -> executing)
                .filter(executing -> !executing)
                .flatMap(v -> {
                    mOnExecuting.onNext(true);
                    return func
                            .doOnError((v1) -> {
                                mOnExecuting.onNext(false);
                                mOnError.onNext(v1);
                            })
                            .onErrorResumeNext(throwable -> {
                                return Observable.empty();
                            })
                            .doOnNext(resultType -> mOnExecuting.onNext(false))
                            .doOnDispose(() -> mOnExecuting.onNext(false));
                });
    }

    public void execute(){
        mCommand.onNext("");
    }

    public Observable<Boolean> onExecuting() {
        return mOnExecuting.debounce(300, TimeUnit.MILLISECONDS);
    }

    public Observable<ResultType> onExecuted() {
        return mOnExecuted;
    }

    public Observable<Throwable> onError() {
        return mOnError;
    }
}
