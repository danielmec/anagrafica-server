# Anagrafica Demo

Progetto per gestione dati anagrafici con architettura client-server.

## Componenti

- **Server**: Backend Spring Boot con API REST
- **JPA**: Persistenza dati con Hibernate 
- **MySQL**: Database per memorizzazione anagrafica

## Funzionalità

Gestione completa dei dati gestionali: inserimento, modifica, ricerca e visualizzazione delle anagrafiche clienti.

## Configurazione MySQL

### 1. Installazione MySQL
```bash
# Su macOS con Homebrew
brew install mysql

### 2. Avvio del servizio MySQL
```bash
# macOS
brew services start mysql

### 3. Configurazione database e utente
```sql
# Accedi a MySQL come root
mysql -u root -p

# Crea il database e le varie tabelle necessarie

# Esegui l'applicazione Spring Boot
./gradlew bootRun

# Oppure su Windows
gradlew.bat bootRun
```
Il server sarà disponibile su `http://localhost:8080`

## Configurazione Database

La configurazione del database si trova in `demo/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/anagrafica?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=anagrafica_user
spring.datasource.password=password
```

Per modificare le credenziali del database, aggiorna questi parametri.