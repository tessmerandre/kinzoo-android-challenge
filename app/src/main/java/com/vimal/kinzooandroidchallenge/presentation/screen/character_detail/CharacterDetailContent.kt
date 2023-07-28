package com.vimal.kinzooandroidchallenge.presentation.screen.character_detail

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.vimal.kinzooandroidchallenge.R
import com.vimal.kinzooandroidchallenge.domain.model.CharacterDetail
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import com.vimal.kinzooandroidchallenge.presentation.comman.AnimatedEpisodeShimmerEffect
import com.vimal.kinzooandroidchallenge.presentation.components.EpisodeItemContent
import com.vimal.kinzooandroidchallenge.ui.theme.BasicBlack
import com.vimal.kinzooandroidchallenge.ui.theme.CHARACTER_DETAIL_BACKGROUND_IMAGE_SIZE
import com.vimal.kinzooandroidchallenge.ui.theme.CHARACTER_DETAIL_HEADER_HEIGHT
import com.vimal.kinzooandroidchallenge.ui.theme.CHARACTER_DETAIL_IMAGE_SIZE
import com.vimal.kinzooandroidchallenge.ui.theme.LARGE_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.MEDIUM_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.SMALL_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.Shapes
import com.vimal.kinzooandroidchallenge.ui.theme.captionSize
import com.vimal.kinzooandroidchallenge.ui.theme.detailHeaderBackgroundColor
import com.vimal.kinzooandroidchallenge.ui.theme.episodeItemNameColor

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    characterDetail: CharacterDetail?,
    episodes: List<Episode>,
    isLoading: Boolean,
    widthSizeClass: WindowWidthSizeClass
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        item {
            HeaderSection(
                characterDetail = characterDetail,
                widthSizeClass = widthSizeClass
            )

            Spacer(modifier = Modifier.height(LARGE_PADDING))

            InformationSection(characterDetail = characterDetail)

            Spacer(modifier = Modifier.height(LARGE_PADDING))

        }

        item {
            CharacterDetailSubtitleText(
                modifier = Modifier.padding(start = LARGE_PADDING, bottom = MEDIUM_PADDING),
                subtitle = R.string.episodes
            )
        }

        if (isLoading) {
            items(6) {
                AnimatedEpisodeShimmerEffect()
            }
        } else {
            items(episodes) {
                EpisodeItemContent(
                    modifier = Modifier.padding(horizontal = LARGE_PADDING),
                    episode = it
                )
            }
        }
    }
}

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    characterDetail: CharacterDetail?,
    widthSizeClass: WindowWidthSizeClass
) {

    characterDetail?.let {
        val request = ImageRequest.Builder(
            context = LocalContext.current
        )
            .crossfade(300)
            .data(characterDetail.image)
            .error(R.drawable.error_image)
            .build()


        val painter = rememberImagePainter(
            request = request
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CHARACTER_DETAIL_HEADER_HEIGHT)
                .background(color = MaterialTheme.colors.detailHeaderBackgroundColor),
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(CHARACTER_DETAIL_BACKGROUND_IMAGE_SIZE),
                painter = painterResource(id = R.drawable.kinzoo_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier
                    .size(CHARACTER_DETAIL_IMAGE_SIZE)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                color = Color.White
            ) {
                Image(
                    modifier = Modifier
                        .size(CHARACTER_DETAIL_IMAGE_SIZE)
                        .padding(SMALL_PADDING)
                        .clip(CircleShape),
                    painter = painter,
                    contentDescription = stringResource(id = R.string.character_image)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.paddingFromBaseline(top = SMALL_PADDING),
                    text = characterDetail.status,
                    color = Color.Gray,
                    style = MaterialTheme.typography.caption,
                    fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
                )
                Text(
                    modifier = Modifier.paddingFromBaseline(top = LARGE_PADDING),
                    text = characterDetail.name,
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier.paddingFromBaseline(top = LARGE_PADDING),
                    text = characterDetail.species.toUpperCase(Locale.current),
                    style = MaterialTheme.typography.caption.copy(letterSpacing = 1.5.sp),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    color = MaterialTheme.colors.episodeItemNameColor
                )
            }
        }
    }
}


@Composable
fun CharacterDetailSubtitleText(
    modifier: Modifier = Modifier,
    @StringRes subtitle: Int
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
        Text(
            modifier = modifier,
            text = stringResource(subtitle),
            style = MaterialTheme.typography.h6,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InformationSection(
    modifier: Modifier = Modifier,
    characterDetail: CharacterDetail?,
) {
    characterDetail?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = LARGE_PADDING)
        ) {
            CharacterDetailSubtitleText(subtitle = R.string.informations)

            InformationContent(
                subtitle = R.string.gender,
                contentText = characterDetail.gender
            )
            InformationContent(
                subtitle = R.string.origin,
                contentText = characterDetail.origin.name
            )
            InformationContent(
                subtitle = R.string.location,
                contentText = characterDetail.location.name,
            )

        }
    }
}

@Composable
fun InformationContent(
    modifier: Modifier = Modifier,
    @StringRes subtitle: Int,
    contentText: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MEDIUM_PADDING)
            .clip(Shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.8f)
        ) {
            Text(
                modifier = Modifier.paddingFromBaseline(
                    bottom = MEDIUM_PADDING
                ),
                text = stringResource(id = subtitle),
                color = Color.BasicBlack,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )

            Text(
                modifier = Modifier.paddingFromBaseline(
                    bottom = MEDIUM_PADDING
                ),
                text = contentText,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = MaterialTheme.colors.episodeItemNameColor
            )
            Divider()
        }
    }
}