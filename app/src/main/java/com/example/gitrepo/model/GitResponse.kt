package com.example.gitrepo.model

data class GitResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<Item>
)



