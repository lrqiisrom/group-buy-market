package com.rom.types.design.framework.link.model2.chain;

/**
 * 链表接口
 */
public interface ILink<E> {
    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(Object o);

    E get(int index);

    void printLinkList();

}
