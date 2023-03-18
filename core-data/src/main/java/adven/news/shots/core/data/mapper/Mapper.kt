package adven.news.shots.core.data.mapper

fun interface Mapper<in Src, out Dest> {
    operator fun invoke(src: Src): Dest
}