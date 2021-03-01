package ru.geekbrains.gostevnv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "data",
        "success",
        "status"
})

@NoArgsConstructor
@Data
public class GetImageResponse {

    @JsonProperty("data")
    private ImageData imageData;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;

    @Data
    public static class ImageData {

        @JsonProperty("id")
        private String id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("datetime")
        private Integer datetime;
        @JsonProperty("type")
        private String type;
        @JsonProperty("animated")
        private Boolean animated;
        @JsonProperty("width")
        private Integer width;
        @JsonProperty("height")
        private Integer height;
        @JsonProperty("size")
        private Integer size;
        @JsonProperty("views")
        public Integer views;
        @JsonProperty("bandwidth")
        private Integer bandwidth;
        @JsonProperty("vote")
        private String vote;
        @JsonProperty("favorite")
        private Boolean favorite;
        @JsonProperty("nsfw")
        private Boolean nsfw;
        @JsonProperty("section")
        private String section;
        @JsonProperty("account_url")
        private String accountUrl;
        @JsonProperty("account_id")
        private Integer accountId;
        @JsonProperty("is_ad")
        private Boolean isAd;
        @JsonProperty("in_most_viral")
        private Boolean inMostViral;
        @JsonProperty("has_sound")
        private Boolean hasSound;
        @JsonProperty("tags")
        private List<Object> tags = new ArrayList<Object>();
        @JsonProperty("ad_type")
        private Integer adType;
        @JsonProperty("ad_url")
        private String adUrl;
        @JsonProperty("edited")
        private String edited;
        @JsonProperty("in_gallery")
        private Boolean inGallery;
        @JsonProperty("deletehash")
        private String deletehash;
        @JsonProperty("name")
        private String name;
        @JsonProperty("link")
        private String link;
        @JsonProperty("ad_config")
        private AdConfig adConfig;

    }

    @Data
    public static class AdConfig {

        @JsonProperty("safeFlags")
        private List<String> safeFlags = new ArrayList<String>();
        @JsonProperty("highRiskFlags")
        private List<String> highRiskFlags = new ArrayList<String>();
        @JsonProperty("unsafeFlags")
        private List<String> unsafeFlags = new ArrayList<String>();
        @JsonProperty("wallUnsafeFlags")
        private List<String> wallUnsafeFlags = new ArrayList<String>();
        @JsonProperty("showsAds")
        private Boolean showsAds;

    }
}
