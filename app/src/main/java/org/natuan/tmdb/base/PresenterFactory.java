package org.natuan.tmdb.base;

/**
 * Created by Nguyen Anh Tuan on 29/10/2016.
 * natuan.org@gmail.com
 */

public interface PresenterFactory<T extends Presenter> {
    T create();
}
