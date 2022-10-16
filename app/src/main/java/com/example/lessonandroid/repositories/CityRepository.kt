package com.example.lessonandroid.repositories

import com.example.lessonandroid.entities.City
import com.example.lessonandroid.models.CityModel

object CityRepository {
    val cities = listOf<CityModel>(
        CityModel(
            City(
                0,
                "London",
                "England",
                "https://cdnn21.img.ria.ru/images/155635/45/1556354505_0:438:2000:1563_1920x0_80_0_0_64fb6d242c6240d9e44e5de1a31a7d6f.jpg",
                9_541_000,
            )
        ),
        CityModel(
            City(
                1,
                "Kazan",
                "Russia",
                "https://kazan-kremlin.ru/wp-content/uploads/ekskursii/tbyubq4muxa-1150x733.jpg",
                1_286_000,
            )
        ),

        CityModel(
            City(
                2,
                "Berlin",
                "Germany",
                "https://guide-tours.ru/wp-content/uploads/2021/04/krasivyj-berlin.jpg",
                3_571_000,
            )
        ),
        CityModel(
            City(
                3,
                "Paris",
                "France",
                "https://ru.parisinfo.com/var/otcp/sites/images/node_43/node_51/node_230/vue-a%C3%A9rienne-paris-tour-eiffel-coucher-de-soleil-%7C-630x405-%7C-%C2%A9-fotolia/19544352-1-fre-FR/Vue-a%C3%A9rienne-Paris-Tour-Eiffel-coucher-de-soleil-%7C-630x405-%7C-%C2%A9-Fotolia.jpg",
                1_114_2000,
            )
        ),
        CityModel(
            City(
                4,
                "Prague",
                "Czech",
                "https://images.prismic.io/mystique/7975faeb-fcea-484b-8539-535f5c07ebaa_Prague+Castle%2B+Chrales+Bridge.jpg",
                1_318_000,
            )
        ),
        CityModel(
            City(
                5,
                "Rome",
                "Italy",
                "https://traland.ru/wp-content/uploads/2020/12/rim.jpg",
                2_873_000,
            )
        ),
        CityModel(
            City(
                6,
                "Barcelona",
                "Spain",
                "https://espanarusa.com/files/autoupload/94/84/67/xllspuz5392644.jpg",
                1_628_000,
            )
        ),
        CityModel(
            City(
                7,
                "Budapest",
                "Hungary",
                "https://www.amigo-s.ru/content-images/2e1e5e37eec40c7f470cd603b163b258.jpg",
                1_756_000,
            )
        ),
        CityModel(
            City(
                8,
                "Amsterdam",
                "Netherlands",
                "https://blog.mydutyfree.net/images/uploaded/19.01.2022%20Amsterdam/TITLE.jpg",
                821_752,
            )
        ),

        )
}
