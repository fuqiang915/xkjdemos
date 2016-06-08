//package com.xkj.demos.pages;
//
//import android.animation.LayoutTransition;
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.ListView;
//import android.widget.ScrollView;
//import com.ihaveu.utils.DensityHelper;
//import com.ihaveu.utils.Log;
//import com.xkj.ui.OverlappingPaneLayout;
//import com.xkj.xkjdemos.R;
//
///**
// * Created by fuqiang on 15/1/28.
// */
//public class OverlappingPaneLayoutDemo extends Activity {
//    private final String TAG ="OverlappingPaneLayoutDemo";
//    OverlappingPaneLayout mOverlappingPaneLayout;
//    ScrollView mScrollView;
//    ListView mListView;
//    View titleView;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_overlappingpanelayout_demo);
//        DensityHelper.init(this);
//
//        mOverlappingPaneLayout = (OverlappingPaneLayout)findViewById(R.id.overlappingPaneLayout);
//        mScrollView = (ScrollView)findViewById(R.id.scroll_view);
//        mListView = (ListView) findViewById(R.id.list_view);
//        titleView = findViewById(R.id.title_view);
//        setupPaneLayout(mOverlappingPaneLayout);
//
//
//
//
//    }
//
//    private void initListView(){
//
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private void setupPaneLayout(OverlappingPaneLayout paneLayout) {
//        // TODO: Remove the notion of a capturable view. The entire view be slideable, once
//        // the framework better supports nested scrolling.
////        paneLayout.setCapturableView(titleView);
//        paneLayout.openPane();
//        paneLayout.setPanelSlideCallbacks(mPanelSlideCallbacks);
//        paneLayout.setIntermediatePinnedOffset(DensityHelper.dip2px(30));
//
////        LayoutTransition transition = paneLayout.getLayoutTransition();
////        // Turns on animations for all types of layout changes so that they occur for
////        // height changes.
////        transition.enableTransitionType(LayoutTransition.CHANGING);
//    }
//    private OverlappingPaneLayout.PanelSlideCallbacks mPanelSlideCallbacks = new OverlappingPaneLayout.PanelSlideCallbacks() {
//        @Override
//        public void onPanelSlide(View panel, float slideOffset) {
//            Log.d(TAG,"slideOffset "+slideOffset);
//            // For every 1 percent that the panel is slid upwards, clip 1 percent off the top
//            // edge of the shortcut card, to achieve the animated effect of the shortcut card
//            // being pushed out of view when the panel is slid upwards. slideOffset is 1 when
//            // the shortcut card is fully exposed, and 0 when completely hidden.
//            float ratioCardHidden = (1 - slideOffset);
////            if (mShortcutCardsListView.getChildCount() > 0) {
////                final SwipeableShortcutCard v =
////                        (SwipeableShortcutCard) mShortcutCardsListView.getChildAt(0);
////                v.clipCard(ratioCardHidden);
////            }
////
////            if (mActionBar != null) {
////                // Amount of available space that is not being hidden by the bottom pane
////                final int topPaneHeight = (int) (slideOffset * mShortcutCardsListView.getHeight());
////
////                final int availableActionBarHeight =
////                        Math.min(mActionBar.getHeight(), topPaneHeight);
////                ((HostInterface) getActivity()).setActionBarHideOffset(
////                        mActionBar.getHeight() - availableActionBarHeight);
////
////                if (!mActionBar.isShowing()) {
////                    mActionBar.show();
////                }
////            }
//        }
//
//        @Override
//        public void onPanelOpened(View panel) {
//            Log.d(TAG," onPanelOpened");
////            mIsPanelOpen = true;
//        }
//
//        @Override
//        public void onPanelClosed(View panel) {
//            Log.d(TAG," onPanelClosed ");
////            mIsPanelOpen = false;
//        }
//
//        @Override
//        public void onPanelFlingReachesEdge(int velocityY) {
//            Log.d(TAG," onPanelFlingReachesEdge "+velocityY);
////            if (getCurrentListView() != null) {
////                getCurrentListView().fling(velocityY);
////            }
//            mScrollView.fling(velocityY);
//        }
//
//        @Override
//        public boolean isScrollableChildUnscrolled() {
////            final AbsListView listView = getCurrentListView();
////            return listView != null && (listView.getChildCount() == 0
////                    || listView.getChildAt(0).getTop() == listView.getPaddingTop());
//            boolean result =mScrollView!=null && (mScrollView.getChildCount()==0 || mScrollView.getChildAt(0).getTop()==mScrollView.getPaddingTop());
//            Log.d(TAG," isScrollableChildUnscrolled "+result);
//            return mScrollView.getScrollY()!=0;
//        }
//    };
//
//}