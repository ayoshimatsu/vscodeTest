package com.yoshimatsu;

import java.io.Serializable;

public class MessageSample implements Serializable{
    private final String oName;
    private final byte[] oData;

    MessageSample(String aName, byte[] aData) {
        oName = aName;
        oData = aData;
    }

    String getName() {
        return oName;
    }

    byte[] getData() {
        return oData;
    }

}
