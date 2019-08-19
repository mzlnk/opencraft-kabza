#Kabza

Kabza to plugin, który umożliwia tworzenie specjalnych worków, do których automatycznie trafiają poszczególne przedmioty po ich zebraniu.
Po otworzeniu worka, otrzymujemy skrzynkę z całą zawartością worka.


-----------------------
###Worki

Każdy z worków ma maksymalną pojemność 27 slotów (tj. max 27 stacków), tzn. zachowuje się tak jak skrzynka.

Każdy stworzony worek jest reprezentowany przez plik `*.json` w folderze `/bags`. Przykładowa struktura pliku:

* `uuid` - identyfikator worka
* `opened` - czy worek został już otworzony przez gracza
* `bagTypeId` - typ danego worka
* `content` - aktualna zawartość worka

```json
{
  "uuid": "6309fb4a-f10a-4a69-9315-08eb79779f3b",
  "opened": false,
  "bagTypeId": "BAG_TYPE_2",
  "content": [
    {
      "type": "YELLOW_WOOL",
      "amount": 90
    },
    {
      "type": "YELLOW_DYE",
      "amount": 27
    }
  ]
}
```


-----------------------
###Typy worków

Każdy typ worka reprezentowany jest przez plik `*.yml` w folderze `/bagtypes`. Przykładowa struktura pliku:

* `bag_type_id` - identyfikator typu worka
* `bag_name` - nazwa worka
* `bag_description` - opis worka, który pojawi się w Lore przedmiotu
* `bag_item_type` - typ przedmiotu, który będzie reprezentować worek
* `crafting_enabled` - czy ma być możliwość craftingu worka
* `crafting_recipe` - crafting worka (_kolejność przedmiotów w liście ma znaczenie!_)
* `allowed_items` - przedmioty, które mają być zbierane do worka

```yaml
bag_type_id: BAG_TYPE_2
bag_name: '&e&lWorek Podroznika'
bag_description:
  - '&fNa podroz!'
bag_item_type: YELLOW_SHULKER_BOX
crafting_enabled: true
crafting_recipe:
  - YELLOW_WOOL
  - YELLOW_WOOL
  - YELLOW_WOOL
  - YELLOW_WOOL
  - AIR
  - YELLOW_WOOL
  - YELLOW_WOOL
  - YELLOW_WOOL
  - YELLOW_WOOL
allowed_items:
  - YELLOW_WOOL
  - YELLOW_DYE
```

-----------------------
###Komendy

**Administracyjne:**

*  `/kabza give bag <bag_type_id>` - otrzymanie specjalnego worka o danym type
*  `/kabza inspect bagtype <bag_type_id>` - wyświetlenie szczegółowych informacji o danym typie worka
*  `/kabza inspect bag` - wyświetla szczegółowe informacje na temat trzymanego w ręku worka
*  `/kabza reload` - przeładowanie configów i przechowywanych danych, z których korzysta plugin


-----------------------
###Permisje

* `kabza.admin` - wymagane, aby korzystać z komend administracyjnych


-----------------------
###API dla innych pluginów

Plugin umożliwia korzystanie przez inne pluginy z jego funkcjonalności poprzez wystawione API:

Aby umieścić plugin w repozytorium lokalnym, należy sklonować projekt, a następnie wywołać polecenie:

```text
mvn clean package
mvn install:install-file -Dfile=target/ocUtensils-2.0.jar -DgroupId=pl.opencraft -DartifactId=ocUtensils -Dversion=2.0 -Dpackaging=jar -DgeneratePom=true
```

Aby skorzystać z API, należy dodać pole `<dependency>` w pliku `pom.xml`:

```xml
<!-- Kabza -->
<dependency>
    <groupId>pl.opencraft</groupId>
    <artifactId>Kabza</artifactId>
    <version>1.0</version>
</dependency>
```

Następnie należy zadeklarować zmienną przechowującą uchwyt do API:

```java
@Override
public void onEnable() {
    kabzaApi = (KabzaApi) this.getServer().getPluginManager().getPlugin("Kabza");
}
``` 

Dostępne metody za pośrednictwem API:

```java
public BagsService getBagsService();
public BagTypesService getBagTypesService();
```


-----------------------
###Changelog

* **v1.0 - Minecraft 1.14.4 - aktualna wersja pluginu. Wersja używana obecnie na serwerze**