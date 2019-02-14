package com.gs.ilkws.jobs

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires


@CompileStatic
@ConfigurationProperties(ClientConfiguration.PREFIX)
@Requires(property = ClientConfiguration.PREFIX)
class ClientConfiguration {

    public static final String PREFIX = "service"
    public static final String API_URL = "https://bintray.com"

    String apiversion

    String organization

    String repository

    String username

    String token

    Map<String, Object> toMap() {
        [
                apiversion: apiversion,
                organization: organization,
                repository: repository,
                username: username,
                token: token
        ] as Map<String, Object>
    }

    String path
}