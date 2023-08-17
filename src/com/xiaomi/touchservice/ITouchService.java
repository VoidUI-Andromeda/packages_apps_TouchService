package com.xiaomi.touchservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ITouchService extends IInterface {
    int[] getModeValues(int i, int i2) throws RemoteException;

    int getTouchModeCurValue(int i, int i2) throws RemoteException;

    int getTouchModeDefValue(int i, int i2) throws RemoteException;

    int getTouchModeMaxValue(int i, int i2) throws RemoteException;

    int getTouchModeMinValue(int i, int i2) throws RemoteException;

    boolean resetTouchMode(int i, int i2) throws RemoteException;

    boolean setEdgeMode(int i, int i2, List list, int i3) throws RemoteException;

    boolean setTouchMode(int i, int i2, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements ITouchService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.xiaomi.touchservice.ITouchService");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.xiaomi.touchservice.ITouchService");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.xiaomi.touchservice.ITouchService");
                return true;
            }
            switch (i) {
                case 1:
                    int readInt = parcel.readInt();
                    int readInt2 = parcel.readInt();
                    int readInt3 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    boolean touchMode = setTouchMode(readInt, readInt2, readInt3);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(touchMode);
                    break;
                case 2:
                    int readInt4 = parcel.readInt();
                    int readInt5 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int touchModeCurValue = getTouchModeCurValue(readInt4, readInt5);
                    parcel2.writeNoException();
                    parcel2.writeInt(touchModeCurValue);
                    break;
                case 3:
                    int readInt6 = parcel.readInt();
                    int readInt7 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int touchModeMaxValue = getTouchModeMaxValue(readInt6, readInt7);
                    parcel2.writeNoException();
                    parcel2.writeInt(touchModeMaxValue);
                    break;
                case 4:
                    int readInt8 = parcel.readInt();
                    int readInt9 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int touchModeMinValue = getTouchModeMinValue(readInt8, readInt9);
                    parcel2.writeNoException();
                    parcel2.writeInt(touchModeMinValue);
                    break;
                case 5:
                    int readInt10 = parcel.readInt();
                    int readInt11 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int touchModeDefValue = getTouchModeDefValue(readInt10, readInt11);
                    parcel2.writeNoException();
                    parcel2.writeInt(touchModeDefValue);
                    break;
                case 6:
                    int readInt12 = parcel.readInt();
                    int readInt13 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int[] modeValues = getModeValues(readInt12, readInt13);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(modeValues);
                    break;
                case 7:
                    int readInt14 = parcel.readInt();
                    int readInt15 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    boolean resetTouchMode = resetTouchMode(readInt14, readInt15);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(resetTouchMode);
                    break;
                case 8:
                    int readInt16 = parcel.readInt();
                    int readInt17 = parcel.readInt();
                    ArrayList readArrayList = parcel.readArrayList(getClass().getClassLoader());
                    int readInt18 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    boolean edgeMode = setEdgeMode(readInt16, readInt17, readArrayList, readInt18);
                    parcel2.writeNoException();
                    parcel2.writeBoolean(edgeMode);
                    break;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }
    }
}
