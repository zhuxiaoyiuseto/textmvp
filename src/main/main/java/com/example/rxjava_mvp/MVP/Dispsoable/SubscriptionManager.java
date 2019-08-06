package com.example.rxjava_mvp.MVP.Dispsoable;

import android.icu.text.UFormat;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SubscriptionManager implements SubscriptionHelper<Object> {
    public static SubscriptionManager subscriptionManager;
    private CompositeDisposable mDisposables;

    public SubscriptionManager() {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
    }

    @Override
    public void add(Disposable subscription) {
        if (subscription == null) return;
        mDisposables.add(subscription);
    }

    @Override
    public void cacel(Disposable t) {
        if (mDisposables != null) {
            mDisposables.delete(t);
        }
    }

    @Override
    public void cancelall() {
        if (mDisposables != null) {
            mDisposables.clear();
        }
    }

    //单例
    public static SubscriptionManager getInstance() {
        if (subscriptionManager == null) {
            synchronized (SubscriptionManager.class) {
                if (subscriptionManager == null) {
                    subscriptionManager = new SubscriptionManager();
                }
            }
        }
        return subscriptionManager;
    }
}
