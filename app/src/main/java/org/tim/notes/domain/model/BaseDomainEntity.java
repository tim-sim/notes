package org.tim.notes.domain.model;

public interface BaseDomainEntity {
    void delete();
    BaseDomainEntity save();
}
