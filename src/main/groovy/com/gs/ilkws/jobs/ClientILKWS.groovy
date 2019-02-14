package com.gs.ilkws.jobs

import groovy.transform.CompileStatic
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriTemplate

import io.reactivex.Flowable
import io.reactivex.Maybe

import javax.inject.Singleton

@CompileStatic
@Client(ClientConfiguration.API_URL)
interface ClientILKWS {
    @Get('/api/${service.apiversion}/repos/${service.organization}/${service.repository}/packages')
    Flowable<ClientPackage> getPackages()
}