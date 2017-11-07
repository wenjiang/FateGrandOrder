package com.wenjiang.wenbiao.fategrandorder.skin;

import android.view.View;
import android.widget.AbsListView;

public class ListSelectorAttr extends SkinAttr {

	@Override
	public void apply(View view) {
		if(view instanceof AbsListView){
			AbsListView tv = (AbsListView)view;
			
			if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
				tv.setSelector(SkinManager.getInstance().getColor(attrValueRefId));
			}else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)){
				tv.setSelector(SkinManager.getInstance().getDrawable(attrValueRefId));
			}
		}
	}
}
