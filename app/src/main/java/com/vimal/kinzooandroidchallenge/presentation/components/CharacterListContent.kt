package com.vimal.kinzooandroidchallenge.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.vimal.kinzooandroidchallenge.R
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.ui.theme.IMAGE_LIST_TYPE_SIZE_COMPACT_SCREEN
import com.vimal.kinzooandroidchallenge.ui.theme.IMAGE_LIST_TYPE_SIZE_MEDIUM_SCREEN
import com.vimal.kinzooandroidchallenge.ui.theme.MEDIUM_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.SMALL_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.Shapes
import com.vimal.kinzooandroidchallenge.ui.theme.body1Size
import com.vimal.kinzooandroidchallenge.ui.theme.captionSize
import com.vimal.kinzooandroidchallenge.ui.theme.statusTextColor
import com.vimal.kinzooandroidchallenge.util.MultiDevicePreviewWithOutSystemUI
import com.vimal.kinzooandroidchallenge.util.handleLoadState
import com.vimal.kinzooandroidchallenge.util.isExpandedScreen
import com.vimal.kinzooandroidchallenge.util.isMediumScreen

@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterListContent(
    characters: LazyPagingItems<Character>,
    widthSizeClass: WindowWidthSizeClass,
    onClickCharacterItem: (Int) -> Unit,

    ) {

    val result = handleLoadState(
        loadState = characters.loadState,
        onRetryClick = { characters.retry() },
        isEmptyList = characters.itemCount == 0
    )



    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(SMALL_PADDING),
        ) {

            items(
                characters,
                key = { item: Character -> item.id }
            ) {
                it?.let {
                    CharacterItem(
                        character = it,
                        widthSizeClass = widthSizeClass,
                        onClick = {
                            onClickCharacterItem(it)
                        }
                    )

                    Divider(
                        modifier = Modifier.padding(horizontal = MEDIUM_PADDING)
                    )
                }
            }
        }
    }


}

@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
    widthSizeClass: WindowWidthSizeClass,
    onClick: (characterId: Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING)
            .clip(Shapes.medium)
            .clickable {
                onClick(character.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {


        val request = ImageRequest.Builder(
            context = LocalContext.current
        )
            .crossfade(300)
            .data(character.image)
            .error(R.drawable.error_image)
            .build()


        val painter = rememberImagePainter(
            request = request
        )

        val imageSize =
            if (widthSizeClass.isMediumScreen() || widthSizeClass.isExpandedScreen())
                IMAGE_LIST_TYPE_SIZE_MEDIUM_SCREEN else IMAGE_LIST_TYPE_SIZE_COMPACT_SCREEN

        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.character_image),
            modifier = Modifier
                .size(
                    imageSize
                )
                .padding(SMALL_PADDING)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SMALL_PADDING)

        ) {

            Text(
                text = character.status,
                color = Color.statusTextColor(character.status),
                style = MaterialTheme.typography.caption,
                fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            Text(
                text = character.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1Size(widthSizeClass = widthSizeClass),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${character.gender} - ${character.species}",
                    style = MaterialTheme.typography.caption,
                    fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
                )

            }
        }

    }
}


@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@MultiDevicePreviewWithOutSystemUI
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        character = Character(
            id = 1,
            name = "Vimal Patel",
            status = "Alive",
            image = "",
            gender = "Male",
            species = "Human"
        ), onClick = {},
        widthSizeClass = WindowWidthSizeClass.Compact
    )
}


