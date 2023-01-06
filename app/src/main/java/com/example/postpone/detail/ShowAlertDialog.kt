package com.example.postpone.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Preview
@Composable
fun ShowAlertDialog(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val context = LocalContext.current.applicationContext
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 6.dp,
                        alignment = Alignment.Start
                    )
                ) {
                    Icon(Icons.Filled.Delete, "", tint = Color.White)

                    Text(
                        text = "Delete Note",
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp
                        )
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 32.dp),
                    text = "Are you sure you want to delete this note?",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.End
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                onDismiss()
                            }
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp, start = 32.dp, end = 32.dp),
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clickable {
                                Toast
                                    .makeText(context, "Note has been deleted.", Toast.LENGTH_SHORT)
                                    .show()
                                onConfirm()
                            }
                            .background(
                                color = MaterialTheme.colors.error,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp, start = 32.dp, end = 32.dp),
                    ) {
                        Text(
                            text = "Delete",
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}