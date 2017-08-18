package co.kr.sky.obj;

import android.os.Parcel;
import android.os.Parcelable;



public class Loginobj implements Parcelable{

	private String id , pw;  
	public static Parcelable.Creator<Loginobj> getCreator() {
		return CREATOR;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Loginobj(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	public Loginobj() {
		super();
	}

	public Loginobj(Parcel in) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(id);
		dest.writeString(pw);
	}
	private void readFromParcel(Parcel in){
		
		id = in.readString();
		pw = in.readString();

	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator<Loginobj> CREATOR = new Parcelable.Creator() {
		public Object createFromParcel(Parcel in) {
			return new Loginobj(in);
		}

		public Object[] newArray(int size) {
			return new Loginobj[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}