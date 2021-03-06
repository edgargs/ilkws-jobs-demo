package com.gs.ilkws.jobs

import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject
import spock.lang.Shared
import io.reactivex.Flowable
import spock.lang.Specification

@MicronautTest
class ClientSpec extends Specification {
    
    @Inject
    ClientILKWS clientILKWS

    @Shared
    List<String> expectedProfileNames = ['base', 'federation', 'function', 'function-aws', 'service']

    def "2. Verify bintray packages can be fetched with compile-time autogenerated @Client"() {
        
        when:
        Flowable<ClientPackage> packagesJobs = clientILKWS.getPackages()
        Iterable<ClientPackage> packages = packagesJobs.blockingIterable()
        then:
        for (String name : expectedProfileNames) {
            assert packages*.name.contains(name)
        }
    }
}