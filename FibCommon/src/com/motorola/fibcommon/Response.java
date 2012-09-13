package com.motorola.fibcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {
	private long result;
	private long time;

	public Response(long result, long time) {
		super();
		this.result = result;
		this.time = time;
	}

	// --- Parcelable stuff ---
	public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
		@Override
		public Response createFromParcel(Parcel source) {
			return new Response(source.readLong(), source.readLong());
		}

		@Override
		public Response[] newArray(int size) {
			return new Response[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(result);
		dest.writeLong(time);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	// --- Setters and getters ---
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}
}
