package com.the.harbor.commons.components.sequence.client.impl;

import com.the.harbor.commons.components.sequence.client.ISeqClient;
import com.the.harbor.commons.components.sequence.service.ISequenceService;
import com.the.harbor.commons.components.sequence.service.impl.SequenceServiceImpl;

public class NormalSeqClientImpl implements ISeqClient {

    private ISequenceService sequenceService;

    public NormalSeqClientImpl() {
        this.sequenceService = new SequenceServiceImpl();
    }

    @Override
    public Long nextValue(String sequenceName) {
        return sequenceService.nextValue(sequenceName);
    }

}
