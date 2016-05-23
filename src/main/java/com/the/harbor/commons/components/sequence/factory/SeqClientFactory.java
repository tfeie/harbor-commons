package com.the.harbor.commons.components.sequence.factory;

import com.the.harbor.commons.components.sequence.client.ISeqClient;
import com.the.harbor.commons.components.sequence.client.impl.NormalSeqClientImpl;

public final class SeqClientFactory {

    private SeqClientFactory() {

    }

    private static ISeqClient sequenceClient;

    public static ISeqClient getSeqClient() {
        if (sequenceClient == null) {
                sequenceClient = new NormalSeqClientImpl();
        }
        return sequenceClient;
    }
}
