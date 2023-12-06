package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent{
    private static final long serialVersionUID = 1L;
    //
    private Asset message;

    public DomainEvent(Object source) {
        super(source);
    }

    public DomainEvent(Object source, Asset message) {
        super(source);
        this.message = message;
    }

    public Asset getMessage() {
        return message;
    }
}