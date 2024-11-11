package com.execfy.teste.currency.connector;

import java.util.List;

import org.camunda.connect.impl.AbstractRequestInvocation;
import org.camunda.connect.spi.ConnectorRequest;
import org.camunda.connect.spi.ConnectorRequestInterceptor;


public class CurrencyRateInvocation extends AbstractRequestInvocation<CurrencyRateResponse> {

    public CurrencyRateInvocation(
            CurrencyRateResponse target,
            ConnectorRequest<?> request,
            List<ConnectorRequestInterceptor> interceptorChain) {
        super(target, request, interceptorChain);
    }

    @Override
    public CurrencyRateResponse invokeTarget() throws Exception {
        return target;
    }



}