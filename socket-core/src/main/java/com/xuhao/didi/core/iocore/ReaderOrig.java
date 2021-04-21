package com.xuhao.didi.core.iocore;

import com.xuhao.didi.core.iocore.interfaces.IOAction;
import com.xuhao.didi.core.pojo.OriginalData;

/**
 * Created by Steven Hua on 2020/5/31.
 */
public class ReaderOrig extends AbsReader {
    @Override
    public void read() {
        try {
            int len = mInputStream.available();
            if (len <= 0) {
                Thread.sleep(200);
                return;
            }
            byte[] body = new byte[len];
            mInputStream.read(body);
            OriginalData originalData = new OriginalData();
            originalData.setBodyBytes(body);
            mStateSender.sendBroadcast(IOAction.ACTION_READ_COMPLETE, originalData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}