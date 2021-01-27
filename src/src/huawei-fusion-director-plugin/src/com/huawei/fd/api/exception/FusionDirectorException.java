/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.exception;

/**
 * FusionDirectorException
 *
 * @since 2019-02-18
 */
public class FusionDirectorException extends Exception {
    public FusionDirectorException(String message) {
        super(message);
    }

    public FusionDirectorException(Exception err) {
        super(err);
    }
}
