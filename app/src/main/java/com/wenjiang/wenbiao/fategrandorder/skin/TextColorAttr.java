package com.wenjiang.wenbiao.fategrandorder.skin;

import android.view.View;
import android.widget.TextView;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;

public class TextColorAttr extends SkinAttr {

	@Override
	public void apply(View view) {
		if(view instanceof TextView){
			TextView tv = (TextView)view;
			if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
				Logger.e("attr1", "TextColorAttr");
				tv.setTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
			}
		}
	}
}
