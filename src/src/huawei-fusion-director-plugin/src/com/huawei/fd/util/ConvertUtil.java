/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.util;

import com.huawei.fd.api.exception.FusionDirectorException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConvertUtil
 *
 * @since 2019-02-18
 */
public class ConvertUtil {
    private static final Logger LOGGER = Logger.getLogger(ConvertUtil.class.getSimpleName());

    /**
     * 判断字符串是否是数字
     *
     * @param text 待判定字符串
     * @return boolean
     */
    public static boolean isNumeric(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }

        return text.matches("^[0-9]+$");
    }

    /**
     * 健康状态适配
     *
     * @param health 健康状态
     * @return 适配后的健康状态
     */
    public static String convertHealth(String health) {
        if (health == null) {
            return "Unknown";
        }

        if (health.matches("^(OK|Warning|Unknown|Immediate|Critical)")) {
            return health;
        } else {
            return "Unknown";
        }
    }

    /**
     * 对象深拷贝
     *
     * @param src 源对象
     * @param <T> T
     * @return 拷贝后的对象
     * @throws FusionDirectorException 异常
     */
    public static <T> T deepClone(T src) throws FusionDirectorException {
        if (src == null) {
            return null;
        }

        T obj = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(src);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            obj = (T) ois.readObject();
            ois.close();
        } catch (IOException e) {
            throw new FusionDirectorException("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new FusionDirectorException("ClassNotFoundException: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ignored) {
                    LOGGER.log(Level.WARNING, "ignore error");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ignored) {
                    LOGGER.log(Level.WARNING, "ignore error");
                }
            }
        }
        return obj;
    }
}
