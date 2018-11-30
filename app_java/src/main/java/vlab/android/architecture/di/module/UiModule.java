/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vlab.android.architecture.di.module;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import vlab.android.architecture.feature.home.HomeFragment;
import vlab.android.architecture.feature.login.LoginFragment;
import vlab.android.architecture.feature.main.MainActivity;
import vlab.android.architecture.feature.user_repository.UserRepositoryFragment;

@Module
public abstract class UiModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    abstract UserRepositoryFragment userRepositoryFragment();
}
