package com.the.harbor.commons.components.sequence.dao;

import java.util.List;

import com.the.harbor.commons.components.sequence.model.Sequence;
import com.the.harbor.commons.components.sequence.model.SequenceCache;

public interface ISequenceDao {

    SequenceCache getSequenceCache(String sequenceName);

    List<Sequence> queryAllSequence();

    Sequence querySequenceByName(String sequenceName);

    void modifySequence(String sequenceName, long nextVal);

}
