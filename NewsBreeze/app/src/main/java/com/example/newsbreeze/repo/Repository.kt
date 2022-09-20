package com.example.newsbreeze.repo

import com.example.newsbreeze.db.LocalDataSource
import com.example.newsbreeze.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
//Scope annotation for bindings that should exist for the life of an activity, surviving configuration.
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource

) {
    val remote = remoteDataSource
    val local = localDataSource

}