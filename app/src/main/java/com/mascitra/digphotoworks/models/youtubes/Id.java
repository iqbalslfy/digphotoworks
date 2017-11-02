package com.mascitra.digphotoworks.models.youtubes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blegoh on 02/11/17.
 */

public class Id {
    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("videoId")
    @Expose
    public String videoId;
    @SerializedName("channelId")
    @Expose
    public String channelId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
