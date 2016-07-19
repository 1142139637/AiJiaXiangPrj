package com.lovehome.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class DefinedEdictor extends EditText {
	Drawable[] drawable ;
	public DefinedEdictor(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init() ;
	}

	public DefinedEdictor(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public DefinedEdictor(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public void  init(){
		drawable = getCompoundDrawables();
		setCompoundDrawables(drawable[0], drawable[1], null, drawable[3]);
		//聚焦事件
		setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					if(getText().toString().length()>0)
						setCompoundDrawables(drawable[0], drawable[1], drawable[2], drawable[3]);
					else
						setCompoundDrawables(drawable[0], drawable[1], null, drawable[3]);
				}else{
					setCompoundDrawables(drawable[0], drawable[1], null, drawable[3]);
						
				}
			}
		});
		//监听内容的事件
		addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.toString().length()>0){
					setCompoundDrawables(drawable[0], drawable[1], drawable[2], drawable[3]);
				}else{
					setCompoundDrawables(drawable[0], drawable[1], null, drawable[3]);
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		//删除的触摸事件
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getX()<(getWidth()-getPaddingRight()) &&
						event.getX()>(getWidth()-getTotalPaddingRight())){
					if(event.getAction() == MotionEvent.ACTION_UP){
						setText("");
					}
				}
				return false;
			}
		});
		
	}
	
}
