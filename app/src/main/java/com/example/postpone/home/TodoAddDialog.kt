package com.example.postpone.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.postpone.R
import com.example.postpone.model.Todo

@Composable
fun TodoAddDialog(onCancel: () -> Unit, onConfirm: (Todo) -> Unit) {
    val context = LocalContext.current.applicationContext
    Dialog(
        onDismissRequest = {
            onCancel()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            var text by rememberSaveable { mutableStateOf("") }
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
                    Icon(painterResource(id = R.drawable.ic_check), "", tint = MaterialTheme.colors.secondary)
                    Text(
                        text = "New task to do",
                        style = TextStyle(
                            color = MaterialTheme.colors.secondary,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp
                        )
                    )
                }
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    singleLine = false,
                    enabled = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.secondary,
                        cursorColor = MaterialTheme.colors.secondary,
                        disabledTextColor = MaterialTheme.colors.secondary,
                        backgroundColor = MaterialTheme.colors.surface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text("Write your new task here...", color = MaterialTheme.colors.secondary.copy(0.5f))
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.End
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                onCancel()
                            }
                            .background(
                                color = MaterialTheme.colors.error,
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
                                    .makeText(
                                        context,
                                        "Congrats!! You have a new task to do.",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                                if (text.isNotEmpty() && text != "") {
                                    onConfirm(Todo(description = text))
                                } else {
                                    onCancel()
                                }
                            }
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp, start = 32.dp, end = 32.dp),
                    ) {
                        Text(
                            text = "Save",
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