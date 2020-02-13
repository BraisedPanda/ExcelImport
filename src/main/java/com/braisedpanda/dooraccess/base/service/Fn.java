package com.braisedpanda.dooraccess.base.service;

import java.io.Serializable;
import java.util.function.Function;

public interface Fn<T, R> extends Function<T, R>, Serializable {
}