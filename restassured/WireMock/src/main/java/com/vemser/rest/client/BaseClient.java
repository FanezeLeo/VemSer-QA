package com.vemser.rest.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {

    public RequestSpecification set() {
        String BASEURL = "http://localhost:8080";
        return new RequestSpecBuilder()
                .setBaseUri(BASEURL)
                .setConfig(RestAssuredConfig.config().logConfig(
                        LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()
                ))
                .build();
    }

}
