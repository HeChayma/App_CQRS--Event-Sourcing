package com.example.accountservice.commonapi.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseEvent<T> {
    @Getter
    protected T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
