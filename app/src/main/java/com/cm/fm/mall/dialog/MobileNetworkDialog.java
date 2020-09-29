package com.cm.fm.mall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cm.fm.mall.BuildConfig;
import com.cm.fm.mall.R;
import com.cm.fm.mall.util.CheckUpdateUtil;
import com.cm.fm.mall.util.LogUtil;

/**
 * 移动网络弹框
 */
public class MobileNetworkDialog extends Dialog implements View.OnClickListener {
    Context context;
    TextView tv_cancel_update,tv_sure_update;

    private final String tag = "TAG_MobileNetworkDialog";
    public MobileNetworkDialog(Context context, int style){
        super(context,style);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View tip_view = View.inflate(context,R.layout.layout_network_tip,null);
        setContentView(tip_view);
        //TODO 通过 Window 设置在整个窗口显示在底部
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = 50;  //相当于设置了  android:layout_marginBottom="25dp"
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);

        tv_cancel_update = tip_view.findViewById(R.id.tv_cancel_update);
        tv_sure_update = tip_view.findViewById(R.id.tv_sure_update);

        //下次再说
        tv_cancel_update.setOnClickListener(this);
        //现在更新
        tv_sure_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel_update:
                LogUtil.d(tag,"点击了取消");
                MobileNetworkDialog.this.dismiss();
                break;
            case R.id.tv_sure_update:
                LogUtil.d(tag,"点击了继续");
                MobileNetworkDialog.this.dismiss();
                //开始下载
                CheckUpdateUtil.getInstance().downloadApk(context);
                break;
        }
    }
}