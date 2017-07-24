package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 *
 */
public interface PtrUIHandler {

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     */
    void onUIReset(PtrFrameLayout frame);

    /**
     * prepare for loading
     */
    void onUIRefreshPrepare(PtrFrameLayout frame);

    /**
     * perform refreshing UI
     */
    void onUIRefreshBegin(PtrFrameLayout frame);

    /**
     * perform UI after refresh
     */
    void onUIRefreshComplete(PtrFrameLayout frame);

    void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator);
}
