package com.mydocent.model.question.entity

import com.mydocent.model.user.entity.User
import jakarta.persistence.*

@Entity
@Table(name = "question_history")
class QuestionHistory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "question_text")
    val questionText: String,

    @Column(name = "keyword")
    val keyword: String
)