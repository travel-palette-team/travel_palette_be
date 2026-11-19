package com.travel.travelPalette.domain.travelalert.dto;

import com.travel.travelPalette.domain.travelalert.entity.TravelAlert;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class AlertAdjustmentResponse {

    // 외교부 해외안전여행 여행경보조정 더보기 고정 URL
    public static final String MOFA_MORE_URL = "https://www.0404.go.kr/dev/newest_list.mofa";

    /**
     * 메인/상세 화면 리스트용 응답 래퍼 (최신 3개 + 더보기 링크 포함)
     */
    @Getter
    @Builder
    public static class ListWrapper {
        private List<SummaryItem> items;
        private String moreUrl; // 프론트엔드 '더보기' 버튼 클릭 시 이동할 외교부 URL

        public static ListWrapper of(List<SummaryItem> items) {
            return ListWrapper.builder()
                    .items(items)
                    .moreUrl(MOFA_MORE_URL)
                    .build();
        }
    }

    /**
     * 리스트의 각 항목 요약 DTO
     */
    @Getter
    @Builder
    public static class SummaryItem {
        private Long id;
        private String title;
        private String countryName;
        private String alarmLevel;
        private LocalDate writtenDate;

        public static SummaryItem from(TravelAlert entity) {
            return SummaryItem.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .countryName(entity.getCountryName())
                    .alarmLevel(entity.getAlarmLevel())
                    .writtenDate(entity.getWrittenDate())
                    .build();
        }
    }

    /**
     * 리스트 클릭 시 띄울 '게시글 상세보기 팝업' 전용 DTO
     */
    @Getter
    @Builder
    public static class DetailPopup {
        private Long id;
        private String title;
        private String countryName;
        private String countryIsoCode;
        private String alarmLevel;
        private String content;
        private LocalDate writtenDate;
        private String mofaSiteUrl;

        public static DetailPopup from(TravelAlert entity) {
            return DetailPopup.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .countryName(entity.getCountryName())
                    .countryIsoCode(entity.getCountryIsoCode())
                    .alarmLevel(entity.getAlarmLevel())
                    .content(entity.getContent())
                    .writtenDate(entity.getWrittenDate())
                    .mofaSiteUrl(MOFA_MORE_URL)
                    .build();
        }
    }
}