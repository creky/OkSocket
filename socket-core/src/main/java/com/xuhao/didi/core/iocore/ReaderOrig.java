package com.xuhao.didi.core.iocore;

import com.xuhao.didi.core.exceptions.ReadException;
import com.xuhao.didi.core.iocore.interfaces.IOAction;
import com.xuhao.didi.core.pojo.OriginalData;

/**
 * Created by Steven Hua on 2020/5/31.
 */
public class ReaderOrig extends AbsReader {
    @Override
    public void read() throws RuntimeException {
        try {
            byte[] bytes = new byte[512];
            int len;
            while ((len = mInputStream.read(bytes)) > 0) {
                OriginalData originalData = new OriginalData();
                byte[] bts = new byte[len];
                System.arraycopy(bytes, 0, bts, 0, len);
                originalData.setBodyBytes(bts);
                mStateSender.sendBroadcast(IOAction.ACTION_READ_COMPLETE, originalData);
            }
        } catch (Exception e) {
            throw new ReadException(e);
        }
    }
}