package com.webcon.sus.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webcon.sus.adapter.RVdrawerAdapter;
import com.webcon.sus.demo.R;
import com.webcon.sus.develop_test.audioTest.AudioTestActivity;
import com.webcon.sus.eventObjects.ServiceEvent;
import com.webcon.wp.utils.WPApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 侧滑菜单
 * @author m
 */
public class NavigationDrawerFragment extends Fragment {
    private static final String TAG = "Navigation";
    private NavigationDrawerCallbacks mCallback;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mContainer;
//    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private android.support.v7.widget.RecyclerView mRecyclerView;
    private android.support.v7.widget.RecyclerView.LayoutManager mLayoutManager;
    private RVdrawerAdapter mRVdrawerAdapter;

    // user image
    private com.webcon.sus.view.CircleImageView cIV;

//    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    /* ListView参数 */
//    private String[] keys = {"title", "leave"};
    private String[] keys = {"title"};
    private int[] itemIds = {R.id.list_item_text};
//    private int[] itemIds = {R.id.list_item_text, R.id.list_item_right};
    private int[] titles = {
            R.string.drawer_list_6,
            R.string.drawer_list_4,
            R.string.drawer_list_5
    };

    private String[] keys_rv = {"用户设置","注销登陆","退出"};
    // R.drawable.ic_highlight_off_black_36dp
    private int[] icons_rv = {R.drawable.ic_settings_black_36dp,R.drawable.ic_exit_to_app_black_36dp,R.drawable.ic_power_settings_new_black_36dp};

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
//            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
        selectItem(-1);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContainer = (LinearLayout)inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        //防止Touch事件透过组件
        mContainer.findViewById(R.id.navigation_drawer_head).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return true;
            }
        });

//        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
//        //------------
//        mDrawerListView = (ListView) mContainer.findViewById(R.id.navigation_drawer_list);
//        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //列表项监听回调处理
//                selectItem(position);
//            }
//        });
//        mDrawerListView.setAdapter(
//                new SimpleAdapter(
//                        getActivity(),
//                        getData(),
//                        R.layout.drawer_list_item,
//                        keys,
//                        itemIds
//                )
//        );
//        //Test for bug
//        mDrawerListView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });


        // circular imageView  // testActivity
        cIV=(com.webcon.sus.view.CircleImageView)mContainer.findViewById(R.id.c_iv);
        cIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getActivity(), AudioTestActivity.class);
                startActivity(i1);
            }
        });


        // recycler view
        // use a linear layout manager--------------------------------------------------------
        mRecyclerView=(RecyclerView) mContainer.findViewById(R.id.navigation_drawer_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRVdrawerAdapter=new RVdrawerAdapter(keys_rv,icons_rv);


        mRVdrawerAdapter.setOnItemClickLitener1(new RVdrawerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                selectItem(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });

        mRecyclerView.setAdapter(mRVdrawerAdapter);


        return mContainer;
    }

    private void fillUserInfo(){
        String userId = WPApplication.getInstance().getCurrentUser().getUserId();
        String userName = WPApplication.getInstance().getCurrentUser().getUserName();
        ((TextView)mContainer.findViewById(R.id.tv_drawer_userid)).setText(userId);
        ((TextView)mContainer.findViewById(R.id.tv_drawer_username)).setText(userName);
    }

    private List<HashMap<String, Object>> getData(){
        List<HashMap<String, Object>> mList = new ArrayList<>();
        HashMap<String, Object> map;
        for(int i : titles){
            map = new HashMap<>();
            map.put(keys[0], getString(i));
            mList.add(map);
        }
        return mList;
    }


    /**
     * 检查菜单是否已经打开
     */
    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void openDrawer(){
        if(isDrawerOpen()){
            closeDrawer();
        }else{
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
    }

    public void closeDrawer(){
        mDrawerLayout.closeDrawers();
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar mToolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                mToolbar,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
//                    SharedPreferences sp = PreferenceManager
//                            .getDefaultSharedPreferences(getActivity());
//                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch(item.getItemId()){
//                    case R.id.action_logo:
//                        break;
//                    case R.id.action_settings:
//                        break;
//                }
//                return true;
//            }
//        });

        //初始化为打开状态
//        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
//            mDrawerLayout.openDrawer(mFragmentContainerView);
//        }
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        if(position == -1){
            return;
        }
//        mCurrentSelectedPosition = position;
//        if (mDrawerListView != null) {
//            mDrawerListView.setItemChecked(position, true);
//        }

        if (mCallback != null) {
            mCallback.onNavigationDrawerItemSelected(position);
        }
//        if (mDrawerLayout != null) {
//            mDrawerLayout.closeDrawer(mFragmentContainerView);
//        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        fillUserInfo();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mDrawerLayout != null && isDrawerOpen()) {
            //侧滑菜单打开时，更换一个menu布局，
            inflater.inflate(R.menu.menu_toolbar_nosettings, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    // caller of this fragment has to implement this interface
    public interface NavigationDrawerCallbacks {
        //当导航Drawer中的选项被选中时回调
        void onNavigationDrawerItemSelected(int position);
    }

    public void onEventMainThread(ServiceEvent event){
        Log.i(TAG, "service event --init");
        if(event.getType() == ServiceEvent.SERVICE_EVENT_INIT_OVER){
            fillUserInfo();
        }
    }

}
