package net.betsafeapp.android;

/**
 * Created by tyln on 16/01/2017.
 */

public interface BasePresenter {
    void start();

    void subscribe();

    void unsubscribe();

    void destroy();
}
