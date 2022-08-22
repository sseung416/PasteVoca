package co.kr.searchvoca.remote.response

data class DetectiveResponse(
    val data: DetectiveData
)

data class DetectiveData(
    val detections: List<List<Detection>>
)

data class Detection(
    val confidence: Int,
    val isReliable: Boolean,
    val language: String
)

fun DetectiveResponse.getLanguageCode() =
    this.data.detections.getOrNull(0)?.getOrNull(0)?.language