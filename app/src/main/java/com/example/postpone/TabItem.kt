package com.example.postpone

sealed class TabItem(var icon: Int, var title: String) {
    object Note : TabItem(R.drawable.ic_note, "Note")
    object Todo : TabItem(R.drawable.ic_check, "Todo")
}