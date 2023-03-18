package adven.hiss.core.network.resource

import io.ktor.resources.*

@kotlinx.serialization.Serializable
@Resource("/top-headlines")
data class Headline(val country: String, val pageSize: Int, val page: Int,val category: String? = null,)