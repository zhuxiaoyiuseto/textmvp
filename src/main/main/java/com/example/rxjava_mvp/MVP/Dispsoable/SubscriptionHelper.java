package com.example.rxjava_mvp.MVP.Dispsoable;

import io.reactivex.disposables.Disposable;

public interface SubscriptionHelper<T> {
    void add(Disposable subscription);
    void cacel(Disposable t);
    void cancelall();
}
