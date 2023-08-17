package com.xiaomi.touchservice;

import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Slog;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ITouchFeature {
    private static volatile ITouchFeature INSTANCE;
    private int mServiceVersion;
    private int mTouchFeatureProperties = SystemProperties.getInt("ro.vendor.touchfeature.type", 0);

    public static ITouchFeature getInstance() {
        if (INSTANCE == null) {
            synchronized (ITouchFeature.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ITouchFeature();
                }
            }
        }
        return INSTANCE;
    }

    public int getSupportTouchFeatureVersion() {
        if (this.mServiceVersion != 0) {
            Slog.i("TouchService-ITouchFeature", "current device and process support version:" + this.mServiceVersion);
            return this.mServiceVersion;
        }
        try {
            if (HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default") != null) {
                Slog.i("TouchService-ITouchFeature", "current device and process support v2 service");
                return 2;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e2) {
            Slog.e("TouchService-ITouchFeature", e2.toString());
        }
        try {
            if (HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default") != null) {
                Slog.i("TouchService-ITouchFeature", "current device and process support v1 service");
                return 1;
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
        } catch (NoSuchElementException e4) {
            Slog.e("TouchService-ITouchFeature", e4.toString());
        }
        Slog.e("TouchService-ITouchFeature", "current device and process not support, v1/v2 service not found");
        return 0;
    }

    private ITouchFeature() {
        this.mServiceVersion = 0;
        this.mServiceVersion = getSupportTouchFeatureVersion();
    }

    public boolean setTouchMode(int i, int i2) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(1, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 != 0) {
                            Slog.e("TouchService-ITouchFeature", "setTouchMode failed. ret = " + readInt32);
                            return false;
                        }
                        return true;
                    }
                } catch (NoSuchElementException e) {
                    Slog.e("TouchService-ITouchFeature", e.toString());
                }
            } catch (RemoteException e2) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e2);
            }
            Slog.e("TouchService-ITouchFeature", "setTouchMode failed.");
            return false;
        } finally {
            hwParcel.release();
        }
    }

    public boolean setTouchMode(int i, int i2, int i3) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return setTouchMode(i2, i3);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        hwParcel2.writeInt32(i3);
                        service.transact(1, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 != 0) {
                            Slog.e("TouchService-ITouchFeature", "setTouchMode failed. ret = " + readInt32);
                            return false;
                        }
                        return true;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "setTouchMode failed.");
                return false;
            } finally {
                hwParcel.release();
            }
        }
        return false;
    }

    public int getTouchModeCurValue(int i) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    service.transact(2, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    int readInt32 = hwParcel.readInt32();
                    if (readInt32 < 0) {
                        Slog.e("TouchService-ITouchFeature", "getTouchModeCurValue failed. ret = " + readInt32);
                        return -1;
                    }
                    return readInt32;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "getTouchModeCurValue failed.");
            return -1;
        } finally {
            hwParcel.release();
        }
    }

    public int getTouchModeCurValue(int i, int i2) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return getTouchModeCurValue(i2);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(2, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 < 0) {
                            Slog.e("TouchService-ITouchFeature", "getTouchModeCurValue failed. ret = " + readInt32);
                            return -1;
                        }
                        return readInt32;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "getTouchModeCurValue failed.");
                return -1;
            } finally {
                hwParcel.release();
            }
        }
        return -1;
    }

    public int getTouchModeMaxValue(int i) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    service.transact(3, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    int readInt32 = hwParcel.readInt32();
                    if (readInt32 < 0) {
                        Slog.e("TouchService-ITouchFeature", "getTouchModeMaxValue failed. ret = " + readInt32);
                        return -1;
                    }
                    return readInt32;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "getTouchModeMaxValue failed.");
            return -1;
        } finally {
            hwParcel.release();
        }
    }

    public int getTouchModeMaxValue(int i, int i2) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return getTouchModeMaxValue(i2);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(3, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 < 0) {
                            Slog.e("TouchService-ITouchFeature", "getTouchModeMaxValue failed. ret = " + readInt32);
                            return -1;
                        }
                        return readInt32;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "getTouchModeMaxValue failed.");
                return -1;
            } finally {
                hwParcel.release();
            }
        }
        return -1;
    }

    public int getTouchModeMinValue(int i) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    service.transact(4, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    int readInt32 = hwParcel.readInt32();
                    if (readInt32 < 0) {
                        Slog.e("TouchService-ITouchFeature", "getTouchModeMinValue failed. ret = " + readInt32);
                        return -1;
                    }
                    return readInt32;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "getTouchModeMinValue failed.");
            return -1;
        } finally {
            hwParcel.release();
        }
    }

    public int getTouchModeMinValue(int i, int i2) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return getTouchModeMinValue(i2);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(4, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 < 0) {
                            Slog.e("TouchService-ITouchFeature", "getTouchModeMinValue failed. ret = " + readInt32);
                            return -1;
                        }
                        return readInt32;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "getTouchModeMinValue failed.");
                return -1;
            } finally {
                hwParcel.release();
            }
        }
        return -1;
    }

    public int getTouchModeDefValue(int i) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    service.transact(5, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    int readInt32 = hwParcel.readInt32();
                    if (readInt32 < 0) {
                        Slog.e("TouchService-ITouchFeature", "getTouchModeDefValue failed. ret = " + readInt32);
                        return -1;
                    }
                    return readInt32;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "getTouchModeDefValue failed.");
            return -1;
        } finally {
            hwParcel.release();
        }
    }

    public int getTouchModeDefValue(int i, int i2) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return getTouchModeDefValue(i2);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(5, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 < 0) {
                            Slog.e("TouchService-ITouchFeature", "getTouchModeDefValue failed. ret = " + readInt32);
                            return -1;
                        }
                        return readInt32;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "getTouchModeDefValue failed.");
                return -1;
            } finally {
                hwParcel.release();
            }
        }
        return -1;
    }

    public int[] getModeValues(int i) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    service.transact(7, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    ArrayList readInt32Vector = hwParcel.readInt32Vector();
                    int size = readInt32Vector.size();
                    int[] iArr = new int[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = ((Integer) readInt32Vector.get(i2)).intValue();
                    }
                    return iArr;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "getModeValues failed.");
            return new int[4];
        } finally {
            hwParcel.release();
        }
    }

    public int[] getModeValues(int i, int i2) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return getModeValues(i2);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(7, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        ArrayList readInt32Vector = hwParcel.readInt32Vector();
                        int size = readInt32Vector.size();
                        int[] iArr = new int[size];
                        for (int i3 = 0; i3 < size; i3++) {
                            iArr[i3] = ((Integer) readInt32Vector.get(i3)).intValue();
                        }
                        return iArr;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "getModeValues failed.");
                return new int[4];
            } finally {
                hwParcel.release();
            }
        }
        return new int[4];
    }

    public boolean resetTouchMode(int i) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    service.transact(6, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    int readInt32 = hwParcel.readInt32();
                    if (readInt32 == 0) {
                        return true;
                    }
                    Slog.e("TouchService-ITouchFeature", "resetTouchMode failed. ret = " + readInt32);
                    return false;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "resetTouchMode failed.");
            return false;
        } finally {
            hwParcel.release();
        }
    }

    public boolean resetTouchMode(int i, int i2) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return resetTouchMode(i2);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                    if (service != null) {
                        HwParcel hwParcel2 = new HwParcel();
                        hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                        hwParcel2.writeInt32(i);
                        hwParcel2.writeInt32(i2);
                        service.transact(6, hwParcel2, hwParcel, 0);
                        hwParcel.verifySuccess();
                        hwParcel2.releaseTemporaryStorage();
                        int readInt32 = hwParcel.readInt32();
                        if (readInt32 != 0) {
                            Slog.e("TouchService-ITouchFeature", "resetTouchMode failed. ret = " + readInt32);
                            return false;
                        }
                        return true;
                    }
                } catch (RemoteException e) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
                } catch (NoSuchElementException e2) {
                    Slog.e("TouchService-ITouchFeature", e2.toString());
                }
                Slog.e("TouchService-ITouchFeature", "resetTouchMode failed.");
                return false;
            } finally {
                hwParcel.release();
            }
        }
        return false;
    }

    public boolean setEdgeMode(int i, ArrayList<Integer> arrayList, int i2) {
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hardware.touchfeature@1.0::ITouchFeature");
                    hwParcel2.writeInt32(i);
                    hwParcel2.writeInt32(i2);
                    hwParcel2.writeInt32Vector(arrayList);
                    service.transact(8, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    int readInt32 = hwParcel.readInt32();
                    if (readInt32 == 0) {
                        return true;
                    }
                    Slog.e("TouchService-ITouchFeature", "setEdgeMode failed. ret = " + readInt32);
                    return false;
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "setEdgeMode failed.");
            return false;
        } finally {
            hwParcel.release();
        }
    }

    public boolean setEdgeMode(int i, int i2, ArrayList<Integer> arrayList, int i3) {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion != 0) {
            if (supportTouchFeatureVersion == 1) {
                return setEdgeMode(i2, arrayList, i3);
            }
            HwParcel hwParcel = new HwParcel();
            try {
                try {
                    try {
                        IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                        if (service != null) {
                            HwParcel hwParcel2 = new HwParcel();
                            hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                            hwParcel2.writeInt32(i);
                            hwParcel2.writeInt32(i2);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32Vector(arrayList);
                            service.transact(8, hwParcel2, hwParcel, 0);
                            hwParcel.verifySuccess();
                            hwParcel2.releaseTemporaryStorage();
                            int readInt32 = hwParcel.readInt32();
                            if (readInt32 != 0) {
                                Slog.e("TouchService-ITouchFeature", "setEdgeMode failed. ret = " + readInt32);
                                return false;
                            }
                            return true;
                        }
                    } catch (NoSuchElementException e) {
                        Slog.e("TouchService-ITouchFeature", e.toString());
                    }
                } catch (RemoteException e2) {
                    Slog.e("TouchService-ITouchFeature", "transact failed. " + e2);
                }
                Slog.e("TouchService-ITouchFeature", "setEdgeMode failed.");
                return false;
            } finally {
                hwParcel.release();
            }
        }
        return false;
    }

    public String getTouchEvent() {
        int supportTouchFeatureVersion = getSupportTouchFeatureVersion();
        if (supportTouchFeatureVersion == 0 || supportTouchFeatureVersion == 1) {
            return null;
        }
        HwParcel hwParcel = new HwParcel();
        try {
            try {
                IHwBinder service = HwBinder.getService("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature", "default");
                if (service != null) {
                    HwParcel hwParcel2 = new HwParcel();
                    hwParcel2.writeInterfaceToken("vendor.xiaomi.hw.touchfeature@1.0::ITouchFeature");
                    service.transact(9, hwParcel2, hwParcel, 0);
                    hwParcel.verifySuccess();
                    hwParcel2.releaseTemporaryStorage();
                    return hwParcel.readString();
                }
            } catch (RemoteException e) {
                Slog.e("TouchService-ITouchFeature", "transact failed. " + e);
            } catch (NoSuchElementException e2) {
                Slog.e("TouchService-ITouchFeature", e2.toString());
            }
            Slog.e("TouchService-ITouchFeature", "getTouchEvent failed.");
            return null;
        } finally {
            hwParcel.release();
        }
    }
}
