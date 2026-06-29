# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A Spring Boot demo app that acts as a reference implementation for local municipalities (lokale besturen) consuming the eLys platform API for managing death registration dossiers (overlijdensdossiers). It is **not** production-ready — it exists purely as an example.

The app is a server-rendered UI (Thymeleaf + htmx) that proxies requests to the eLys backend API, handling OAuth2 authentication on behalf of one or more municipalities identified by their KBO number.

## Commands

```bash
# Build
./gradlew build

# Run locally (dev profile, expects local eLys backend on :8090)
./gradlew bootRun --args="--spring.profiles.active=dev,secret-dev"

# Run against test environment
./start-test.sh
# which runs: ./gradlew bootRun --args="--spring.profiles.active=test,secret-test"

# Run against beta environment
./start-beta.sh
```

There are no tests in this project (`src/test` does not exist).

The app starts on port **8082** by default.

## Secret config files (not committed)

Before running you need a `src/main/resources/application-secret-<env>.yml` file:

```yaml
dao:
  gemeentes:
    security-method: private_key_jwt   # or client_secret for dev/test
    config:
      <gemeentenaam>:
        naam: <Gemeentenaam>
        kbonummer: <kbonummer>
        clientid: <clientid>
        clientsecret: <JWK json or client secret string>
```

Multiple municipalities can be added under `config`.

## Architecture

### Request flow

1. Browser → Spring MVC controller (`DossierDao`, `VerslagDao`, `BegraafplaatsReservatiesDao`, `FileDao`, `ArtsDao`)
2. Controller → `RestClientProvider.getRestClient(kbonummer)` → authenticated `RestClient`
3. `RestClient` adds OAuth2 bearer token (client credentials flow) and calls `dao.service.connection-url` (the eLys backend)
4. JSON response → parsed via `parsing/` classes → passed to Thymeleaf model → rendered

### Multi-municipality OAuth2

`ClientConfigurationProperties` reads all municipalities from `dao.gemeentes.config.*`. Each gets its own OAuth2 `ClientRegistration` (registered in `ClientRegistrationConfig`) and a pre-built `RestClient` cached by `RestClientProvider.init()`. Requests are dispatched to the correct client by `kbonummer` (passed as a query/session param).

Two auth methods are supported, set per environment via `security-method`:
- `client_secret` — used in dev/test; plain client credentials
- `private_key_jwt` — used in beta/prod; the `clientsecret` field contains a JWK JSON string

### Controllers (adapter/dao layer)

- **`DossierDao`** — dossier list, detail, afsluiten/heropen/verwijderen/aanvullen/SEG/wijzig-plaats-overlijden/ontkoppel
- **`VerslagDao`** — verslagen-beedigd-arts list, detail, koppel/ontkoppel/verwijder
- **`FileDao`** — upload/download of aktes, toestemmingen, documenten
- **`BegraafplaatsReservatiesDao`** — cemetery reservation handling
- **`ArtsDao`** — arts (doctor) lookup

### Parsing layer

The `parsing/` package contains record classes that wrap raw JSON DTOs from `json/` and expose human-readable computed values for templates. There are two main paths:

- **`OuderDanEenJaarParser`** / **`detail-ouder-dan-1-jaar.html`** — deceased older than 1 year
- **`JongerDanEenJaarParser`** / **`detail-jonger-dan-1-jaar.html`** — deceased younger than 1 year (includes pregnancy/birth data)

The `VaststellingType` on the dossier determines which path is taken in `DossierDao.dossierDetail()`.

### JSON DTOs

All API request/response shapes live under `adapter/dao/json/`. Packages group by domain area: `aanvulling`, `begraafplaats`, `inlichtingenfiche`, `medischverslag`, `moeder`, `overlijden`, `plaats`, `socioeconomische`, `statistischegegevens`, `verslag`, `historiek`, `laatsteWilsbeschikking`.

The base class `JSON` and Jackson is configured globally in `application.yml` (field visibility, constructor-detector, timezone).

### Templates

Thymeleaf templates in `src/main/resources/templates/`. Heavy use of `fragments/` for reusable sections. htmx is used for in-page actions (afsluiten, heropen, aanvullen, etc.) — fragments are returned directly from POST endpoints via `ResponseEntity<String>`.

## Backend API endpoints

The eLys API is documented via Swagger at `/swagger-ui/index.html` on the backend. Key prefixes used by this app:

- `/burgerlijke-stand/v2/dossiers` — paginated dossier list (primary)
- `/burgerlijke-stand/v1/dossiers/{id}/*` — dossier actions
- `/burgerlijke-stand/v2/verslagen-beedigd-arts` — paginated verslagen
- `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/*` — verslag actions

All requests require JSON-LD format; context files are at `/context/AangifteOverlijden.jsonld` and `/context/VerslagBeedigdArts.jsonld` on the backend.