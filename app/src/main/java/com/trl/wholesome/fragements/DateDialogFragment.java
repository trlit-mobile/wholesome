package com.trl.wholesome.fragements;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DateDialogFragment extends DialogFragment {

	public static String TAG = "DateDialogFragment";
	static Context mContext; // I guess hold the context that called it. Needed
								// when making a DatePickerDialog. I guess its
								// needed when conncting the fragment with the
								// context
	public static int mYear;
	public static int mMonth;
	public static int mDay;
	public static DateDialogFragmentListener mListener;

	public static DateDialogFragment newInstance(Context context,
												 DateDialogFragmentListener listener, Calendar now) {
		DateDialogFragment dialog = new DateDialogFragment();
		mContext = context;
		mListener = listener;

		mYear = now.get(Calendar.YEAR);
		mMonth = now.get(Calendar.MONTH);
		mDay = now.get(Calendar.DAY_OF_MONTH);
		/* I dont really see the purpose of the below */
		Bundle args = new Bundle();
		args.putString("title", "Set Date");
		dialog.setArguments(args);// setArguments can only be called before
									// fragment is attached to an activity, so
									// right after the fragment is created

		return dialog;
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new DatePickerDialog(mContext, mDateSetListener, mYear, mMonth,
				mDay);
	}

	public DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

			mListener.updateChangedDate(year, monthOfYear, dayOfMonth);
		}
	};

	public interface DateDialogFragmentListener{
		//this interface is a listener between the Date Dialog fragment and the activity to update the buttons date
		public void updateChangedDate(int year, int month, int day);
	}
   
}
