package com.travel.travelPalette.domain.travelalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MofaAlertApiResponse {

    @JsonProperty("data")
    private List<Item> data;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        @JsonProperty("id")
        private String id; // 공지/조정 고유 ID

        @JsonProperty("title")
        private String title; // 제목

        @JsonProperty("country_nm")
        private String countryName; // 국가명

        @JsonProperty("country_iso_alp2")
        private String countryIsoCode; // 국가 ISO 2자리 코드

        @JsonProperty("alarm_lvl")
        private String alarmLevel; // 경보 단계

        @JsonProperty("txt_origin_cn")
        private String content; // 상세 내용

        @JsonProperty("wrt_dt")
        private String writtenDate; // 작성일 (YYYY-MM-DD)
    }
}