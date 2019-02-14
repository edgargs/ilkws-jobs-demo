package com.gs.ilkws.jobs

import groovy.util.logging.Slf4j
import javax.inject.Singleton
import io.micronaut.scheduling.annotation.Scheduled

import io.reactivex.Maybe
import io.reactivex.Flowable

@Slf4j
@Singleton
class ClientsJob {

    ClientLowLevelILKWS clientLowLevelILKWS
    ClientILKWS clientILKWS

    ClientsJob(ClientLowLevelILKWS clientLowLevelILKWS,
                        ClientILKWS clientILKWS) {
        this.clientLowLevelILKWS = clientLowLevelILKWS
        this.clientILKWS = clientILKWS
    }

    @Scheduled(fixedDelay = "10s")
    void consumeLowLevel() {
        Maybe<List<ClientPackage>> packagesJobs = clientLowLevelILKWS.getPackages()
        log.debug("LowLevel:"+packagesJobs.blockingGet().toString())
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    void consumeClient() {
        Flowable<ClientPackage> packagesJobs = clientILKWS.getPackages()
        //log.debug("@Client:"+packagesJobs.blockingLast().toString())
        packagesJobs.subscribe({s -> log.debug "@Client:"+s.toString()})
    }
}