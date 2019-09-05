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

Każdy typ worka reprezentowany jest przez plik `*.json` w folderze `/bagtypes`. Przykładowa struktura pliku:

* `id` - identyfikator typu worka
* `bagName` - nazwa worka
* `bagDescription` - opis worka, który pojawi się w Lore przedmiotu
* `bagItemType` - typ przedmiotu, który będzie reprezentować worek
* `craftingEnabled` - flaga określająca, czy ma być możliwość craftingu worka
* `shapelessCrafting` - flaga określająca, czy ma być możliwość craftingu worka w trybie _shapeless_ (tj. nie ma znaczenia ułożenie przedmiotów)
* `craftingRecipe` - crafting worka (_kolejność przedmiotów w liście ma znaczenie!_)
* `allowedItemAllowed` - flaga określająca, czy worek ma przyjmować wszystkie przedmioty
* `allowedItems` - przedmioty, które mają być zbierane do worka
* `type` - rodzaj przedmiotu (klasa `Material`)
* `allNamesAllowed` - flaga określająca, czy mają być dopuszczalne przedmioty z dowolną nazwą
* `allLoresAllowed` - flaga określająca, czy mają być dopuszczalne przedmioty z dowolnym Lore
* `noNameAllowed` - flaga określająca, czy ma być dopuszczony przedmiot bez customowej nazwy (tj. z domyślną nazwą w MC)
* `noNameAllowed` - flaga określająca, czy ma być dopuszczony przedmiot bez Lore
* `names` - lista nazw, które są dopuszczalne na przedmiocie
* `lores` - lista Lore, które są dopuszczalne na przedmiocie


```json
{
  "id" : "BAG_TYPE_1",
  "bagName" : "&6Worek",
  "bagDescription": [
    "&1Linia opisu 1",
    "&2Linia opisu 2"
  ],
  "bagItemType": "CHEST",
  "craftingEnabled": true,
  "craftingRecipe" : [
    "CHEST",
    "CHEST",
    "CHEST",
    "CHEST",
    "AIR",
    "CHEST",
    "CHEST",
    "CHEST",
    "CHEST"
  ],
  "allItemsAllowed": false,
  "allowedItems": [
    {
      "type": "APPLE",
      "allNamesAllowed": false,
      "allLoresAllowed": false,
      "noNameAllowed": true,
      "noLoreAllowed": true,
      "names": [
        "Nazwa 1",
        "&8Nazwa 2 (kolorowa)"
      ],
      "lores": [
        {
          "lore": [
            "Linia Lore 1",
            "&2Linia Lore 2"
          ]
        },
        {
          "lore": [
            "Inna Linia Lore 1",
            "Inna Linia Lore 2"
          ]
        }
      ] 
    } 
  ]
}
```

-----------------------
###Komendy

**Oznaczenia:**

*  `<bag_type_id>` - ID typu worka
*  `<item_type>` - typ przedmiotu (np. RED_WOOL, CHEST)
*  `<bag_name>` - nazwa worka
*  `<flag>` - flaga przyjmująca wartości: `TRUE` lub `FALSE`
*  `<line_no>` - numer linii
*  `<lore_no>` - liczba porządkowa danego Lore na liście
*  `<name_no>` - liczba porządkowa danej nazwy na liście
*  `<name>` - nazwa
*  `<line>` - linia (np. Lore)


**Administracyjne:**

*  `/kabza bag give <bag_type_id>` - otrzymanie worka o danym ID
*  `/kabza bag inspect` - wyświetlenie informacji o worku trzymanym w ręce
*  `/kabza bagtype <bag_type_id> create` - stworzenie nowego typu worka
*  `/kabza bagtype <bag_type_id> inspect all` - wyświetlenie ogólnych informacji o danym typie worka
*  `/kabza bagtype <bag_type_id> inspect alloweditem <item_type>` - wyświetlenie informacji o danym dopuszczalnym w worku przedmiocie
*  `/kabza bagtype <bag_type_id> edit set bagname <bag_name>` - ustawienie nazwy worka o danym typie
*  `/kabza bagtype <bag_type_id> edit set bagitemtype <item_type>` - ustawienie przedmiotu reprezentującego worek o danym type
*  `/kabza bagtype <bag_type_id> edit set craftingenabled <flag>` - ustawienie, czy dany typ worka można scraftować
*  `/kabza bagtype <bag_type_id> edit set shapelesscrafting <flag>` - ustawienie, czy dany typ ma mieć shapeless crafting 
*  `/kabza bagtype <bag_type_id> edit set allitemsallowed <flag` - ustawienie, czy dany typ worka może zbierać wszystkie przedmioty
*  `/kabza bagtype <bag_type_id> edit bagdescription add <line_no> <line>` - dodanie linii do opisu danego typu worka
*  `/kabza bagtype <bag_type_id> edit bagdescription remove <line_no>` - usunięcie linii z opisu danego typu worka
*  `/kabza bagtype <bag_type_id> edit bagdescription clear` - usunięcie całego opisu danego typu worka
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> set allnamesallowed <flag>` - ustawienie, czy dany dopuszczalny typ przedmiotu w worku może mieć jakikolwiek Lore
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> set allloresallowed <flag>` - ustawienie, czy dany dopuszczalby typ przedmiotu w worku może mieć jakąkolwiek nazwę
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> set nonameallowed <flag>` - ustawienie, czy dany dopuszczalny typ przedmiotu w worku może nie mieć nazwy (tj. mieć oryginalną nazwę z gry)
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> set noloreallowed <flag>` - ustawienie, czy dany dopuszczalny typ przedmiotu w worku może nie mieć Lore
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit names add <name>` - dodanie dopuszczalnej nazwy dla danego typuprzedmiotu w worku o danym typie
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit names remove <name_no>` - usunięcie dopuszczalnej nazwy dla dango typu przedmiotu w worku o danym typie
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit names clear` - usunięcie wszystkich dopuszczalnych nazw dla danego typu przedmiotu w worku o danym typie
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit lores <lore_no> add <line_no> <line>` - dodanie linii do danego Lore dla danego dopuszczalnego typu przedmiotu w worku o danym typie
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit lores <lore_no> remove all` - usunięcie danego Lore dla danego dopuszczalnego typu przedmiotu w worku o danym typie
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit lores <lore_no> remove <line_no>` - usunięcie linii z danego Lore dla danego dopuszczalnego typu przedmiotu w worku o danym typie
*  `/kabza bagtype <bag_type_id> edit alloweditem <item_type> edit lores clear` - usunięcie wszystkich dopuszczalnych Lore dla danego typu przedmiotu w worku o danym typie
*  `/kabza reload` - przeładowanie pluginu


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