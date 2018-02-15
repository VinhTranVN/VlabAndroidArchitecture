package vlab.android.common.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Execute Rx command
 */
public class RxCommandNew<ResultType> {
    PublishSubject<Void> mCommand = PublishSubject.create();
    BehaviorSubject<Boolean> mOnExecuting = BehaviorSubject.create(false);
    PublishSubject<Throwable> mOnError = PublishSubject.create();
    Observable<ResultType> mOnExecuted = PublishSubject.create();

    public RxCommandNew() {
    }

    public void execute(Observable<ResultType> observable) {
        mOnExecuted = mCommand.withLatestFrom(mOnExecuting, (v, executing) -> executing)
                .filter(executing -> !executing)
                .flatMap(v -> {
                    mOnExecuting.onNext(true);
                    return observable
                            .doOnError((v1) -> {
                                mOnExecuting.onNext(false);
                                mOnError.onNext(v1);
                            })
                            .onErrorResumeNext(error -> Observable.empty())
                            .doOnNext(resultType -> mOnExecuting.onNext(false))
                            .doOnUnsubscribe(() -> mOnExecuting.onNext(false));
                });

        mCommand.onNext(null);
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
