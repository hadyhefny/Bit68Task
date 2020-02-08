package com.hefny.hady.bit68task.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A generic class that holds a value with its loading status.
 * Based on Android Architecture Components example:
 * https://github.com/android/architecture-components-samples/blob/b1a194c1ae/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.java
 *
 * @param <T>
 */
public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@NonNull String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    /**
     * Status of a resource that is provided to the UI.
     */
    public enum Status {SUCCESS, ERROR, LOADING}
}