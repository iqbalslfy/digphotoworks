package com.mascitra.digphotoworks.models.youtubes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blegoh on 02/11/17.
 */

public class PageInfo {
    @SerializedName("totalResults")
    @Expose
    public Integer totalResults;
    @SerializedName("resultsPerPage")
    @Expose
    public Integer resultsPerPage;

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
