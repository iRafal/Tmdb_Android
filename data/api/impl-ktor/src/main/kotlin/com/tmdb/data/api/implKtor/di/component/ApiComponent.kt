package com.tmdb.data.api.implKtor.di.component

import com.tmdb.data.api.config.di.module.ApiConfigModule
import com.tmdb.data.api.implKtor.di.module.ApiModule
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.Component


@[ApplicationScope Component(modules = [ApiModule::class, ApiConfigModule::class])]
interface ApiComponent: HasApiServices {

    @Component.Builder
    interface Builder {
        fun build(): ApiComponent
    }
}
