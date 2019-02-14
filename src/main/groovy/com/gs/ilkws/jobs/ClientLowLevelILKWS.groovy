package com.gs.ilkws.jobs

import groovy.transform.CompileStatic
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriTemplate

import io.reactivex.Flowable
import io.reactivex.Maybe

import javax.inject.Singleton

@CompileStatic
@Singleton 
class ClientLowLevelILKWS {
    private final RxHttpClient httpClient
    private final String uri
    
    ClientLowLevelILKWS(@Client(ClientConfiguration.API_URL) RxHttpClient httpClient, 
                          ClientConfiguration configuration) {
        this.httpClient = httpClient
        String path = configuration.path
        uri = UriTemplate.of(path).expand(configuration.toMap())
    }

    Maybe<List<ClientPackage>> getPackages() {
        HttpRequest<?> req = HttpRequest.GET(uri) 
        Flowable flowable = httpClient.retrieve(req, Argument.of(List, ClientPackage)) 
        flowable.firstElement() as Maybe<List<ClientPackage>> 
    }
}