package com.jadeon.iterator;

/**
 * @author WooKong
 */
public interface Iterator {
    /**
     * 是否存在下一个元素
     * @return boolean
     */
   boolean hasNext();

    /**
     * 获取下一个元素
     * @return Object
     */
   Object next();

}
