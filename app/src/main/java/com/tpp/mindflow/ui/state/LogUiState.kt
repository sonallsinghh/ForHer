package com.tpp.mindflow.ui.state

data class LogUiState(
    var selectSquares: LinkedHashMap<Int, Any>,
    var promptToText: LinkedHashMap<Int, String>,
)
