package com.mascitra.digphotoworks.models.instagrams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blegoh on 17/10/17.
 */

public class Node {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("__typename")
    @Expose
    public String typename;
    @SerializedName("edge_media_to_caption")
    @Expose
    public EdgeMediaToCaption edgeMediaToCaption;
    @SerializedName("shortcode")
    @Expose
    public String shortcode;
    @SerializedName("edge_media_to_comment")
    @Expose
    public EdgeMediaToComment edgeMediaToComment;
    @SerializedName("comments_disabled")
    @Expose
    public Boolean commentsDisabled;
    @SerializedName("taken_at_timestamp")
    @Expose
    public Integer takenAtTimestamp;
    @SerializedName("dimensions")
    @Expose
    public Dimensions dimensions;
    @SerializedName("display_url")
    @Expose
    public String displayUrl;
    @SerializedName("edge_media_preview_like")
    @Expose
    public EdgeMediaPreviewLike edgeMediaPreviewLike;
    @SerializedName("owner")
    @Expose
    public Owner owner;
    @SerializedName("thumbnail_src")
    @Expose
    public String thumbnailSrc;
    @SerializedName("thumbnail_resources")
    @Expose
    public List<Object> thumbnailResources = null;
    @SerializedName("is_video")
    @Expose
    public Boolean isVideo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public EdgeMediaToCaption getEdgeMediaToCaption() {
        return edgeMediaToCaption;
    }

    public void setEdgeMediaToCaption(EdgeMediaToCaption edgeMediaToCaption) {
        this.edgeMediaToCaption = edgeMediaToCaption;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public EdgeMediaToComment getEdgeMediaToComment() {
        return edgeMediaToComment;
    }

    public void setEdgeMediaToComment(EdgeMediaToComment edgeMediaToComment) {
        this.edgeMediaToComment = edgeMediaToComment;
    }

    public Boolean getCommentsDisabled() {
        return commentsDisabled;
    }

    public void setCommentsDisabled(Boolean commentsDisabled) {
        this.commentsDisabled = commentsDisabled;
    }

    public Integer getTakenAtTimestamp() {
        return takenAtTimestamp;
    }

    public void setTakenAtTimestamp(Integer takenAtTimestamp) {
        this.takenAtTimestamp = takenAtTimestamp;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public EdgeMediaPreviewLike getEdgeMediaPreviewLike() {
        return edgeMediaPreviewLike;
    }

    public void setEdgeMediaPreviewLike(EdgeMediaPreviewLike edgeMediaPreviewLike) {
        this.edgeMediaPreviewLike = edgeMediaPreviewLike;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getThumbnailSrc() {
        return thumbnailSrc;
    }

    public void setThumbnailSrc(String thumbnailSrc) {
        this.thumbnailSrc = thumbnailSrc;
    }

    public List<Object> getThumbnailResources() {
        return thumbnailResources;
    }

    public void setThumbnailResources(List<Object> thumbnailResources) {
        this.thumbnailResources = thumbnailResources;
    }

    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }
}
