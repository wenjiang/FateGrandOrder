package com.wenjiang.wenbiao.fategrandorder.skin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Skin Manager Instance
 *
 *
 * <ul>
 * <strong>get single runtime instance</strong>
 * <li> {@link #getInstance()} </li>
 * </ul>
 * <ul>
 * <strong>detach a listener (Activity or fragment) to SkinManager</strong>
 * <li> {@link #detach(ISkinUpdate observer)} </li>
 * </ul>
 * <ul>
 * <strong>load latest theme </strong>
 * <li> {@link #load()} </li>
 * <li> {@link #load(ILoaderListener callback)} </li>
 * </ul>
 * <ul>
 * <strong>load new theme with the giving skinPackagePath</strong>
 * <li> {@link #load(String skinPackagePath,ILoaderListener callback)} </li>
 * </ul>
 * 
 * 
 * @author fengjun
 */
public class SkinManager implements ISkinLoader{
	
	private static final String NOT_INIT_ERROR = "SkinManager MUST init with Context first";
	private static Object synchronizedLock = new Object();
	private static SkinManager instance;

	private List<ISkinUpdate> skinObservers;
	private Context context;
	private String skinPackageName;
	private Resources mResources;
	private String skinPath;
	private boolean isDefaultSkin = false;
	
	/**
	 * whether the skin being used is from external .skin file 
	 * @return is external skin = true
	 */
	public boolean isExternalSkin(){
		return !isDefaultSkin && mResources != null;
	}
	
	/**
	 * get current skin path
	 * @return current skin path
	 */
	public String getSkinPath() {
		return skinPath;
	}

	/**
	 * return a global static instance of {@link SkinManager}
	 * @return
	 */
	public static SkinManager getInstance() {
		if (instance == null) {
			synchronized (synchronizedLock) {
				if (instance == null){
					instance = new SkinManager();
				}
			}
		}
		return instance;
	}
	
	public String getSkinPackageName() {
		return skinPackageName;
	}
	
	public Resources getResources(){
		return mResources;
	}
	
	private SkinManager() { }
	
	public void init(Context ctx){
		context = ctx.getApplicationContext();
	}
	
	public void restoreDefaultTheme(){
		isDefaultSkin = true;
		mResources = context.getResources();
		notifySkinUpdate();
	}

	public void load(){
		load(SkinConfig.SKIN_DIR, null);
	}
	
	public void load(ILoaderListener callback){
		load(SkinConfig.SKIN_DIR, callback);
	}
	
	/**
	 * Load resources from apk in asyc task
	 * @param skinPackagePath path of skin apk
	 * @param callback callback to notify user
	 */
	public void load(String skinPackagePath, final ILoaderListener callback) {
		
		new AsyncTask<String, Void, Resources>() {

			protected void onPreExecute() {
				if (callback != null) {
					callback.onStart();
				}
			};

			@Override
			protected Resources doInBackground(String... params) {
				try {
					if (params.length == 1) {
						String skinPkgPath = params[0];
						
						File file = new File(skinPkgPath);
						if(file == null || !file.exists()){
							return null;
						}

						PackageManager mPm = context.getPackageManager();
						PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
						if(mInfo == null){
							return null;
						}
						skinPackageName = mInfo.packageName;

						AssetManager assetManager = AssetManager.class.newInstance();
						Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
						addAssetPath.invoke(assetManager, skinPkgPath);

						Resources superRes = context.getResources();
						Resources skinResource = new Resources(assetManager,superRes.getDisplayMetrics(),superRes.getConfiguration());
						skinPath = skinPkgPath;
						isDefaultSkin = false;
						return skinResource;
					}
					return null;
				} catch (Exception e) {
					Logger.e(e.toString());
					return null;
				}
			};

			protected void onPostExecute(Resources result) {
				mResources = result;

				if (mResources != null) {
					if (callback != null) callback.onSuccess();
					notifySkinUpdate();
				}else{
					isDefaultSkin = true;
					if (callback != null) callback.onFailed();
				}
			};

		}.execute(skinPackagePath);
	}
	
	@Override
	public void attach(ISkinUpdate observer) {
		if(skinObservers == null){
			skinObservers = new ArrayList<ISkinUpdate>();
		}
		if(!skinObservers.contains(observer)){
			skinObservers.add(observer);
		}
	}

	@Override
	public void detach(ISkinUpdate observer) {
		if(skinObservers == null) return;
		if(skinObservers.contains(observer)){
			skinObservers.remove(observer);
		}
	}

	@Override
	public void notifySkinUpdate() {
		if(skinObservers == null) return;
		for(ISkinUpdate observer : skinObservers){
			observer.onThemeUpdate();
		}
	}
	
	public int getColor(int resId){
		int originColor = context.getResources().getColor(resId);
		if(mResources == null || isDefaultSkin){
			return originColor;
		}
		
		String resName = context.getResources().getResourceEntryName(resId);
		
		int trueResId = mResources.getIdentifier(resName, "color", skinPackageName);
		int trueColor = 0;
		
		try{
			trueColor = mResources.getColor(trueResId);
		}catch(NotFoundException e){
			e.printStackTrace();
			trueColor = originColor;
		}
		
		return trueColor;
	}

    public float getDimension(int resId){
        float originDimension = context.getResources().getDimension(resId);
        if(mResources == null || isDefaultSkin){
            return originDimension;
        }

        String resName = context.getResources().getResourceEntryName(resId);

        int trueResId = mResources.getIdentifier(resName, "dimension", skinPackageName);
        float trueDimension = 0;

        try{
            trueDimension = mResources.getDimensionPixelOffset(trueResId);
        }catch(NotFoundException e){
            e.printStackTrace();
            trueDimension = originDimension;
        }

        return trueDimension;
    }
	
	@SuppressLint("NewApi")
	public Drawable getDrawable(int resId){
		Drawable originDrawable = context.getResources().getDrawable(resId);
		if(mResources == null || isDefaultSkin){
			return originDrawable;
		}
		String resName = context.getResources().getResourceEntryName(resId);
		
		int trueResId = mResources.getIdentifier(resName, "drawable", skinPackageName);
		
		Drawable trueDrawable = null;
		try{
			Logger.e("ttgg", "SDK_INT = " + android.os.Build.VERSION.SDK_INT);
			if(android.os.Build.VERSION.SDK_INT < 22){
				trueDrawable = mResources.getDrawable(trueResId);
			}else{
				trueDrawable = mResources.getDrawable(trueResId, null);
			}
		}catch(NotFoundException e){
			e.printStackTrace();
			trueDrawable = originDrawable;
		}
		
		return trueDrawable;
	}
	
	/**
	 * 加载指定资源颜色drawable,转化为ColorStateList，保证selector类型的Color也能被转换。</br>
	 * 无皮肤包资源返回默认主题颜色
	 * @author pinotao
	 * @param resId
	 * @return
	 */
	public ColorStateList convertToColorStateList(int resId) {
		Logger.e("attr1", "convertToColorStateList");
		
		boolean isExtendSkin = true;
		
		if (mResources == null || isDefaultSkin) {
			isExtendSkin = false;
		}
		
		String resName = context.getResources().getResourceEntryName(resId);
		Logger.e("attr1", "resName = " + resName);
		if (isExtendSkin) {
			Logger.e("attr1", "isExtendSkin");
			int trueResId = mResources.getIdentifier(resName, "color", skinPackageName);
			Logger.e("attr1", "trueResId = " + trueResId);
			ColorStateList trueColorList = null;
			if (trueResId == 0) { // 如果皮肤包没有复写该资源，但是需要判断是否是ColorStateList
				try {
					ColorStateList originColorList = context.getResources().getColorStateList(resId);
					return originColorList;
				} catch (NotFoundException e) {
					e.printStackTrace();
					Logger.e("resName = " + resName + " NotFoundException : "+ e.getMessage());
				}
			} else {
				try {
					trueColorList = mResources.getColorStateList(trueResId);
					Logger.e("attr1", "trueColorList = " + trueColorList);
					return trueColorList;
				} catch (NotFoundException e) {
					e.printStackTrace();
					Logger.w("resName = " + resName + " NotFoundException :" + e.getMessage());
				}
			}
		} else {
			try {
				ColorStateList originColorList = context.getResources().getColorStateList(resId);
				return originColorList;
			} catch (NotFoundException e) {
				e.printStackTrace();
				Logger.w("resName = " + resName + " NotFoundException :" + e.getMessage());
			}

		}

		int[][] states = new int[1][1];
		return new ColorStateList(states, new int[] { context.getResources().getColor(resId) });
	}
}