/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/marko/workspaces/motorola/class-2661/LogCommon/src/com/motorola/logcommon/ILogService.aidl
 */
package com.motorola.logcommon;
public interface ILogService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.motorola.logcommon.ILogService
{
private static final java.lang.String DESCRIPTOR = "com.motorola.logcommon.ILogService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.motorola.logcommon.ILogService interface,
 * generating a proxy if needed.
 */
public static com.motorola.logcommon.ILogService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.motorola.logcommon.ILogService))) {
return ((com.motorola.logcommon.ILogService)iin);
}
return new com.motorola.logcommon.ILogService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_log:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
this.log(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.motorola.logcommon.ILogService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void log(int priority, java.lang.String tag, java.lang.String msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(priority);
_data.writeString(tag);
_data.writeString(msg);
mRemote.transact(Stub.TRANSACTION_log, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_log = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void log(int priority, java.lang.String tag, java.lang.String msg) throws android.os.RemoteException;
}
