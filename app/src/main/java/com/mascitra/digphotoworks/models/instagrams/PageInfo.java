package com.mascitra.digphotoworks.models.instagrams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blegoh on 17/10/17.
 */

public class PageInfo {
    @SerializedName("has_next_page")
    @Expose
    public Boolean hasNextPage;
    @SerializedName("end_cursor")
    @Expose
    public String endCursor;

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public String getEndCursor() {
        return endCursor;
    }

    public void setEndCursor(String endCursor) {
        this.endCursor = endCursor;
    }
}
