# Тестовое задание на стажировку Android

Задача:
1. Основной экран списка покемонов - в качестве информации название, картинка и id из url. Пагинация с помощью Paging3. Список на RecyclerView из Header и самого списка, на GridLayout с дополнительной настройкой для двух колонок.
<img width="300px" src="https://github.com/wybin4/pokeapi_vk/blob/assets/sc_1.PNG"/>
2. Экран с подробностями о покемоне - Картинка, тип, рост, вес, название и id. Типы выводятся как RecyclerView с LinearLayout, где orientation=horizontal, т.к. горизонтального ListView не бывает. Может быть реализован другой тип адаптера.
<img width="300px" src="https://github.com/wybin4/pokeapi_vk/blob/assets/sc_2.PNG"/>

Картинки грузятся из GitHub PokeAPI с помощью Glide. Доступ к самому API через Retrofit+OkHttp. DataBinding. Без Hilt и других инструментов внедрения зависимостей. Корутины используются.
