# TP 2

___
___
___

# TP 1
## Enunciado

https://github.com/unq-ui/material/blob/master/TPs/2020s1/TP1-Desktop.md

## Ejemplos Componentes Arena-kotlin

https://github.com/unq-ui/arena-kotlin-extensions

## Interfaz UNQFlix

```kotlin
domain.UNQFlix
    searchMovies(text: String): List<Movie>
    searchSeries(text: String): List<Serie>

    @throw ExistException
    addUser(user: User): Boolean 

    @throw ExistException
    addCategory(category: Category): Boolean

    @throw ExistException
    addMovie(movie: Movie): Boolean

    @throw ExistException
    addSerie(serie: Serie): Boolean

    @throw NotFoundException
    addSeason(idSerie: String, season: Season): Boolean

    @throw NotFoundException
    addChapter(idSerie: String, idSeason: String, chapter: Chapter): Boolean

    addBanner(banner: Content): Boolean

    deleteBanner(banner: Content): Boolean
    deleteMovie(movieId: String): Boolean
    deleteSerie(serieId: String): Boolean

    @throw NotFoundException
    deleteSeason(idSerie: String, idSeason: String): Boolean

    @throw NotFoundException
    deleteChapter(idSerie: String, idSeason: String, idChapter: String): Boolean

    addLastSeen(idUser: String, idContent: String): Unit
    addOrDeleteFav(idUser: String, idContent: String): Unit
```
    

