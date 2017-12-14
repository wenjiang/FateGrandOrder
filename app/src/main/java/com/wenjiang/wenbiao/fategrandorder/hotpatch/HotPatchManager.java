package com.wenjiang.wenbiao.fategrandorder.hotpatch;

/**
 * Created by weber_zheng on 2017/12/12.
 */

public final class HotPatchManager {
    private static HotPatchManager hotPatchManager;

    public static HotPatchManager getInstance() {
        if(hotPatchManager == null){
            hotPatchManager = new HotPatchManager();
        }

        return hotPatchManager;
    }

    public void getTopTabListener(String name) {

    }
}
