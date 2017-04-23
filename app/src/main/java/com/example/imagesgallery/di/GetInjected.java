package com.example.imagesgallery.di;

import javax.inject.Inject;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class GetInjected {
    @Inject
    Dagger2Tester tester;

    public void print() {
        System.out.println(tester);
    }
}
