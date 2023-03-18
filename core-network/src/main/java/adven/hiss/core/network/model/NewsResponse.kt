package adven.hiss.core.network.model

@kotlinx.serialization.Serializable
data class NewsResponse(
    val status : String?,
    val totalResults : Int?,
    val articles : List<Article>?
)