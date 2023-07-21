/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.rememberListDetailPaneScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.samples.apps.nowinandroid.feature.interests.InterestsRoute
import com.google.samples.apps.nowinandroid.feature.topic.TopicRoute

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
)
@Composable
fun NiaApp() {
    val layoutState = rememberListDetailPaneScaffoldState(
        initialFocus = ListDetailPaneScaffoldRole.List
    )
    var selectedTopicId: String? by remember { mutableStateOf(null) }
    ListDetailPaneScaffold(
        layoutState = layoutState,
        listPane = {
            InterestsRoute(onTopicClick = { topicId ->
                selectedTopicId = topicId
                layoutState.navigateTo(ListDetailPaneScaffoldRole.Detail)
            })
        },
        detailPane = {
            selectedTopicId?.let {
                TopicRoute(
                    topicId = it,
                    onTopicClick = {},
                    onBackClick = {
                        layoutState.navigateBack(true)
                        layoutState.navigateTo(ListDetailPaneScaffoldRole.List)
                    },
                    //modifier = Modifier.preferredWidth(400.dp)
                )
            }
        },
    )
}

