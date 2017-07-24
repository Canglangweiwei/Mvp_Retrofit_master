
package com.sunset.mvp_retrofit_master.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 妹子
 * </p>
 * Created by weiwei on 2016/7/25
 */
@SuppressWarnings("all")
public class GankListEntity implements Serializable {

    private boolean error;
    private List<GankEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankEntity> getResults() {
        return results;
    }

    public void setResults(List<GankEntity> results) {
        this.results = results;
    }

    public GankListEntity() {
        super();
    }
}
