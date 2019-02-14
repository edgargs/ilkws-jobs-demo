package com.gs.ilkws.jobs

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.context.ApplicationContext

import spock.lang.Specification
import spock.lang.AutoCleanup
import spock.lang.Shared
import io.reactivex.Maybe

class ClientLowLevelSpec extends Specification {

    @Shared
    @AutoCleanup 
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    ClientLowLevelILKWS clientLowLevelILKWS = embeddedServer.applicationContext.getBean(ClientLowLevelILKWS)


    def "1. Verify reactive packages can be fetched with low level HttpClient"() {
        given:
            println """
                    ¡Servidor iniciado!
                    Puerto: ${embeddedServer.getPort()}
                    """

        when:
        Maybe<List<ClientPackage>> packagesJobs = clientLowLevelILKWS.getPackages()
        
        then:
        packagesJobs.test().assertNoErrors()
    }

    
    
}