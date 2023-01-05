package com.example.postpone.detail

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowAlertDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = "Delete Note")
            },
            text = {
                Text("Are you sure to delete your note?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                    }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }) {
                    Text("Cancel")
                }
            }
        )
}