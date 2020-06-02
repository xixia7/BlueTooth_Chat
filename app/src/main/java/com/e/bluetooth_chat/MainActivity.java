package com.e.bluetooth_chat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment mTab01 = new weixinFragment();
    private Fragment mTab02 = new friendFragment();
    private Fragment mTab03 = new contactFragment();
    private Fragment mTab04 = new settingsFragment();
    private Fragment Tab_add = new add();

    private MyDAO myDAO;
    private List<Map<String, Object>> listData;
    private Map<String, Object> listItem;

    private EditText e_name = null;
    private EditText e_tel = null;

    private FragmentManager fm;

    private LinearLayout mTabweixin;
    private LinearLayout mTabfrd;
    private LinearLayout mTabcontact;
    private LinearLayout mTabsettings;
    private LinearLayout mBtnAdd;

    private ImageButton mImgweixin;
    private ImageButton mImgfrd;
    private ImageButton mImgcontact;
    private ImageButton mImgsettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
        initEvent();
        setSelect(0);

        initDao();
        initc_List();
    }


    private void initDao() {
        myDAO = new MyDAO(this);
    }

    private void initView() {
        mTabweixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabfrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabcontact = (LinearLayout) findViewById(R.id.id_tab_contact);
        mTabsettings = (LinearLayout) findViewById(R.id.id_tab_settings);
        mBtnAdd = (LinearLayout) findViewById(R.id.tab_btn_add);

        mImgweixin = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mImgfrd = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mImgcontact = (ImageButton) findViewById(R.id.id_tab_contact_img);
        mImgsettings = (ImageButton) findViewById(R.id.id_tab_settings_img);
    }

    private void initc_RecyclerView(){
        contact_adapter cAdapter = new contact_adapter(this);
        RecyclerView recyclerView = findViewById(R.id.c_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cAdapter);

        cAdapter.initDataList(listData);
    }

    private void initc_List() {
        listData = new ArrayList<Map<String, Object>>();
        Cursor cursor = myDAO.allQuery();
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            String tel = cursor.getString(2);
            listItem = new HashMap<String, Object>();
            listItem.put("name",name);
            listItem.put("tel",tel);
            listData.add(listItem);
        }


    }
    private void initFragment() {
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content, mTab01);
        transaction.add(R.id.id_content, mTab02);
        transaction.add(R.id.id_content, mTab03);
        transaction.add(R.id.id_content, mTab04);
        transaction.add(R.id.id_content, Tab_add);
        transaction.commit();
    }


    private void initEvent() {
        mTabweixin.setOnClickListener(this);
        mTabfrd.setOnClickListener(this);
        mTabcontact.setOnClickListener(this);
        mTabsettings.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
    }

    private void addContact(){
        String p1 = e_name.getText().toString();
        String p2 = e_tel.getText().toString();
    }

    private void setSelect(int i) {
        String s = String.valueOf(i);
        Log.d("select", "s");
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                Log.d("setSelect", "1");
                transaction.show(mTab01);
                mImgweixin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(mTab02);
                mImgfrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                initc_List();
                initc_RecyclerView();
                transaction.show(mTab03);
                mImgcontact.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImgsettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
            case 4:
                transaction.show(Tab_add);
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        Log.d("hide", "1");
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
        transaction.hide(Tab_add);
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);
                break;
            case R.id.id_tab_contact:
                setSelect(2);
                break;
            case R.id.id_tab_settings:
                setSelect(3);
                break;
            default:
                break;
        }
    }

    private void resetImgs() {
        mImgweixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgfrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgcontact.setImageResource(R.drawable.tab_address_normal);
        mImgsettings.setImageResource(R.drawable.tab_settings_normal);
    }




}



