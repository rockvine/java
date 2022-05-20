package com.rockvine.kernel.core.model;

import org.apache.commons.lang3.builder.*;

import java.io.Serializable;

/**
 * @author rocky
 * @date 2022-05-19 22:21
 * @description 抽象可持久化类
 */
public abstract class AbstractPersistable implements Serializable {
    private static final long serialVersionUID = -2587016856668951095L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
