package com.example.imagesgallery.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hyemi on 2017. 4. 23..
 */

@Module
public class Dagger2Module {
    @Provides @Singleton Dagger2Tester dagger2Tester() {
        return new Dagger2Tester();
    }
}
