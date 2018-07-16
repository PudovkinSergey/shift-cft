# SHIFT

Необходимое ПО для разработки бекенда:

1. IntelliJ IDEA (https://www.jetbrains.com/idea/download/#section=windows). Можно скачать Ultimate, есть триал-период 30 дней, но Community для бека тоже подойдет
2. Git-клиент (https://git-scm.com/download/win) Лучше сразу научиться работать с репозиторием. Можно пользоваться Идеей, либо установить отдельный инструмент, например, SourceTree (https://www.sourcetreeapp.com/)
3. Gradle (последней версии)
4. JDK 1.8
5. Postman (или аналог) для работы с API

Как запустить бек в IDE:
1. Указать путь до gradle (ищем в Settings)
2. Указать путь до jdk (ищем в Project Structure)
3. Установить плагин lombok (ищем "plugins" в Settings). Так же нужно включить обработку аннотаций, так же ищем в Settings "Annotation Processors", ставим галочку
4. Открыть проект
5. Запустить сборку через gradle (панель обычно справа, вытащить можно через View -> Tool Window)
6. Запустить сервер (выбрать класс SpringbootSampleApplication, нажать зеленый треугольник, Idea автоматически сконфигурирует запуск)

В каталоге postman рядом с кодом бека лежит коллекция запросов. Ее можно импортировать в свой Postman и протестировать API.
Порт, на котором стартует сервер указан в application.properties, можно менять.