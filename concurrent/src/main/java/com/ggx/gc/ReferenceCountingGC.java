package com.ggx.gc;

/**
 * 引用计数无法解决的互相引用问题
 */
public class ReferenceCountingGC {
    Object instance = null;

    public static void main(String[] args) {
        ReferenceCountingGC referenceA = new ReferenceCountingGC();
        ReferenceCountingGC referenceB = new ReferenceCountingGC();
        referenceA.instance = referenceB;
        referenceB.instance = referenceA;
        referenceA = null;
        referenceB = null;
    }

}
