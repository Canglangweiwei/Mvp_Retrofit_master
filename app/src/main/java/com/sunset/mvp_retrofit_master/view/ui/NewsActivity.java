package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.MenuEntity;
import com.sunset.mvp_retrofit_master.model.entity.NewsEntity;
import com.sunset.mvp_retrofit_master.model.event.Event;
import com.sunset.mvp_retrofit_master.presenter.NewsContract;
import com.sunset.mvp_retrofit_master.presenter.NewsPresenter;
import com.sunset.mvp_retrofit_master.util.RxBus;
import com.sunset.mvp_retrofit_master.view.adapter.NewsListAdapter;
import com.sunset.mvp_retrofit_master.view.adapter.PopMenuAdapter;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;
import com.sunset.mvp_retrofit_master.view.base.DividerItemDecoration;
import com.sunset.mvp_retrofit_master.view.base.MySwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 列表(主页)
 */
@SuppressWarnings("ALL")
public class NewsActivity extends BaseActivity
        implements NewsContract.View,
        NewsListAdapter.OnCardItemClickLitener {

    @Bind(R.id.fab)
    FloatingActionButton fab;
    // 侧边栏菜单
    private SlidingMenu slidingMenu;
    // 左边菜单
    private LeftMenuFragment leftMenu;

    @Bind(R.id.drawer_layout)
    RelativeLayout drawerLayout;

    @Bind(R.id.txtTitle)
    TextView txtTitle;// 标题
    @Bind(R.id.top_view)
    View top_view;//

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_layout)
    MySwipeRefreshLayout mSwipeLayout;

    private NewsListAdapter newsListAdapter;

    private NewsContract.Presenter mPresenter;

    private List<NewsEntity.Result.Data> mList = new ArrayList<>();

    // 观察者
    private Subscription subscription;

    private static String titleName = "头条";
    private static String loadType = "top";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        createPresenter();
        initView();
        initMenu();
        initPopupWindow();
        // 数据加载
        loadData();
    }

    /**
     * 初始化侧边栏菜单
     */
    private void initMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setFadeEnabled(true);
        // 设置侧边栏展现后主界面剩余显示的空间；
//        slidingMenu.setBehindOffset(200);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 左边菜单
        slidingMenu.setMenu(R.layout.left_menu);
        leftMenu = new LeftMenuFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.left_menu, leftMenu).commit();
        // 通知页面是否关闭
        subscription = RxBus.getInstance().toObservable(Event.class)
                .subscribe(new Action1<Event>() {

                    @Override
                    public void call(Event event) {
                        if (event != null) {
                            switch (event.getId()) {
                                case 1000:// 关闭菜单
                                    if ("closeMenu".equals(event.getName())) {
                                        slidingMenu.showContent();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
    }

    /**
     * 初始化页面布局
     */
    @Override
    protected void initView() {
        // 设置标题
        txtTitle.setText(titleName);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        newsListAdapter = new NewsListAdapter(this);
        mRecyclerView.setAdapter(newsListAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        if (itemDecoration != null) {
            mRecyclerView.addItemDecoration(itemDecoration);
        }

        newsListAdapter.setOnItemClickLitener(this);
        mSwipeLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    protected void createPresenter() {
        // 初始化事件加载
        mPresenter = new NewsPresenter();
        mPresenter.attachView(this);
    }

    @OnClick({R.id.txtMore, R.id.left_slide})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.txtMore:
                popupWindow.showAsDropDown(top_view);
                break;
            case R.id.left_slide:// 打开左边菜单
                slidingMenu.showMenu();
                leftMenu.startAnim();
                break;
            default:
                break;
        }
    }

    /**
     * 刷新页面
     */
    @OnClick({R.id.btnRefresh})
    void refresh(View view) {
        loadData();
    }

    /**
     * 刷新
     */
    private void loadData() {
        mPresenter.loadNewsList(loadType);
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void dismissLoading() {
        cancelDialog();
    }

    /**
     * 订阅方法，当接收到事件的时候，会调用该方法
     */
    @Override
    public void showNews(NewsEntity newsEntity) {
        mSwipeLayout.refreshComplete();
        mList = newsEntity.getResult().getData();
        newsListAdapter.setList(mList);
    }

    @Override
    public void showNoMore() {
        mSwipeLayout.refreshComplete();
        Toast.makeText(this, "-------没有数据需要加载-------", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {
        mSwipeLayout.refreshComplete();
        // 请求数据失败返回失败信息
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(NewsActivity.this, WebActivity.class);
        intent.putExtra("url", mList.get(position).getUrl());
        startActivity(intent);
    }

    private long newTime;

    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {
        // 先判断菜单是否是打开着的状态，如果是先关闭
        if (slidingMenu.isMenuShowing() || slidingMenu.isSecondaryMenuShowing()) {
            slidingMenu.showContent();
        } else {
            if (System.currentTimeMillis() - newTime > 2000) {
                newTime = System.currentTimeMillis();
                Snackbar snackbar = Snackbar.make(drawerLayout, "再按一次返回键退出程序", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                snackbar.show();
            } else {
                super.onBackPressed();
            }
        }
    }

    private PopupWindow popupWindow;

    /**
     * 初始化菜单
     */
    private void initPopupWindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.pop_window, null);

        ListView ll_01 = (ListView) view.findViewById(R.id.pop_list);
        final PopMenuAdapter adapter = new PopMenuAdapter(this);
        ll_01.setAdapter(adapter);
        ll_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                MenuEntity entity = adapter.getItem(position);
                if (titleName.equals(entity.getName())) {
                    return;
                }
                titleName = entity.getName();
                txtTitle.setText(titleName);
                loadType = entity.getTypeUrl();
                loadData();
            }
        });

        popupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
    }

    @Override
    protected void onDestroy() {
        if (subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        mPresenter.detachView();
        super.onDestroy();
    }
}
