package com.travel.travelPalette.domain.travelalert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "travel_alert")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TravelAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 공공데이터 고유 식별값 (중복 저장 방지용)
    @Column(unique = true, nullable = false)
    private String externalId;

    // 게시글 제목
    @Column(nullable = false)
    private String title;

    // 국가명 및 국가 코드 (ISO)
    private String countryName;
    private String countryIsoCode;

    // 경보 단계 (예: 1단계 여행유의, 2단계 여행자제, 특별여행주의보 등)
    private String alarmLevel;

    // 본문 내용 (팝업 상세보기용)
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    // 외교부 원문 상세 URL (필요 시 활용)
    private String originUrl;

    // 작성일 / 발령일
    @Column(nullable = false)
    private LocalDate writtenDate;

    public void update(String title, String alarmLevel, String content, LocalDate writtenDate) {
        this.title = title;
        this.alarmLevel = alarmLevel;
        this.content = content;
        this.writtenDate = writtenDate;
    }
}