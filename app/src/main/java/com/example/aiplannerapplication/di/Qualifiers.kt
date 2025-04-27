package com.example.aiplannerapplication.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RegularInterceptorQualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ErrorInterceptorQualifier