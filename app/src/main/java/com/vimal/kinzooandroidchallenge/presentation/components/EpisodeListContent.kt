package com.vimal.kinzooandroidchallenge.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import com.vimal.kinzooandroidchallenge.ui.theme.BOTTOM_NAV_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.BasicBlack
import com.vimal.kinzooandroidchallenge.ui.theme.LARGE_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.MEDIUM_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.SMALL_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.Shapes
import com.vimal.kinzooandroidchallenge.ui.theme.episodeItemNameColor
import com.vimal.kinzooandroidchallenge.util.MultiDevicePreviewWithOutSystemUI

@Composable
fun EpisodeItemContent(
    modifier: Modifier = Modifier,
    episode: Episode
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(
                    0.7f
                )
                .padding(SMALL_PADDING)
        ) {
            Text(
                modifier = Modifier.paddingFromBaseline(
                    top = MEDIUM_PADDING
                ),
                text = episode.episode,
                style = MaterialTheme.typography.h6,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.BasicBlack
            )


            Text(
                modifier = Modifier.paddingFromBaseline(
                    top = MEDIUM_PADDING,
                    bottom = MEDIUM_PADDING
                ),
                text = episode.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = MaterialTheme.colors.episodeItemNameColor
            )

            Text(
                text = episode.air_date,
                style = MaterialTheme.typography.caption.copy(letterSpacing = 1.5.sp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = MaterialTheme.colors.episodeItemNameColor
            )

            Spacer(modifier = Modifier.height(MEDIUM_PADDING))
            Divider()
            Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        }
    }
}

@MultiDevicePreviewWithOutSystemUI
@Composable
fun EpisodeItemPreview() {
    LazyColumn(
        contentPadding = PaddingValues(
            top = LARGE_PADDING,
            start = LARGE_PADDING,
            end = LARGE_PADDING,
            bottom = BOTTOM_NAV_PADDING
        ),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(
            listOf(
                Episode(id = 1, name = "Iron Man", air_date = "July 27, 2023", episode = "S01E01"),
                Episode(
                    id = 2,
                    name = "Iron Man 2",
                    air_date = "July 9, 2023",
                    episode = "S01E02"
                ),
                Episode(
                    id = 3,
                    name = "Iron Man 3",
                    air_date = "August 26, 2023",
                    episode = "S01E03"
                ),
                Episode(
                    id = 4,
                    name = "Iron Man 4",
                    air_date = "September 26, 2023",
                    episode = "S02E01"
                )
            )
        ) {
            EpisodeItemContent(
                episode = Episode(
                    id = it.id,
                    name = it.name,
                    air_date = it.air_date,
                    episode = it.episode
                ),
            )
        }
    }
}
