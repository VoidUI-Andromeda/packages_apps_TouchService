package com.xiaomi.touchservice;

import com.xiaomi.touchservice.ITouchService;
import java.util.ArrayList;
import java.util.List;

public class ITouchServiceStubImpl extends ITouchService.Stub {
    private ITouchFeature mTouchFeature = ITouchFeature.getInstance();

    @Override // com.xiaomi.touchservice.ITouchService
    public boolean setTouchMode(int i, int i2, int i3) {
        return this.mTouchFeature.setTouchMode(i, i2, i3);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public int getTouchModeCurValue(int i, int i2) {
        return this.mTouchFeature.getTouchModeCurValue(i, i2);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public int getTouchModeMaxValue(int i, int i2) {
        return this.mTouchFeature.getTouchModeMaxValue(i, i2);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public int getTouchModeMinValue(int i, int i2) {
        return this.mTouchFeature.getTouchModeMinValue(i, i2);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public int getTouchModeDefValue(int i, int i2) {
        return this.mTouchFeature.getTouchModeDefValue(i, i2);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public int[] getModeValues(int i, int i2) {
        return this.mTouchFeature.getModeValues(i, i2);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public boolean resetTouchMode(int i, int i2) {
        return this.mTouchFeature.resetTouchMode(i, i2);
    }

    @Override // com.xiaomi.touchservice.ITouchService
    public boolean setEdgeMode(int i, int i2, List list, int i3) {
        return this.mTouchFeature.setEdgeMode(i, i2, new ArrayList<>(list), i3);
    }
}
