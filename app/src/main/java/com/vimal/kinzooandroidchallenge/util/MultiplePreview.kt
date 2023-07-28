package com.vimal.kinzooandroidchallenge.util

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(device = Devices.PIXEL_3A, name = "Phone", showBackground = true)
@Preview(device = Devices.NEXUS_9, name = "Tablet", fontScale = 2f, showBackground = true)
annotation class MultiDevicePreviewWithOutSystemUI