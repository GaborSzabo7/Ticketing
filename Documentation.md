# Ticketing System

Ticketing rendszer egy prototípus, amely a jegyvásárlással kapcsolatos teendőket hivatott megvalósítani. A rendszer 4 teljesen független komponensből áll.

- API
- CORE
- TICKET
- PARTNER

## API

API komponens hivatott fogadni a beérkező kéréseket és a kérésben szereplő **User-Token** ellenőrzése után a megfelelő komponenshez irányítja a kérést. A **User-Token** ellenőrzéséhez átadja a tokent a **CORE** komponensnek ellenőrzésre.

## CORE

CORE komponens kezeli a Felhasználóhoz kapcsolódó teendőket. A komponenshez tartozik egy adatbázis, amely a tartalmazza felhasználók adatait. A komponens feladata ellenőrzni a **User-Token** helyességét, Van-e elég fedezet a kártyán a díj megfizetésére, illetve, hogy a kártya a megadott felhasználóhoz tartozik-e.

## TICKET

TICKET komponens feladata a jegyvásárlással kapcsolatos teendők lebonyolítása. Ez a komponens kommunikál a **PARTNER** komponenssel az eseménnyel kapcsolatos információk megszerzése végett. Fizetés esetén a **CORE** komponenssel kommunikálva ellenőrzi a fedezetet a kártyán, illetve, hogy az adott eseményre foglalható-e jegy, amit a **PARTNER** komponenssel kommunikálva kapja meg az információt.

## Szekvencia diagram

### Események lekérdezése

```mermaid
sequenceDiagram
    create actor U as USER
    create participant API
    U ->> API: Események lekérése
    create participant CORE
    API ->> CORE: User-Token ellenőrzése
    destroy CORE
    CORE ->> API: User-Token helyes
    create participant TICKET
    API ->> TICKET: Események lekérése
    create participant PARTNER
    TICKET ->> PARTNER: Események lekérése
    destroy PARTNER
    PARTNER ->> TICKET: Események listája
    destroy TICKET
    TICKET ->> API: Események listája
    destroy API
    API ->> U: Események listája
```

### Eseményhez tartozó székek lekérése

```mermaid
sequenceDiagram
    create actor U as USER
    create participant API
    U ->> API: Székek lekérése
    create participant CORE
    API ->> CORE: User-Token ellenőrzése
    destroy CORE
    CORE ->> API: User-Token helyes
    create participant TICKET
    API ->> TICKET: Székek lekérése
    create participant PARTNER
    TICKET ->> PARTNER: Székek lekérése
    destroy PARTNER
    PARTNER ->> TICKET: Székek listája
    destroy TICKET
    TICKET ->> API: Székek listája
    destroy API
    API ->> U: Székek listája
```

### Eseményhez tartozó szék lefoglalása és fizetése

```mermaid
sequenceDiagram
    create actor U as USER
    create participant API
    U ->> API: Szék fizetése kártyával
    create participant CORE1
    API ->> CORE1: User-Token ellenőrzése
    CORE1 ->> API: User-Token helyes
    API ->> CORE1: Kártya ellenőrzése
    destroy CORE1
    CORE1 ->> API: Kártya helyes
    create participant TICKET
    API ->> TICKET: Szék fizetése kártyával
    create participant PARTNER
    TICKET ->> PARTNER: Esemény időpontjának lekérése
    PARTNER ->> TICKET: Esemény időpontja
    TICKET ->> PARTNER: Szék lefoglalása
    PARTNER ->> TICKET: Szék szabad, foglalás azonosító
    TICKET ->> PARTNER: Szék díjának lekérdezése
    destroy PARTNER
    PARTNER ->> TICKET: Szék díja
    create participant CORE2
    TICKET ->> CORE2: Van-e elég fedezete a kártyán
    destroy CORE2
    CORE2 ->> TICKET: Igen
    destroy TICKET
    TICKET ->> API: Sikeresség a fizetésről
    destroy API
    API ->> U: Sikeresség a fizetésről
```
