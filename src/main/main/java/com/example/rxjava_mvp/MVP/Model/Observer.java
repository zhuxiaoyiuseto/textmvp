package com.example.rxjava_mvp.MVP.Model;

import com.example.rxjava_mvp.MVP.Error.ExceptionHandle;

import io.reactivex.disposables.Disposable;

public abstract class Observer<T> implements io.reactivex.Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        onDisposable(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(ExceptionHandle.handleEcception(e));
    }

    @Override
    public void onComplete() {
        onCompleted();
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(ExceptionHandle.ResponeThrowable e);

    public abstract void onCompleted();

    public abstract void onDisposable(Disposable d);
}
