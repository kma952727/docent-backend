package com.mydocent.model.art.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "playlist")
@Comment("미술 작품 스크립트 (AI를 통해 생성된 데이터)")
class PlayList (
    @Column(name = "playlist_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("pk")
    val id: Int? = null,

    @Column(name = "uuid")
    @Comment("ai 플랫폼의 데이터와 연결시켜주는 FK")
    val aiServiceKey: String? = null,

    @Comment("검색 키워드")
    @Column(name = "keyword")
    val keyword: String? = null,

    @Comment("작가명")
    @Column(name = "author")
    val author: String? = null,

    @Comment("작품명")
    @Column(name = "workTitle")
    val workTitle: String? = null,

    @Comment("위치")
    @Column(name = "location")
    val location: String? = null,

    @Comment("???")
    @Column(name = "workIntro")
    val workIntro: String? = null,

    @Comment("???")
    @Column(name = "authorIntro")
    val authorIntro: String? = null,

    @Comment("???")
    @Column(name = "workBackground")
    val workBackground: String? = null,

    @Comment("???")
    @Column(name = "appreciationPoint")
    val appreciationPoint: String? = null,

    @Comment("역사")
    @Column(name = "history")
    val history: String? = null,

    @Comment("출처")
    @Column(name = "source")
    val source: String? = null
)